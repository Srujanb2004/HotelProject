package com.example.hotel.repository;

import com.example.hotel.entities.Customer;
import com.example.hotel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
