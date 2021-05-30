package com.example.kim_s_cafe.model.history;



import org.springframework.data.jpa.repository.JpaRepository;

public interface historydao extends JpaRepository<historyvo,Integer> {
    
}
