package com.example.lab2.data;

public class AddThingRequest {
    private String name;
    private String description;
    private String[] keywords;

    public AddThingRequest() {}

    public AddThingRequest(String name, String description, String[] keywords) {
        this.name = name;
        this.description = description;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }
}
