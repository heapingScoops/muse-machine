package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.Services.CloudinaryService;
import com.techelevator.tenmo.Services.DALLEService;
import com.techelevator.tenmo.Services.SummaryService;
import com.techelevator.tenmo.dao.CreationDao;
import com.techelevator.tenmo.dao.PoemDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Creation;
import com.techelevator.tenmo.model.Poem;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class PoemController {

    CloudinaryService cloudinaryService = new CloudinaryService();
    SummaryService summaryService = new SummaryService();
    DALLEService dalleService = new DALLEService();
    @Autowired
    PoemDao poemDao;
    @Autowired
    UserDao userDao;

    @Autowired
    CreationDao creationDao;

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
    public String getImageByPoemId(@PathVariable int id, Principal principal) throws IOException {



        //obtain current userId
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        Poem poem = poemDao.getPoemById(id);
        String summary =  summaryService.fetchPoemSummary(poem);

        //call DallE service to create the image based on the summary
        String dalleBlobUrl = dalleService.fetchImage(summary);
        //String dalleBlobUrl = "https://grammarist.com/wp-content/uploads/httpsgrammarist.comhomophonesbut-vs-butt-1024x478.png";

        //pass temporary url to cloudinary to save and return permanent url
        String cloudinaryUrl = cloudinaryService.uploadTest(dalleBlobUrl);

        //make new creation
        creationDao.newCreation(cloudinaryUrl, userId, poem.getPoemId());

        return cloudinaryUrl;
    }

    @GetMapping(path = "creations")
    public List<Creation> getCreations(Principal principal){
        //obtain current userId
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        //get creations
        return creationDao.fetchCreationsByUserId(userId);
    }



}
