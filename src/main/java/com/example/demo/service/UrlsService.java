package com.example.demo.service;

import com.example.demo.dao.DbDao;
import com.example.demo.entity.DBEntity;
import com.example.demo.request.StoreUrlRequest;
import com.example.demo.response.CountResponse;
import com.example.demo.response.GetResponse;
import com.example.demo.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlsService {
    @Autowired
    private DbDao dbDao;
    public void storeDataToDb(StoreUrlRequest storeUrlRequest){
        DBEntity dbEntity=new DBEntity();
        dbEntity.setUrls(storeUrlRequest.getUrls());
        dbEntity.setShortkey(storeUrlRequest.getUrls().hashCode());
        dbEntity.setCount(0);
        dbDao.save(dbEntity);
    }

    public GetResponse incrementCount(StoreUrlRequest storeUrlRequest){
        DBEntity dbEntity=new DBEntity();
        try {
            int shortKey = storeUrlRequest.getUrls().hashCode();
            DBEntity dbEntity1 = dbDao.findByShortkey(shortKey);
                dbEntity.setCount(dbEntity1.getCount() + 1);
                dbEntity.setUrls(dbEntity1.getUrls());
                dbEntity.setShortkey(dbEntity1.getShortkey());
                dbDao.save(dbEntity);

                GetResponse getResponse=new GetResponse();
                getResponse.setShortkey(shortKey);
                getResponse.setCode(200);
                return getResponse;

        }catch (Exception e){
            GetResponse getResponse=new GetResponse();
            getResponse.setCode(404);
            return getResponse;
        }

    }

    public CountResponse getCount(StoreUrlRequest storeUrlRequest){
        try {
            int shortKey = storeUrlRequest.getUrls().hashCode();
            DBEntity dbEntity = dbDao.findByShortkey(shortKey);
            CountResponse countResponse=new CountResponse();
            countResponse.setCode(200);
            countResponse.setCount(dbEntity.getCount());
            return countResponse;

        }catch (Exception e){
            CountResponse countResponse=new CountResponse();
            countResponse.setCode(400);
            countResponse.setCount(0);
            return countResponse;
        }
    }
    public List<ListResponse> getAllData(){
      //  DBEntity dbEntity=new DBEntity();
        List<DBEntity> list=dbDao.findAll();
        List<ListResponse> listResponse=new ArrayList<>();

        for (DBEntity dbEntity:list){
            ListResponse listResponse1=new ListResponse();
            listResponse1.setShortkey(dbEntity.getShortkey());
            listResponse1.setUrls(dbEntity.getUrls());
            listResponse1.setCount(dbEntity.getCount());

            listResponse.add(listResponse1);
        }
        return listResponse;
    }
}
