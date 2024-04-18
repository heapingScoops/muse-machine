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

    @GetMapping(path = "heartbeat")
    public String heartbeat(){
        String heartbeat="babump";
        System.out.println("babumppp");
        return heartbeat;
    }

    //gets the entire poem object by poem id
    @GetMapping(path = "poems/{id}")
    public Poem getPoemById(@PathVariable int id){
        return poemDao.getPoemById(id);
    }

    //gets the summary by poem id
    @GetMapping(path = "poems/summary/{id}")
    public String getSummaryByPoemId(@PathVariable int id){
        Poem poem = poemDao.getPoemById(id);
        return summaryService.fetchPoemSummary(poem);
    }

    //gets the associated image by poem id
    @GetMapping(path = "poems/image/{id}")
    public Creation getImageByPoemId(@PathVariable int id, Principal principal) throws IOException {
        //obtain current userId
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        //get poem object from database
        Poem poem = poemDao.getPoemById(id);

        //fetch summary from Cohere
//        String summary =  summaryService.fetchPoemSummary(poem);
//        System.out.println("summary created");

        //TEMPORARY - substring dalle
        String poemText;
        if (poem.getPoem().length() > 3995){
            poemText = poem.getPoem().substring(0,3994);
        } else{
            poemText = poem.getPoem();
        }

        //call DallE service to create the image based on the summary

            //FOR REAL: below will get an actual image
//            String dalleBlobUrl = dalleService.fetchImage(summary);
            String dalleBlobUrl = dalleService.fetchImage(poemText);
            System.out.println("image created");

            //FOR TESTING: below will just do this image
            //String dalleBlobUrl = "https://grammarist.com/wp-content/uploads/httpsgrammarist.comhomophonesbut-vs-butt-1024x478.png";

        //pass temporary url to cloudinary to save and return permanent url
        String cloudinaryUrl = cloudinaryService.uploadTest(dalleBlobUrl);
        System.out.println("uploaded to cloudinary");

        //add new creation to database, and return creation object to client
        return creationDao.newCreation(cloudinaryUrl, userId, poem.getPoemId());
    }

    @GetMapping(path = "creations")
    public List<Creation> getCreations(Principal principal){
        //obtain current userId
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        //get creations
        return creationDao.fetchCreationsByUserId(userId);
    }

    //a method that prints entire database of creations to the console in easy format
    //for copy/pasting into .sql file to rebuild the database and keep all existing creations
    @GetMapping(path = "sql-backup")
    public void sqlBackup(Principal principal){
        creationDao.sqlBackup();
    }



}
