package com.example.demo.repository;

import com.example.demo.entity.SocialMediaEntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<SocialMediaEntityUser, Long> {
    Optional<SocialMediaEntityUser> findByEmail(String email);
}
