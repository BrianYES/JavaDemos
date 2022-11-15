package shiro.dao;

import java.util.List;

import shiro.domain.Role;

public interface RoleDAO {

    List<Role> listRolesByUserName(String userName);
}
