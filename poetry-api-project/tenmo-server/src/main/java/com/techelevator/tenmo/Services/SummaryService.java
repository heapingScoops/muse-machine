package com.techelevator.tenmo.Services;

import com.techelevator.tenmo.model.Poem;
import com.techelevator.tenmo.model.SummaryDto;
import com.techelevator.tenmo.model.SummaryResponse;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class SummaryService {
    private static final String baseUrl = "https://api.cohere.ai/v1/summarize";
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = "F86RUJbch0BjDA7by6IBXazYO6Qnnz2W2Ysm0QZK";



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



        return summaryText;

    }



    private HttpEntity<SummaryDto> makeSummaryEntity(SummaryDto summaryDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        headers.setContentType(MediaType.APPLICATION_JSON); //extra step 1
        HttpEntity<SummaryDto> entity = new HttpEntity<>(summaryDto, headers); //extra step 2
        return entity;
    }

}
