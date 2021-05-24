package com.example.kim_s_cafe.service;

import com.example.kim_s_cafe.model.reservation.reservationdao;
import com.example.kim_s_cafe.model.reservation.reservationvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationservice {
    @Autowired
    private reservationdao reservationdao;
    
    public void log(String[] seat,String email,String name,int persons) {
        for(int i=0;i<seat.length;i++)
        {  
            System.out.println("예약을 시도 하는 자리"+seat[i]);
        }
        System.out.println("예약을 시도하는이메일  "+email);
        System.out.println("예약을 시도하는이름 "+name);
        System.out.println("예약을 시도하는인원 "+persons);
    } 
    
    public boolean insertreservation(reservationvo reservationvo) {
        try {
            reservationdao.save(reservationvo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    
}
