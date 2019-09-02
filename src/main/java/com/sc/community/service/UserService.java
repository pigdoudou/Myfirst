package com.sc.community.service;

import com.sc.community.mapper.UserMapper;
import com.sc.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: An
 * @Date: Created in 16:062019/9/2
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        Boolean userExists=userMapper.findByAccountId(user.getAccountId());
        if(userExists){
            userMapper.updateUser(user);
        }else {
            userMapper.insertUser(user);
        }
    }
}
