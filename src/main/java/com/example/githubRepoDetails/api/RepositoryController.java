package com.example.githubRepoDetails.api;

import com.example.githubRepoDetails.Service.GithubRepoService;
import com.example.githubRepoDetails.dto.*;
import com.example.githubRepoDetails.webClient.GithubWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class RepositoryController {

    @Autowired
    GithubWebClient githubWebClient;

    @Autowired
    GithubRepoService githubRepoService;

    @GetMapping("search/repositories/{param}")
    public ResponseEntity<?> searchRepoController(@PathVariable String param){
        //SearchRepoResponse searchRepoResponse = githubWebClient.getRepoService(repo);

        SearchRepoResp response = githubWebClient.getRepoList(param);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/repo/commit/{owner}/{repo}")
    public ResponseEntity<?> repoCommitController(@PathVariable String owner, @PathVariable String repo){

        List<CommitList> response = githubRepoService.commitList(owner, repo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/repo/committers/{owner}/{repo}")
    public ResponseEntity<?> repoContributorsController(@PathVariable String owner, @PathVariable String repo){

        List<Contributor> response = githubWebClient.getContributorsList(owner, repo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/repo/committers/commits/{owner}/{repo}")
    public ResponseEntity<?> repoContributorsCommitController(@PathVariable String owner, @PathVariable String repo){

        List<Contributor> response = githubRepoService.usersCommits(owner, repo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/repo/projection/commits/{owner}/{repo}")
    public ResponseEntity<?> repoCommitProjectionController(@PathVariable String owner, @PathVariable String repo){

        CommitProjectionDetails response = githubRepoService.commitsProjection(owner, repo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
