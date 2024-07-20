package com.example.demo.repository;

import com.example.demo.entity.SocialMediaEntityComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryComment extends JpaRepository<SocialMediaEntityComment, Long> {
}
