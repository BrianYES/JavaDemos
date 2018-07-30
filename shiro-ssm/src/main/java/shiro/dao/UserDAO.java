package shiro.dao;

import shiro.domain.User;

public interface UserDAO {

    User getByName(String name);
}
