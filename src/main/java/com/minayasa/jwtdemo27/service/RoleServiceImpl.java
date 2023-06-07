package com.minayasa.jwtdemo27.service;

import com.minayasa.jwtdemo27.entity.RoleEntity;
import com.minayasa.jwtdemo27.model.RoleModel;
import com.minayasa.jwtdemo27.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleModel createRole(RoleModel roleModel) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleModel, roleEntity); // (resource, target) model to entity
        RoleEntity savedRole = roleRepository.save(roleEntity);
        BeanUtils.copyProperties(savedRole, roleModel); // (resource, target) entity to model
        return roleModel;
    }

    @Override
    public List<RoleModel> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntityList) {
            RoleModel roleModel = new RoleModel();
            BeanUtils.copyProperties(roleEntity, roleModel);
            roleModels.add(roleModel);
        }
        return roleModels;
    }

    @Override
    public RoleModel getRoleById(Long roleId) {
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new UsernameNotFoundException("role not found"));
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(roleEntity, roleModel);
        return roleModel;
    }
}
