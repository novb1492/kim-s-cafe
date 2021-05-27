package com.example.kim_s_cafe.model.reservation;



import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface reservationdao extends JpaRepository<reservationvo,Integer> {
    
    @Query(value = "select requesthour from reservation where seat=?1",nativeQuery = true)
    List<Integer>findbyseat(String seat);

  
    @Query(value = "insert into  reservation (created, remail, requesthour, rname, seat)values(?1, ?2, ?3, ?4, ?5)",nativeQuery = true)
    void insertonly(Timestamp created,String remail, int requesthour,String rname,String seat);
   
}
