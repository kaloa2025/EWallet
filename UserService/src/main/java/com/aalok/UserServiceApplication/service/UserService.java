package com.aalok.UserServiceApplication.service;

import com.aalok.UserServiceApplication.Repo.UserRepo;
import com.aalok.UserServiceApplication.dto.UserRequestDTO;
import com.aalok.UserServiceApplication.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public Users addUpdate(UserRequestDTO dto)
    {
        Users user=dto.toUser();
        user.setAuthorities(userAuthority);
        {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        user = userRepo.save(user);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("contact",user.getContact());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
