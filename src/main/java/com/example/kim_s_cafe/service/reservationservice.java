package com.example.kim_s_cafe.service;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.example.kim_s_cafe.model.reservation.reservationdao;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationservice {

    private final boolean yes=false;
    private final boolean no=true;
    private final byte opentime=6;
    private final byte endtime=22;

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

        List<Integer>array2=new ArrayList<>();

        Calendar cal = Calendar.getInstance();
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour+"현재시간");
        try {

            List<Integer>array=reservationdao.findbyseat(seat);
        
            if(!array.isEmpty()){///비어있나 확인해야함
                for(int ii=opentime;ii<=endtime;ii++){
                    for(int i=0;i<array.size();i++){
                            if(ii==array.get(i)||ii<=hour){
                                System.out.println(ii+"이미 예약"); 
                                break;   
                            }
                            else{  
                                if(i==array.size()-1){
                                    System.out.println(ii+"자바스크립트"); 
                                    array2.add(ii);
                                }
                            }
                    }
                } 
            }
            else{
                for(int i=opentime;i<=endtime;i++){
                    if(i>hour){
                        System.out.println(i+"자바스크립트"); 
                        array2.add(i);  
                    }
                }
            }
            return array2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertreservation(reservationvo reservationvo,List<Integer> requesthour) {
      
            try {  
                for(int i=0;i<requesthour.size();i++){
                    reservationvo reservationvo2=new reservationvo();///20210528그래준영아 객체를 비워줘야지.. 안그러면 update만 되잖아..
                    reservationvo2.setRequesthour(requesthour.get(i));
                     reservationvo2.setCreated(reservationvo.getCreated());
                     reservationvo2.setRemail(reservationvo.getRemail());
                     reservationvo2.setRname(reservationvo.getRname());
                     reservationvo2.setSeat(reservationvo.getSeat());
                     reservationdao.save(reservationvo2);   
                }               
                return yes;
            } catch (Exception e) {
                e.printStackTrace();      
            }
            return no;  
    }
 
    
}
