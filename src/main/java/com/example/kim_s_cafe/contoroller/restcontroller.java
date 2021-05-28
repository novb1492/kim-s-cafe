package com.example.kim_s_cafe.contoroller;






import java.util.List;

import com.example.kim_s_cafe.model.reservation.reservationdao;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restcontroller {

    private final boolean yes=true;
    private final boolean no=false;

    @Autowired
    private userservice userservice;
    @Autowired
    private reservationservice reservationservice;
    
    @PostMapping("/auth/comfirm")
    public String checkemail(@RequestParam("email")String email) {
        System.out.println("email");
        String yesorno=userservice.checkemail(email);
        return yesorno;
    }
    @PostMapping("updatepwdpageprocess")
    public String updatepwdpageprocess(@RequestParam("email")String email,@RequestParam("pwd")String pwd,@RequestParam("npwd")String npwd,@RequestParam("npwd2")String npwd2) {
       
        if(npwd.equals(npwd2)){
            if(userservice.checkpwdwithdbpwd(pwd, email)){

                if(userservice.updatepwd(email, npwd2)){
                    return "yes";
                }
                else{
                    return "error";
                }
            }
            else{
                return "wrongpwd";
            }
        }
        else{
            return "npwd!=npwd2";
        }
    }
    @PostMapping("reservationconfrim")
    public List<Integer> reservationconfirm(@RequestParam("seat")String seat) {
        List<Integer>array=reservationservice.reservationconfirm(seat);
        return array;
    }
    @PostMapping("reservationprocess")
    public boolean reservationprocess(reservationvo reservationvo,@RequestParam(value = "requesthour[]")List<Integer> requesthour) { ///checkbox로 받을때 value = "파라미터이름[]" 과 List로만 해야한다 20210526
            
            reservationservice.log(reservationvo,requesthour);        
            boolean yorn=reservationservice.insertreservation(reservationvo,requesthour);
            if(yorn){
                 return no;
            }
        return yes;
    }
    @PostMapping("reservationcancleprocess")
    public boolean reservationcancleprocess(@RequestParam("rid")int rid) {
    System.out.println("test중");
    reservationservice.deletereservation(rid);
   
        return no;
    }
  
    
}
