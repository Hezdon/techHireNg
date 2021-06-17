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

    @Value("${commit.limit}")
    private int commitLimit;

    public SearchRepoResp getRepoList(String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = githubBaseUrl+"/search/repositories?q={param}&per_page="+commitLimit;
        SearchRepoResp searchRepoResponses = restTemplate.getForObject(url, SearchRepoResp.class, param);


        return searchRepoResponses;
    }

    public List<CommitList> getCommitList(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = githubBaseUrl+"/repos/{owner}/{repo}/commits?per_page="+commitLimit;

        Map<String, String> vars = new HashMap<>();
        vars.put("owner", owner);
        vars.put("repo", repo);
        CommitList[] commitLists = restTemplate.getForObject(url, CommitList[].class, vars);

        return Arrays.asList(commitLists);
    }



    public List<Contributor> getContributorsList(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = githubBaseUrl+"/repos/{owner}/{repo}/contributors?per_page=100";

        Map<String, String> vars = new HashMap<>();
        vars.put("owner", owner);
        vars.put("repo", repo);
        Contributor[] contributorsList = restTemplate.getForObject(url, Contributor[].class, vars);

        return Arrays.asList(contributorsList);
    }
    public List<Contributor> getCollaborators(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = githubBaseUrl+"/repos/{owner}/{repo}/collaborators";

        Map<String, String> vars = new HashMap<>();
        vars.put("owner", owner);
        vars.put("repo", repo);
        Contributor[] contributorsList = restTemplate.getForObject(url, Contributor[].class, vars);

        return Arrays.asList(contributorsList);
    }
}
