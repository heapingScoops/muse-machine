package com.techelevator.tenmo.Services;

import com.techelevator.tenmo.model.Poem;
import com.techelevator.tenmo.model.SummaryDto;
import com.techelevator.tenmo.model.SummaryResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class SummaryService {
//    Dotenv dotenv = Dotenv.load();
//    private String COHERE_AUTH_TOKEN = dotenv.get("COHERE_AUTH_TOKEN");

    //USE System.getenv() for AWS
    private String COHERE_AUTH_TOKEN = System.getenv("COHERE_AUTH_TOKEN");
    private static final String baseUrl = "https://api.cohere.ai/v1/summarize";
    private RestTemplate restTemplate = new RestTemplate();

    public String fetchPoemSummary(Poem poem){
        SummaryDto summary = new SummaryDto();
        summary.setText(poem.getPoem());
        SummaryResponse summaryResponse = null;

        try {
            ResponseEntity<SummaryResponse> response = restTemplate.exchange(baseUrl, HttpMethod.POST, makeSummaryEntity(summary), SummaryResponse.class);
            summaryResponse = response.getBody();
            //
        } catch (RestClientResponseException | ResourceAccessException e) {
            e.getRootCause();
        }

        String summaryText = summaryResponse.getSummary();
        System.out.println(summaryText);

        return summaryText;
    }

    private HttpEntity<SummaryDto> makeSummaryEntity(SummaryDto summaryDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(COHERE_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON); //extra step 1
        HttpEntity<SummaryDto> entity = new HttpEntity<>(summaryDto, headers); //extra step 2
        return entity;
    }
}
