package com.example.lab2;

import com.example.lab2.data.*;
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
    ThingRepository thingRepository;
    @Autowired
    UserRepository userRepository;
    User user;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userRepository.getAllUsers());
        return "index";
    }

    @GetMapping("/list")
    public String getAllThings(
            @PathParam("keywords") String keywords,
            @PathParam("userId") String userId,
            Model model
    ) {
        if (userId != null) {
            user = userRepository.getUser(UUID.fromString(userId));
        }
        List<Thing> things;
        if (keywords == null || keywords.isEmpty()) {
            things = thingRepository.getAllThings();
        }
        else {
            List<Keyword> keywords1 = Stream.of(keywords.split(","))
                    .map(Keyword::new)
                    .collect(Collectors.toList());
            things = thingRepository.findThingsByKeywords(keywords1);
        }
        model.addAttribute("things", things);
        return "thing_list";
    }

    @GetMapping("/thing/{id}")
    public String getThingInfo(Model model, @PathVariable String id) {
        ThingDescription description = thingRepository.getThingDescription(UUID.fromString(id));
        model.addAttribute("description", description);
        Thing thing = thingRepository.getThing(UUID.fromString(id));
        User thingUser = userRepository.getUser(thing.getUserId());
        model.addAttribute("contactInfo", thingUser.getContactInfo());
        return "thing_detailed";
    }

    @GetMapping(value = "/addThing")
    public String addThingPage() {
        return "thing_add";
    }

    @PostMapping(value = "/addThing", consumes = "application/json")
    public void addThing(@RequestBody AddThingRequest request) {
        Thing thing = new Thing(
                user.getId(), request.getName(),
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
        thingRepository.addThing(thing, description);
    }
}
