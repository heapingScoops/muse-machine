package com.techelevator.tenmo.Services;


import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import com.techelevator.tenmo.model.CloudinaryDto;
import io.github.cdimascio.dotenv.Dotenv;
import org.cloudinary.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CloudinaryService {

    //uses dotenv-java library to load the environment variables into this object
//    Dotenv dotenv = Dotenv.load();
//    String CLOUDINARY_API_KEY = dotenv.get("CLOUDINARY_API_KEY");
//    String CLOUDINARY_API_SECRET = dotenv.get("CLOUDINARY_API_SECRET");

    //Use System.getenv() to retrieve env variables in AWS
    String CLOUDINARY_API_KEY = System.getenv("CLOUDINARY_API_KEY");
    String CLOUDINARY_API_SECRET = System.getenv("CLOUDINARY_API_SECRET");


    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "djzcnmn12",
            "api_key", CLOUDINARY_API_KEY,
            "api_secret", CLOUDINARY_API_SECRET)
    );


    private RestTemplate restTemplate = new RestTemplate();

    public String uploadTest(String dalleBlob) throws IOException {
        try{
            //parameters I want to send in
            Map params1 = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true
            );

            //response comes in a Hashmap
            Map cloudinaryResponse = cloudinary.uploader().upload(dalleBlob, params1);

            //for some reason it's not reading the secureUrl Value as a String, so I have to do .toString (shrug)
            String cloudinaryUrl = cloudinaryResponse.get("secure_url").toString();

            return cloudinaryUrl;
        }
        catch(IOException e){
            return e.getMessage();
        }
        catch(Exception e){
            return e.getMessage();
        }
    }



}
