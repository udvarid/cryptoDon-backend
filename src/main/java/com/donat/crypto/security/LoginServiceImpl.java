package com.donat.crypto.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.donat.crypto.domain.User;
import com.donat.crypto.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Scheduled(fixedRate = 600000)
	@Override
	public void resetFailedLogins() {
		System.out.println("Checking for locked accounts");

		List<User> userList = userService.listAll();

		userList.forEach(user -> {
			if(!user.isEnabled() && user.getFailedLoginAttempts() > 0){
				System.out.println("Resetting failed attempts for user: " + user.getFullname());
				user.setFailedLoginAttempts(0);
				user.setEnabled(true);
				userService.saveOrUpdate(user);
			}
		});

	}

}
