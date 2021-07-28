package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DbDao extends JpaRepository<DBEntity, Integer> {
    DBEntity findByShortkey(int hash);
    String findByUrls(String url);
}
