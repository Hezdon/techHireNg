package com.example.githubRepoDetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties
@NoArgsConstructor
public class SearchRepoResp {

    @JsonProperty(value ="total_count")
    long totalCount;

    List<SearchRepoResponse> items;
}
