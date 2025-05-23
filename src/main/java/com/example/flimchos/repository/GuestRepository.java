package com.example.flimchos.repository;

import com.example.flimchos.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository <Guest, Long> {
}
