package com.example.demo.dao;

import com.example.demo.entity.DBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbDao extends JpaRepository<DBEntity, Integer> {
    DBEntity findByShortkey(int hash);

}
