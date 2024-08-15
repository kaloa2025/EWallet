package com.aalok.UserServiceApplication.service;

import com.aalok.UserServiceApplication.Repo.UserRepo;
import com.aalok.UserServiceApplication.dto.UserRequestDTO;
import com.aalok.UserServiceApplication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${user.Authority}")
    private String userAuthority;
    @Value("${admin.Authority}")
    private String adminAuthority;
    public Users addUpdate(UserRequestDTO dto)
    {
        Users user=dto.toUser();
        user.setAuthorities(userAuthority);
        if(userRepo.findByContact(user.getContact())!=null)
        {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
