package com.aalok.UserServiceApplication.Repo;

import com.aalok.UserServiceApplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByContact(String contact);
    Users findByEmail(String email);
}
