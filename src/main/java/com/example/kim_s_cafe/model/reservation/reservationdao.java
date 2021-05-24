package com.example.kim_s_cafe.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface reservationdao extends JpaRepository<reservationvo,Integer> {
    
}
