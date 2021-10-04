package com.example.lab2;

import com.example.lab2.data.Keyword;
import com.example.lab2.data.Thing;
import com.example.lab2.data.ThingDescription;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ThingRepository {
    private final ArrayList<Thing> things = new ArrayList<>(List.of(
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
    ));
    private final ArrayList<ThingDescription> descriptions = new ArrayList<>(List.of(
            new ThingDescription(
                    things.get(0).getId(),
                    things.get(0).getName(),
                    "My unfulfilled hopes that I left behind",
                    new Date(),
                    false
            ),
            new ThingDescription(
                    things.get(1).getId(),
                    things.get(1).getName(),
                    "My precious dreams that I left behind",
                    new Date(),
                    false
            ),
            new ThingDescription(
                    things.get(2).getId(),
                    things.get(2).getName(),
                    "My sanity which helped me to survive",
                    new Date(),
                    false
            )
    ));

    public List<Thing> getAllThings() {
        return things;
    }

    public ThingDescription getThingDescription(UUID id) {
        Optional<ThingDescription> optional = descriptions.stream()
                .filter(thing -> thing.getThingId().equals(id))
                .findFirst();
        return optional.orElse(null);
    }

    public void addThing(Thing thing, ThingDescription description) {
        things.add(thing);
        descriptions.add(description);
    }

    public List<Thing> findThingsByKeywords(List<Keyword> keywords) {
        return things.stream()
                .filter(thing -> List.of(thing.getKeywords()).containsAll(keywords))
                .collect(Collectors.toList());
    }
}
