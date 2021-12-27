package com.example.lab2.data;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "things")
public class Thing {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "keywords")
    private String keywordLine;


    public Thing(UUID userId, String name, Keyword[] keywords) {
        this.userId = String.valueOf(userId);
        this.name = name;

        for (int i = 0; i < keywords.length; i++)
        {
            if (i == 0)
            {
                this.keywordLine = "";
                this.keywordLine += keywords[i];
            }
            else
            {
                this.keywordLine += ", " + keywords[i];
            }
        }
    }

    public Thing() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUserId() {
        return UUID.fromString(userId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywordLine() {
        return keywordLine;
    }

    public void setKeywordLine(String keywordLine) {
        this.keywordLine = keywordLine;
    }

    public Keyword[] getKeywords()
    {
        String[] keywordsToConvert = this.keywordLine.split(", ");
        Keyword[] keywords = new Keyword[keywordsToConvert.length];

        for (int i = 0; i < keywords.length; i++)
        {
            keywords[i] = new Keyword(keywordsToConvert[i]);
        }
        return keywords;
    }
}
