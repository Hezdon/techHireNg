package com.example.githubRepoDetails.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @JsonIgnoreProperties @NoArgsConstructor
public class Owner {

    long id;

    @JsonProperty(value = "html_url")
    String htmlUrl;

    @JsonProperty(value = "organizations_url")
    String organizationsUrl;

    @JsonProperty(value ="repos_url")
    String reposUrl;

    String type, url, login;
    @JsonProperty(value ="avatar_url")
    String avatarUrl;


}
