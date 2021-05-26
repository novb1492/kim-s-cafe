package com.example.kim_s_cafe.model.reservation;



import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface reservationdao extends JpaRepository<reservation,Integer> {
    
    @Query(value = "select * from seat where seat=?1",nativeQuery = true)
    List<reservation>findbyseat(String seat);
    @Query(value = "insert into reservation(remail,requesthour,rname,seat)VALUES(?1,?2,?3,?4)",nativeQuery = true)
    int onlyinsert(String remail,int requesthour,String rname,String seat);
}
