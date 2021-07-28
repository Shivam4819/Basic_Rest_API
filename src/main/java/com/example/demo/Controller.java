package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private AllService allService;

    @PostMapping("/storeurl")
    public void storeurl(@RequestBody StoreUrlRequest storeUrlRequest){
        System.out.println("hi buddy");
        allService.storeDataToDb(storeUrlRequest);

    }
    @PostMapping("/geturl")
    public GetResponse get(@RequestBody StoreUrlRequest storeUrlRequest){
        System.out.println("hi buddy");

        return allService.incrementCount(storeUrlRequest);
    }
    @PostMapping("/count")
    public void count(@RequestBody StoreUrlRequest storeUrlRequest){
        System.out.println("hi buddy");

        allService.getCount(storeUrlRequest);
    }
}
