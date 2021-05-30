package com.example.kim_s_cafe.service;

import com.example.kim_s_cafe.model.history.historydao;
import com.example.kim_s_cafe.model.history.historyvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class historyservice {

    @Autowired
    private historydao historydao;
    
    public historyvo inserthistory(reservationvo reservationvo) {
        
        historyvo historyvo=new historyvo();
        historyvo.setRid(reservationvo.getRid());
        historyvo.setRequesthour(reservationvo.getRequesthour());
        historyvo.setCreated(reservationvo.getCreated());
        historyvo.setRemail(reservationvo.getRemail());
        historyvo.setRname(reservationvo.getRname());
        historyvo.setSeat(reservationvo.getSeat());
        return historyvo;
    }
    public void inserthistory(historyvo historyvo) {
        try {
            historydao.save(historyvo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    public void deletehistory(int rid) {

        try {
            historydao.deleteById(rid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
