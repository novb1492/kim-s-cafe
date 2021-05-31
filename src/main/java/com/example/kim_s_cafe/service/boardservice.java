package com.example.kim_s_cafe.service;

import java.util.ArrayList;
import java.util.List;

import com.example.kim_s_cafe.model.board.boarddao;
import com.example.kim_s_cafe.model.board.boardvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class boardservice {

    private final boolean yes=true;
    private final boolean no=false;
    private final int pagesize=3;
    
    @Autowired
    private boarddao boarddao;

    public boolean insertarticle(boardvo boardvo) {
        try {
            boarddao.save(boardvo);
            return yes;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return no;
        
    }
    public Page<boardvo> getboards(int currentpage) {

        try {
            Page<boardvo>array=boarddao.findAll(PageRequest.of(currentpage-1, pagesize,Sort.by(Sort.Direction.DESC,"bid")));
            return array;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    public int getsearchboardscount(String title) {
        try {
            int count=boarddao.findallcountbytitle(title);
            int totalpages=count/pagesize;
            if(count%pagesize>0)
            {
                totalpages++;
            }
            return totalpages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<boardvo> getsearchboards(int currentpage,String title,int totalpages) {

        List<boardvo>array=new ArrayList<>();
        try {
            if(totalpages>0){
            int fisrt=(currentpage-1)*pagesize+1;
            int end=fisrt+pagesize-1;
            array=boarddao.findsearch(title,fisrt,end);
            }
            System.out.println("검색완료"+array.get(0).getBid());
            return array;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
}
