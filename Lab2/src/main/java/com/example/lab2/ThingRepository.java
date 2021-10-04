package com.example.lab2;

import com.example.lab2.data.Keyword;
import com.example.lab2.data.Thing;
import com.example.lab2.data.ThingDescription;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ThingRepository {
    private final ArrayList<Thing> things = new ArrayList<>();
    private final ArrayList<ThingDescription> descriptions = new ArrayList<>();

    public List<Thing> getAllThings() {
        return things;
    }

    public ThingDescription getThingDescription(UUID id) {
        Optional<ThingDescription> optional = descriptions.stream()
                .filter(thing -> thing.getThingId().equals(id))
                .findFirst();
        return optional.orElse(null);
    }
    public Thing getThing(UUID id) {
        return things.stream().filter(thing -> thing.getId().equals(id)).findFirst().orElse(null);
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
