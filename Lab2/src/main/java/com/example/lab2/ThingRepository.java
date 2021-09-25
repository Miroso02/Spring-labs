package com.example.lab2;

import com.example.lab2.data.Keyword;
import com.example.lab2.data.Thing;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ThingRepository {
    private final Thing[] things = {
            new Thing("My hopes", new Keyword[]{
                    new Keyword("Mine"),
                    new Keyword("Never_forget"),
                    new Keyword("Hopes"),
            }),
            new Thing("My dreams", new Keyword[]{
                    new Keyword("Mine"),
                    new Keyword("Never_forget"),
                    new Keyword("Dreams"),
            }),
            new Thing("My sanity", new Keyword[]{
                    new Keyword("Mine"),
                    new Keyword("Never_goes_back"),
                    new Keyword("Sanity"),
            })
    };

    public List<Thing> getAllThings() {
        return List.of(things);
    }

    public List<Thing> findThingByKeywords(Keyword[] keywords) {
        return Arrays.stream(things)
                .filter(thing -> List.of(thing.getKeywords()).containsAll(List.of(keywords)))
                .collect(Collectors.toList());
    }
}
