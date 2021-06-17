package com.example.githubRepoDetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
@NoArgsConstructor
public class CommitList implements Serializable {
    Owner committer;

    @JsonProperty(value ="html_url")
    String htmlUrl;

    Owner author;

    Commit commit;


}
