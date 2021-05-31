package com.example.kim_s_cafe.contoroller;




import java.util.List;

import com.example.kim_s_cafe.model.board.boarddao;
import com.example.kim_s_cafe.model.board.boardvo;
import com.example.kim_s_cafe.model.reservation.reservationvo;
import com.example.kim_s_cafe.model.user.uservo;
import com.example.kim_s_cafe.service.boardservice;
import com.example.kim_s_cafe.service.contentservice;
import com.example.kim_s_cafe.service.reservationservice;
import com.example.kim_s_cafe.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class controller {

    @Autowired
    private userservice userservice;
    @Autowired
    private reservationservice reservationservice;
    @Autowired
    private boardservice boardservice;
    @Autowired
    private contentservice contentservice;
 

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
    @GetMapping("reservationcanclepage")
    public String reservationcanclepage(Model model) {
        reservationservice.check24();
        String email=userservice.getemail();
        List<reservationvo>array=reservationservice.findreservation(email);
        int nowhour=reservationservice.gethour();
        model.addAttribute("nowhour", nowhour);
        model.addAttribute("array",array);
        return "reservationcanclepage";
    }
    @PostMapping("reservationupdatepage")
    public String reservationupdatepage(reservationvo reservationvo,Model model) {
        model.addAttribute("reservationvo",reservationvo);
        return "reservationupdatepage";
    }
    @GetMapping("/auth/boardlist")
    public String boardlist(Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {  
       Page<boardvo>array=boardservice.getboards(currentpage);
       model.addAttribute("array", array);
       model.addAttribute("totalpage", array.getTotalPages());
       model.addAttribute("currentpage", currentpage);
        return "boardlist";
        
    }
    @GetMapping("/writearticle")
    public String  writepage() {
        
        return "writearticle";
    }
    @GetMapping("/auth/search")
    public String search(@RequestParam("title")String title,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {

        int totalpages=boardservice.getsearchboardscount(title);
        System.out.println("검색한 키워드 총페이지 "+totalpages);
        List<boardvo>array=boardservice.getsearchboards(currentpage, title,totalpages);
        model.addAttribute("currentpage", currentpage);
        model.addAttribute("totalpage", totalpages);
        model.addAttribute("array", array);
        
        return "boardlist"; 
    }
    @GetMapping("/auth/content")
    public String content(@RequestParam("bid")int bid,Model model,@RequestParam(value="page", defaultValue = "1") int currentpage) {
        try {
            boardvo boardvo= contentservice.getcontent(bid);
            System.out.println("들어온 글번호"+boardvo.getBid());
            model.addAttribute("boardvo", boardvo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "content";
    }
    @GetMapping("updatecontent")
    public String updatecontent(@RequestParam("bid")int bid,Model model) {
        
        boardvo vo=contentservice.getcontentnohit(bid);
        if(vo!=null){
            model.addAttribute("boardvo", vo);
            return "updatecontent";
        }else{
            return "updatecontent";
        }
    }
}
