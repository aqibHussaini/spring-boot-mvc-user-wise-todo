package com.App.repository;

import com.App.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface userRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByName(String name);
    User findByPassword(String password);


 }
