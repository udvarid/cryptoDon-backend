package com.donat.crypto.service;

import com.donat.crypto.domain.Role;
import com.donat.crypto.domain.User;
import com.donat.crypto.dto.RegisterDto;
import com.donat.crypto.dto.UserDto;
import com.donat.crypto.dto.UserLoginDto;
import com.donat.crypto.exceptions.InvalidException;
import com.donat.crypto.repository.RoleRepository;
import com.donat.crypto.repository.UserRepository;
import com.donat.crypto.security.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private final SecurityService securityService;
    private final AuthenticationProvider authenticationProvider;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       SecurityService securityService, AuthenticationProvider authenticationProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.securityService = securityService;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = findByEmail(email);
        if (user == null || !user.isEnabled()) {
            throw new UsernameNotFoundException(email);
        }
        return new UserDetailsImpl(user);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(RegisterDto registerDto) {
        if (findByEmail(registerDto.getEmail()) != null) {
            throw new InvalidException("Already registered user!");
        }
        if (registerDto.getPassword().isEmpty()) {
            throw new InvalidException("Not valid password!");
        }
        if (registerDto.getFullName().isEmpty()) {
            throw new InvalidException("Not filled full name!");
        }

        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newUser.setFullname(registerDto.getFullName());

        Role role = roleRepository.findByRole("ROLE_USER");
        newUser.getRoles().add(role);
        newUser.setEnabled(true);
        newUser.setTimeOfRegistration(LocalDateTime.now());

        userRepository.saveAndFlush(newUser);
    }


    public void login(UserLoginDto userLoginDto, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        authRequest.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = this.authenticationProvider.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    public UserDto getOneUser(String email) {
        User user = findByEmail(email);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullname());
        userDto.setRole(user.getRoles().toString());
        return userDto;
    }


}
