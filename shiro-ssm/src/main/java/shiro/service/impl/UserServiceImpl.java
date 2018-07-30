package shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shiro.dao.UserDAO;
import shiro.domain.User;
import shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public String getDBPassword(String name) {
        User user = userDAO.getByName(name);
        if (user == null) {
            return null;
        }
        return user.getPswd();
    }
}
