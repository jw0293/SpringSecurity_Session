package com.example.security.springsecurity.app.user.service;

import com.example.security.springsecurity.app.user.model.UserVO;

public interface UserService {

    UserVO login(UserVO userVO);

    UserVO createUser(UserVO userVO);

    UserVO findUserByUserEmail(String userEmail);
}
