package com.spring.boot.blog.Springbootblog.dao;

import com.spring.boot.blog.Springbootblog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(Role roleName);

}
