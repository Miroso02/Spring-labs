package com.example.lab2.data;

import java.util.UUID;

public class Thing {
    private UUID id = UUID.randomUUID();

    public UUID getUserId() {
        return userId;
    }

    private final UUID userId;
    private String name;
    private Keyword[] keywords;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Thing(UUID userId, String name, Keyword[] keywords) {
        this.userId = userId;
        this.name = name;
        this.keywords = keywords;
    }

    public Keyword[] getKeywords() {
        return keywords;
    }

    public void setKeywords(Keyword[] keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
