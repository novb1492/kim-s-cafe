package com.example.kim_s_cafe.service;

import java.util.List;

import com.example.kim_s_cafe.model.reservation.reservationdao;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationservice {
    @Autowired
    private reservationdao reservationdao;
    
    public void log(reservationvo reservationvo) {
 
            System.out.println("예약을 시도 하는 자리"+reservationvo.getSeat());
        
        System.out.println("예약을 시도하는이메일  "+reservationvo.getRemail());
        System.out.println("예약을 시도하는이름 "+reservationvo.getRname());
        System.out.println("예약을 시도하는인원 "+reservationvo.getPersons());
        System.out.println("예약을 시도하는날짜"+reservationvo.getReservationday()); 
        System.out.println("예약을 원하는시간"+reservationvo.getHour()+reservationvo.getMinuite()); 
        System.out.println("대여하는시간"+reservationvo.getRentaltime()); 
    } 
    public boolean confirm(String seat) {
        System.out.println("예약검사"+seat);
        try {
            List<reservationvo>array=reservationdao.findbyseat(seat);
            reservationvo reservationvo=array.get(0);///아하 이렇게 꺼내는거구나 20210524
            System.out.println(reservationvo.getHour()+"예약시간");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public String insertreservation(reservationvo reservationvo) {

        boolean check=confirm(reservationvo.getSeat());
        if(true)
        {
            try {
            
   
                reservationdao.save(reservationvo);
                return "yes";
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
        return "no";
    }
 
    
}
