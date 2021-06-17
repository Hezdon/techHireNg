package com.example.githubRepoDetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties
@NoArgsConstructor
public class CommitProjectionDetails {
    int numCommit;
    Date minDate, maxDate;
    long days;
}
