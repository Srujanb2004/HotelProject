package com.example.hotel.repository;

import com.example.hotel.entities.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Integer> {
}
