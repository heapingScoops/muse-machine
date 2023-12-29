package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.Services.CloudinaryService;
import com.techelevator.tenmo.Services.DALLEService;
import com.techelevator.tenmo.Services.SummaryService;
import com.techelevator.tenmo.dao.PoemDao;
import com.techelevator.tenmo.model.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
public class PoemController {

    CloudinaryService cloudinaryService = new CloudinaryService();
    SummaryService summaryService = new SummaryService();
    DALLEService dalleService = new DALLEService();
    @Autowired
    PoemDao poemDao;

//    @PostMapping(path = "uplaod-image")
//    public void getPoemById(@PathVariable int id){
//        return poemDao.getPoemById(id);
//    }

    //gets the entire poem object by poem id
    @GetMapping(path = "poems/{id}")
    public Poem getPoemById(@PathVariable int id){
        return poemDao.getPoemById(id);
    }

    //gets the summary by poem id
    @GetMapping(path = "summary/poems/{id}")
    public String getSummaryByPoemId(@PathVariable int id){
        Poem poem = poemDao.getPoemById(id);
        return summaryService.fetchPoemSummary(poem);
    }

    //gets the associated image by poem id
    @GetMapping(path = "image/poems/{id}")
    public String getImageByPoemId(@PathVariable int id) throws IOException {
        Poem poem = poemDao.getPoemById(id);
        String summary =  summaryService.fetchPoemSummary(poem);

        //pass temporary url to cloudinary
        String dalleBlobUrl = dalleService.fetchImage(summary);

        String cloudinaryUrl = cloudinaryService.uploadTest(dalleBlobUrl);

        return cloudinaryUrl;
    }



}
