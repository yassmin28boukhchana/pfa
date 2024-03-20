package com.example.pfa.Repository;

import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
