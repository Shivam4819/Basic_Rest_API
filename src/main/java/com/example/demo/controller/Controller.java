package com.example.demo.controller;

import com.example.demo.request.StoreUrlRequest;
import com.example.demo.response.CountResponse;
import com.example.demo.response.GetResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UrlsService urlsService;

    @PostMapping("/storeurl")
    public void storeurl(@RequestBody StoreUrlRequest storeUrlRequest){
        System.out.println("hi buddy");
        urlsService.storeDataToDb(storeUrlRequest);

    }
    @PostMapping("/geturl")
    public GetResponse get(@RequestBody StoreUrlRequest storeUrlRequest){
        System.out.println("hi buddy");

        return urlsService.incrementCount(storeUrlRequest);
    }
    @PostMapping("/count")
    public CountResponse count(@RequestBody StoreUrlRequest storeUrlRequest){
        System.out.println("hi buddy");

       return urlsService.getCount(storeUrlRequest);
    }

    @GetMapping("/list")
    public List<ListResponse> list(){
        System.out.println("hi buddy");

       return urlsService.getAllData();
    }
}
