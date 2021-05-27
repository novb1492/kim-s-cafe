package com.example.kim_s_cafe.model.user;
import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userdao extends JpaRepository<uservo,String> {///얘는 인터페이스로
    
    @Query(value="select pwd from members where email=?1",nativeQuery = true)
    String getpwd(String pwd);
}
