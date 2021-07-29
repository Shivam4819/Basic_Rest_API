package com.example.demo.controller;

import com.example.demo.request.StoreUrlRequest;
import com.example.demo.response.CountResponse;
import com.example.demo.response.GetResponse;
import com.example.demo.response.ListResponse;
import com.example.demo.response.StoreUrlResponse;
import com.example.demo.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.utils.RestUrls.*;

@RestController
public class Controller {

    @Autowired
    private UrlsService urlsService;

    // this api store the url in database
    @GetMapping(STORE_URL)
    public StoreUrlResponse storeurl(@RequestParam(name = "url") String url) {
        StoreUrlRequest storeUrlRequest = new StoreUrlRequest();
        storeUrlRequest.setUrls(url);
        return urlsService.storeDataToDb(storeUrlRequest);

    }

    // this api increment the count for url and return the short key as response
    @GetMapping(GET)
    public GetResponse get(@RequestParam(name = "url") String url) {
        StoreUrlRequest storeUrlRequest = new StoreUrlRequest();
        storeUrlRequest.setUrls(url);
        return urlsService.incrementCount(storeUrlRequest);
    }

    //this api return the count for the url as the response
    @GetMapping(COUNT)
    public CountResponse count(@RequestParam String url) {
        StoreUrlRequest storeUrlRequest = new StoreUrlRequest();
        storeUrlRequest.setUrls(url);
        return urlsService.getCount(storeUrlRequest);
    }

    //this api return all the data from database in JSON form
    @GetMapping(LIST)
    public List<ListResponse> list() {
        return urlsService.getAllData();
    }
}
