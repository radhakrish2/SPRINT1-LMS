package com.learnhub.repository;

import com.learnhub.entity.Roles;
import com.learnhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {

	
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByEmailAndRole(String email, Roles role);
    
    List<User> findTop10ByOrderByIdDesc();

}