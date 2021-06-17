package com.example.githubRepoDetails.Service;

import com.example.githubRepoDetails.dto.CommitList;
import com.example.githubRepoDetails.dto.CommitProjectionDetails;
import com.example.githubRepoDetails.dto.Contributor;
import com.example.githubRepoDetails.webClient.GithubWebClient;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubRepoService {

    @Autowired
    GithubWebClient githubWebClient;

    @Value("${commit.limit}")
    private int commitLimit;

    public List<CommitList> commitList(String owner, String repo){
        List<CommitList> commitList = githubWebClient.getCommitList(owner, repo);
        return commitList;
    }

    public List<Contributor> usersCommits(String owner, String repo){
        List<Contributor> contributors = githubWebClient.getContributorsList(owner, repo);
        List<CommitList> sortedCommitList = getCommitWithLimit(owner, repo);

        contributors.forEach(contributor -> {
            long userCommitCount = sortedCommitList.stream().filter(c -> c.getCommitter().getLogin().equals(contributor.getLogin())).count();
            contributor.setNumberCommit(userCommitCount);
        });
        return contributors;
    }

    public CommitProjectionDetails commitsProjection(String owner, String repo){
        List<CommitList> sortedCommitList = getCommitWithLimit(owner, repo);

        CommitProjectionDetails commitProjectionDetails = new CommitProjectionDetails();
        commitProjectionDetails.setMaxDate(sortedCommitList.get(0).getCommit().getCommitter().getDate());
        commitProjectionDetails.setMinDate(sortedCommitList.get(sortedCommitList.size()-1).getCommit().getCommitter().getDate());
        commitProjectionDetails.setNumCommit(sortedCommitList.size());

        long differenceInTime = commitProjectionDetails.getMaxDate().getTime() - commitProjectionDetails.getMinDate().getTime();
        long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
        commitProjectionDetails.setDays(differenceInDays);

        return commitProjectionDetails;
    }

    public List<CommitList> getCommitWithLimit(String owner, String repo){
        List<CommitList> commitList = commitList(owner, repo);
        List<CommitList> sortedCommitList = commitList.stream().filter(c -> null != c.getCommitter()).limit(commitLimit).collect(Collectors.toList());
        return sortedCommitList;
    }
}
