package com.aalok.UserServiceApplication.service;

import com.aalok.UserServiceApplication.Repo.UserRepo;
import com.aalok.UserServiceApplication.dto.UserRequestDTO;
import com.aalok.UserServiceApplication.model.Users;
import com.aalok.Utilities.CommonConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public Users addUpdate(UserRequestDTO dto) throws JsonProcessingException {
        Users user=dto.toUser();
        user.setAuthorities(userAuthority);
        {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        user = userRepo.save(user);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put(CommonConstants.USER_CONTACT,user.getContact());
        jsonObject.put(CommonConstants.USER_EMAIl, user.getEmail());
        jsonObject.put(CommonConstants.USER_NAME , user.getName());
        jsonObject.put(CommonConstants.USER_IDENTIFIER , user.getUserIdentifier());
        jsonObject.put(CommonConstants.USER_IDENTIFIER_VALUE , user.getUserIdentifierValue());
        jsonObject.put(CommonConstants.USER_ID, user.getPk());
        kafkaTemplate.send(CommonConstants.USER_CREATED_TOPIC,objectMapper.writeValueAsString(jsonObject));
        return user;
    }

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= userRepo.findByContact(username);
        System.out.println("got the user Details" + user);
        return user;
    }
}
