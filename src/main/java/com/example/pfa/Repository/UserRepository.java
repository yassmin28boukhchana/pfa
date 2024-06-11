package com.example.pfa.Repository;

import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional <User> findByEmail(String email);
}
