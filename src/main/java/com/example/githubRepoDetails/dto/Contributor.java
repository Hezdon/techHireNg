package com.example.githubRepoDetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@JsonIgnoreProperties
@NoArgsConstructor
public class Contributor extends Owner {

            int contributions;
            long numberCommit;
}
