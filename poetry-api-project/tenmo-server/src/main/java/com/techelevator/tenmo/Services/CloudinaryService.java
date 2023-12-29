package com.techelevator.tenmo.Services;


import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import com.techelevator.tenmo.model.CloudinaryDto;
import org.cloudinary.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class CloudinaryService {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "djzcnmn12",
            "api_key", "539717164663443",
            "api_secret", "a5_QoztPe6rP0GL6wZ3Km6ePAbc"));

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
