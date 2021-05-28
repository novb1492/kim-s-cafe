package com.example.kim_s_cafe.contoroller;




import java.util.List;

import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.model.user.uservo;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class controller {

    @Autowired
    private userservice userservice;
    @Autowired
    private reservationservice reservationservice;
 

    @PostMapping("/auth/joinprocess")
    public String name(uservo uservo) {
        boolean tf=userservice.insertmember(uservo);
        if(tf==true)
        {
            return "loginpage";
        }
        else{
            return "joinpage";
        }
    }
    @GetMapping("/auth/loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @GetMapping("/auth/joinpage")
    public String joinpage() {

        return "joinpage";
        
    }
    @GetMapping("/auth/index")
    public String index() {

        return "index";
        
    }
    @GetMapping("mypage")
    public String mypage(Model model) {
    
        String email=userservice.getemail();
        uservo uservo=userservice.getinfor(email);
        model.addAttribute("uservo", uservo);
        return "mypage";
    }
    @GetMapping("updatepwdpage")
    public String updatepwdpage() {
    
        return "updatepwdpage";
    }
    @GetMapping("/auth/reservationpage")
    public String reservationpage() {
        return "reservationpage";
    }
    @GetMapping("/reservationcanclepage")
    public String reservationcanclepage(Model model) {

        String email=userservice.getemail();
        List<reservationvo>array=reservationservice.findreservation(email);
        model.addAttribute("array",array);
        return "reservationcanclepage";
    }

    
}
