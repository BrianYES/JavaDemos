package brian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import brian.dao.UserDao;
import brian.pojo.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public void insert() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("Brian");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(1);
        u2.setName("Jack");
        userDao.insert(u2);
    }
}
