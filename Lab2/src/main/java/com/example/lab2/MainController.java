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

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    ThingRepository repository;

    @GetMapping("/")
    public String getAllThings(Model model) {
        model.addAttribute("things", repository.getAllThings());
        return "thing_list";
    }

    @GetMapping("/{id}")
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
