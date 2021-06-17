package com.example.githubRepoDetails.webClient;

import com.example.githubRepoDetails.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GithubWebClient {

    SearchRepoResponse searchRepoResponse;

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.api.baseurl}")
    private String githubBaseUrl;

    public SearchRepoResp getRepoList(String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = githubBaseUrl+"/search/repositories?q={param}";
        SearchRepoResp searchRepoResponses = restTemplate.getForObject(url, SearchRepoResp.class, param);


        return searchRepoResponses;
    }

    public List<CommitList> getCommitList(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = githubBaseUrl+"/repos/{owner}/{repo}/commits";

        Map<String, String> vars = new HashMap<>();
        vars.put("owner", owner);
        vars.put("repo", repo);
        CommitList[] commitLists = restTemplate.getForObject(url, CommitList[].class, vars);

        return Arrays.asList(commitLists);
    }



    public List<Contributor> getContributorsList(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = githubBaseUrl+"/repos/{owner}/{repo}/contributors";

        Map<String, String> vars = new HashMap<>();
        vars.put("owner", owner);
        vars.put("repo", repo);
        Contributor[] contributorsList = restTemplate.getForObject(url, Contributor[].class, vars);

        return Arrays.asList(contributorsList);
    }
}
