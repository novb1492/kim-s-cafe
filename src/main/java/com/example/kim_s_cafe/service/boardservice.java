package com.example.kim_s_cafe.service;

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
}
