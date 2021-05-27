package com.example.kim_s_cafe.service;


import java.util.ArrayList;
import java.util.List;

import com.example.kim_s_cafe.model.reservation.reservationdao;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationservice {
    private final boolean yes=false;
    private final boolean no=true;

    @Autowired
    private reservationdao reservationdao;
    
    public void log(reservationvo reservationvo,List<Integer> requesthour) {
            for(int i=0;i<requesthour.size();i++)
            {
                System.out.println("예약을 시도하는시간"+requesthour.get(i));  
            }
            System.out.println("예약을 시도 하는 자리"+reservationvo.getSeat());
            System.out.println("예약을 시도하는이메일 "+reservationvo.getRemail());
            System.out.println("예약을 시도하는이름 "+reservationvo.getRname());
    } 
    public List<Integer> reservationconfirm(String seat) {

        try {
            List<Integer>array=reservationdao.findbyseat(seat);
            for(int i=0;i<array.size();i++)
            {
                System.out.println(array.get(i)+"이미 예약되어있는시간");
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertreservation(reservationvo reservationvo) {
      
            try {

      
                    reservationdao.save(reservationvo);
                

                //reservationdao.insertonly(reservationvo.getCreated(),reservationvo.getRemail(), reservationvo.getRequesthour(), reservationvo.getRname(), reservationvo.getSeat());
                return yes;
            } catch (Exception e) {
                e.printStackTrace();      
            }
            return no;  
    }
 
    
}
