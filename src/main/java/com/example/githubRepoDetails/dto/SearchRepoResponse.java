package com.example.githubRepoDetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @JsonIgnoreProperties @NoArgsConstructor
public class SearchRepoResponse implements Serializable {
    Owner owner;

    @JsonProperty(value ="node_id")
    String nodeId;

    long id;
    String name, description;

    @JsonProperty(value ="full_name")
    String fullName;

    @JsonProperty(value ="contributors_url")
    String contributorsUrl;

}
