package com.distribuida.appbooks.clients;

import com.distribuida.appbooks.dtos.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorRestClient {

    private final RestTemplate restTemplate;
    private final String authorsUrl;

    @Autowired
    public AuthorRestClient(RestTemplate restTemplate, @Value("${app.authors.url}") String authorsUrl) {
        this.restTemplate = restTemplate;
        this.authorsUrl = authorsUrl;
    }

    /*
    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.authors.url}")
    private String authorsUrl;

     */

    public AuthorDto findById(Integer id) {
        return restTemplate.getForObject(authorsUrl + "/authors/" + id, AuthorDto.class);
    }

}