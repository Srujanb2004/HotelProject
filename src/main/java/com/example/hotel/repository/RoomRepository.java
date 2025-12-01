package com.example.hotel.repository;

import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

	Optional<Room> findByRoomNum(Integer roomId);
}
