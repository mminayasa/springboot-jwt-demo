package com.minayasa.jwtdemo27.service;

import com.minayasa.jwtdemo27.model.RoleModel;

import java.util.List;

public interface RoleService {
    public RoleModel createRole(RoleModel roleModel);
    public List<RoleModel> getAllRoles();
    public RoleModel getRoleById(Long roleId);
}
