package com.stack.service;

 import org.springframework.stereotype.Service;
 import org.springframework.beans.factory.annotation.Value;

@Service
public class ApplicationService {

    @Value("stack")
    private String projectName;

    @Value("Grzesiek Rachel")
    private String projectAuthor;

    @Value("heroku.com/TODO")
    private String projectWebsite;

    public ApplicationService() { }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectAuthor() {
        return projectAuthor;
    }

    public String getProjectWebsite() {
        return projectWebsite;
    }

}