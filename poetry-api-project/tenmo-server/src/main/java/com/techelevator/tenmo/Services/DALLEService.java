package com.techelevator.tenmo.Services;

import com.techelevator.tenmo.model.DalleDto;
import com.techelevator.tenmo.model.SummaryDto;
import com.techelevator.tenmo.model.SummaryResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DALLEService {

    //Use dotenv library to extract secret keys from .env file
//    Dotenv dotenv = Dotenv.load();
//    private String DALLE_AUTH_TOKEN = dotenv.get("DALLE_AUTH_TOKEN");

    //Use System.getenv() for AWS
    private String DALLE_AUTH_TOKEN = System.getenv("DALLE_AUTH_TOKEN");
    private static final String baseUrl = "https://api.openai.com/v1/images/generations";
    private RestTemplate restTemplate = new RestTemplate();

    public String fetchImage(String summary){
        DalleDto dalleDto = new DalleDto();
        dalleDto.setPrompt(summary);
        String url = null;

        try {
            Gson gson = new Gson();

            String dalleResponseString = restTemplate.postForObject(baseUrl, makeDalleDtoEntity(dalleDto), String.class);
            System.out.println(dalleResponseString);
            JsonObject dalleResponseObject = gson.fromJson(dalleResponseString, JsonObject.class);
            System.out.println(dalleResponseObject);


            JsonArray dalleResponseObjectData = dalleResponseObject.getAsJsonArray("data");
            JsonElement urlElement = dalleResponseObjectData.get(0);
            JsonObject urlObjecgt = urlElement.getAsJsonObject();
            url = urlObjecgt.get("url").getAsString();

        } catch (RestClientResponseException | ResourceAccessException e) {
            e.getRootCause();
        }
        return url;
    }


    private HttpEntity<DalleDto> makeDalleDtoEntity(DalleDto dalleDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(DALLE_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON); //extra step 1
        HttpEntity<DalleDto> entity = new HttpEntity<>(dalleDto, headers); //extra step 2
        return entity;
    }

}
