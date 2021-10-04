package com.example.lab2;

import com.example.lab2.data.AddThingRequest;
import com.example.lab2.data.Keyword;
import com.example.lab2.data.Thing;
import com.example.lab2.data.ThingDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {
    @Autowired
    ThingRepository repository;

    @GetMapping("/")
    public String getAllThings(@PathParam("keywords") String keywords, Model model) {
        List<Thing> things;
        if (keywords == null || keywords.isEmpty()) {
            things = repository.getAllThings();
        }
        else {
            List<Keyword> keywords1 = Stream.of(keywords.split(","))
                    .map(Keyword::new)
                    .collect(Collectors.toList());
            things = repository.findThingsByKeywords(keywords1);
        }
        model.addAttribute("things", things);
        return "thing_list";
    }

    @GetMapping("/search")
    public String searchThings(@PathParam("keywords") String keywords, Model model) {
        List<Keyword> keywords1 = Stream.of(keywords.split(",")).map(Keyword::new).collect(Collectors.toList());
        List<Thing> things = repository.findThingsByKeywords(keywords1);
        model.addAttribute("things", things);
        return "thing_list";
    }

    @GetMapping("/thing/{id}")
    public String getThingInfo(Model model, @PathVariable String id) {
        ThingDescription description = repository.getThingDescription(UUID.fromString(id));
        model.addAttribute("description", description);
        return "thing_detailed";
    }

    @GetMapping(value = "/addThing")
    public String addThingPage() {
        return "thing_add";
    }

    @PostMapping(value = "/addThing", consumes = "application/json")
    public void addThing(@RequestBody AddThingRequest request) {
        Thing thing = new Thing(
                request.getName(),
                Arrays.stream(request.getKeywords())
                        .map(Keyword::new)
                        .toArray(Keyword[]::new)
        );
        ThingDescription description = new ThingDescription(
                thing.getId(),
                thing.getName(),
                request.getDescription(),
                new Date(),
                false
        );
        repository.addThing(thing, description);
    }
}
