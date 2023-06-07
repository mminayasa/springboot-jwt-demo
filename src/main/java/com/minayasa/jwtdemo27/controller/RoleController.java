package com.minayasa.jwtdemo27.controller;

import com.minayasa.jwtdemo27.entity.RoleEntity;
import com.minayasa.jwtdemo27.model.RoleModel;
import com.minayasa.jwtdemo27.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<RoleModel> createRoleEntity(@RequestBody RoleModel roleModel) {
        return ResponseEntity.ok(roleService.createRole(roleModel));
    }

    @GetMapping("/roles")
    public List<RoleModel> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public  RoleModel getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
}
