package com.donat.crypto.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.donat.crypto.domain.User;
import com.donat.crypto.dto.RegisterDto;
import com.donat.crypto.dto.UserDto;
import com.donat.crypto.dto.UserLoginDto;

public interface UserService {
	void login(UserLoginDto userLoginDto, HttpServletRequest request);
	void registerUser(RegisterDto registerDto);
	UserDto getOneUser(String email);
	User findByEmail(String email);
	User saveOrUpdate(User user);
	List<User> listAll();
}
