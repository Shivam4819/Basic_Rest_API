package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllService {
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
            System.out.println("no data");
            GetResponse getResponse=new GetResponse();
            getResponse.setCode(404);
            return getResponse;
        }

    }

    public void getCount(StoreUrlRequest storeUrlRequest){

    }
}
