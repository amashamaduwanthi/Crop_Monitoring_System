package com.example.Crop_Monitoring_system.Service.impl;




import com.example.Crop_Monitoring_system.Exception.UserNotFoundException;
import com.example.Crop_Monitoring_system.Service.UserService;
import com.example.Crop_Monitoring_system.dao.UserDao;
import com.example.Crop_Monitoring_system.dto.impl.UserWithKey;
import com.example.Crop_Monitoring_system.entity.impl.UserEntity;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDao.findByEmail(username).
                        orElseThrow(()->new UserNotFoundException("User Name Not Found"));
    }

    @Override
    public boolean sendCodeToChangePassword(UserWithKey userWithKey) {
        Optional<UserEntity>byEmail=userDao.findByEmail((userWithKey.getEmail()));
        if (byEmail.isPresent()){
            return true;
        }
        return false;
    }
}
