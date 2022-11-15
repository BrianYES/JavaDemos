package shiro.service;

import java.util.Set;

public interface RoleService {

    Set<String> listRoleNames(String userName);
}
