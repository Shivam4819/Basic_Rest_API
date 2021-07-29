package com.example.demo.service;

import com.example.demo.dao.DbDao;
import com.example.demo.entity.DBEntity;
import com.example.demo.request.StoreUrlRequest;
import com.example.demo.response.CountResponse;
import com.example.demo.response.GetResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.StoreUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlsService {
    @Autowired
    private DbDao dbDao;

    public StoreUrlResponse storeDataToDb(StoreUrlRequest storeUrlRequest) {
        //the url is getting split from = and stored in array
        String[] str = storeUrlRequest.getUrls().split("=");
        //the last value in array is changed to string
        String str1 = str[str.length - 1];
        try {
            DBEntity dbEntity = new DBEntity();
            // setting value in DBEntity object which will store the data to database

            dbEntity.setUrls(str1.toLowerCase());
            dbEntity.setShortkey(str1.toLowerCase().hashCode());
            dbEntity.setCount(0);
            dbDao.save(dbEntity);

            StoreUrlResponse storeUrlResponse=new StoreUrlResponse();
            storeUrlResponse.setMessage("data inserted");
            return storeUrlResponse;
        }catch (Exception exception){
            StoreUrlResponse storeUrlResponse=new StoreUrlResponse();
            storeUrlResponse.setMessage(exception.toString());
            return storeUrlResponse;
        }
    }

    public GetResponse incrementCount(StoreUrlRequest storeUrlRequest) {
        DBEntity dbEntity = new DBEntity();
        //the url is getting split from = and stored in array
        String[] str = storeUrlRequest.getUrls().split("=");
        //the last value in array is changed to string
        String str1 = str[str.length - 1];
        try {
            //converting url to lower case and generating hash
            int shortKey = str1.toLowerCase().hashCode();
            //searching the shortkey in database as it will be unique
            DBEntity dbEntity1 = dbDao.findByShortkey(shortKey);
            //updating the count value for the url
            dbEntity.setCount(dbEntity1.getCount() + 1);
            dbEntity.setUrls(dbEntity1.getUrls());
            dbEntity.setShortkey(dbEntity1.getShortkey());
            dbDao.save(dbEntity);

            // setting the response variable
            GetResponse getResponse = new GetResponse();
            getResponse.setShortkey(shortKey);
            //code 200 is for success
            getResponse.setCode(200);
            return getResponse;

        } catch (Exception e) {
            GetResponse getResponse = new GetResponse();
            //code 404 is for data not found
            getResponse.setCode(404);
            return getResponse;
        }

    }

    public CountResponse getCount(StoreUrlRequest storeUrlRequest) {

        //the url is getting split from = and stored in array
        String[] str = storeUrlRequest.getUrls().split("=");
        //the last value in array is changed to string
        String str1 = str[str.length - 1];
        try {
            //converting url to lower case and generating hash
            int shortKey = str1.toLowerCase().hashCode();
            //searching the short key in database
            DBEntity dbEntity = dbDao.findByShortkey(shortKey);
            //set count value in response
            CountResponse countResponse = new CountResponse();
            countResponse.setCode(200);
            countResponse.setCount(dbEntity.getCount());
            return countResponse;

        } catch (Exception e) {
            //set response in case data not found
            CountResponse countResponse = new CountResponse();
            countResponse.setCode(404);
            countResponse.setCount(0);
            return countResponse;
        }
    }

    public List<ListResponse> getAllData() {


        //fetching all data in list from database
        List<DBEntity> list = dbDao.findAll();
        //creating the response object list to send data
        List<ListResponse> listResponse = new ArrayList<>();


        for (DBEntity dbEntity : list) {
            //setting the data from list to response object
            ListResponse listResponse1 = new ListResponse();
            listResponse1.setShortkey(dbEntity.getShortkey());
            listResponse1.setUrls(dbEntity.getUrls());
            listResponse1.setCount(dbEntity.getCount());

            //adding all response in main response list
            listResponse.add(listResponse1);
        }
        return listResponse;
    }
}
