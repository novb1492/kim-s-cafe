package com.example.kim_s_cafe.contoroller;






import java.util.List;

import com.example.kim_s_cafe.model.board.boardvo;
import com.example.kim_s_cafe.model.comment.commentvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.service.boardservice;
import com.example.kim_s_cafe.service.commentservice;
import com.example.kim_s_cafe.service.contentservice;
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
    @Autowired
    private boardservice boardservice;
    @Autowired
    private contentservice contentservice;
    @Autowired
    private commentservice commentservice;
    
    @PostMapping("/auth/comfirm")
    public boolean checkemail(@RequestParam("email")String email) {
        System.out.println("email"+email);
        boolean yorn=userservice.checkemail(email);
        if(yorn){
            return yes;
        }
        return no;
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
        reservationservice.check24();
        return reservationservice.reservationconfirm(seat);
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
        System.out.println("예약취소rid= "+rid);
        boolean yorn=reservationservice.deletereservation(rid);
        if(yorn){
            return no;
        }
        return yes;
    }
    @PostMapping("reservationupdateprocess")
    public boolean reservationupdateprocess(reservationvo reservationvo,@RequestParam(value = "requesthour[]")List<Integer> requesthour) {
        
        System.out.println("변경시도");
        reservationservice.log(reservationvo, requesthour);
        boolean yorn=reservationservice.reservationupdate(reservationvo);
        if(yorn){
            return no;
        }else{
            return yes;    
        } 
    }
    @PostMapping("writearticleprocess")
    public boolean writearticleprocess(boardvo boardvo) {
        
      boolean yorn= boardservice.insertarticle(boardvo);
      if(yorn){
          return yes;
      }
      return no; 
    }
    @PostMapping("updatecontentprocess")
    public boolean updatecontentprocess(boardvo vo,@RequestParam("bid")int bid) {
        
        boolean yorn=contentservice.updatecontent(bid, vo);
        if(yorn){
            return yes;
        }else{
            return no;
        }
        
    }
    @PostMapping("insertcomment")
    public boolean insertcomment(commentvo commentvo) {
        System.out.println("댓글을 시도하는 이메일 "+commentvo.getEmail());
        boolean yorn=commentservice.insertcomment(commentvo);
        if(yorn){
            return yes;
        }
        return no;
    }
    @PostMapping("deletearticle")
    public boolean deletearticle(@RequestParam("bid")int bid) {
        boolean yorn=boardservice.deletearticle(bid);
        boolean yorn2=commentservice.deletecommentbybid(bid);
        if(yorn&&yorn2){
            return yes;
        }else{
            return no;
        }
    }
  
    
}
