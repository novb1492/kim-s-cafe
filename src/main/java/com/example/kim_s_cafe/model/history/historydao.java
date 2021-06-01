package com.example.kim_s_cafe.model.history;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface historydao extends JpaRepository<historyvo,Integer> {
    
    @Query(value = "select *from history where remail=?1 order by hid desc limit ?2,?3",nativeQuery = true)
    List<historyvo>gethistorybyemail(String email,int first,int end);

    @Query(value = "select count(*) from history where remail=?1",nativeQuery = true)
    int countbyeamil(String email);

    @Query(value = "select *from history where reamil=?1 order by hid",nativeQuery = true)
    List<historyvo>gethistorybyemail2(String email);
}
