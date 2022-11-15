package shiro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shiro.dao.RoleDAO;
import shiro.domain.Role;
import shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Set<String> listRoleNames(String userName) {
        List<Role> roles = roleDAO.listRolesByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Role role: roles) {
            result.add(role.getRoleName());
        }
        return result;
    }
}
