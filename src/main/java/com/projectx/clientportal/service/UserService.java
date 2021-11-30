package com.projectx.clientportal.service;

import com.projectx.clientportal.model.User;
import com.projectx.clientportal.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        User temp = this.userDao.findUserByEmail(user.getEmail());
        if(temp != null)
            return null;
        return this.userDao.save(user);
    }

    public User getUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }

    public User editUser(User user) {
        User temp = this.userDao.findUserByEmail(user.getEmail());
        if(temp == null)
            return null;
        else {
            if(user.getEmail() != null)
                temp.setEmail(user.getEmail());
            if(user.getPassword() != null)
                temp.setPassword(user.getPassword());
            if(user.getApproved() != null)
                temp.setApproved(user.getApproved());
            return this.userDao.save(user);
        }
    }

    public void deleteUser(User user) {
        this.userDao.delete(user);
    }
}
