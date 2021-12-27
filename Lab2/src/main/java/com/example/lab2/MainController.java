package com.example.lab2;

import com.example.lab2.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    ThingTestRepository thingTestRepository;

    User user;

    @ResponseBody
    @GetMapping("/testMap")
    public Thing getThing(@RequestParam int id){
        return thingTestRepository.findById(id).orElseGet(() -> { throw new RuntimeException("no thing"); });
    }

    @ResponseBody
    @PostMapping(value = "/testMap", consumes = "application/json")
    public String saveThing(@RequestBody Thing thing)
    {
        thingTestRepository.save(thing);
        return "saved successfully";
    }

    @PutMapping(value = "replaceThing", consumes = "application/json")
    public void replaceMessage(@RequestParam int id, @RequestBody Thing thing)
    {
        Thing newThing = new Thing();
        newThing.setId(id);
        newThing.setName(thing.getName());
        newThing.setKeywordLine(thing.getKeywordLine());

        thingTestRepository.deleteById(id);
        thingTestRepository.save(newThing);
    }

    @DeleteMapping("/deleteById")
    public void deleteThing(@RequestParam int id)
    {
        thingTestRepository.deleteById(id);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userRepository.getAllUsers());
        return "index";
    }

    @GetMapping("/list")
    public String getAllThings(
            @PathParam("keywords") String keywords,
            @PathParam("userId") UUID userId,
            Model model
    ) {
        if (userId != null) {
            user = userRepository.getUser(userId);
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
        ThingDescription description = thingRepository.getThingDescription(Integer.parseInt(id));
        model.addAttribute("description", description);
        Thing thing = thingRepository.getThing(Integer.parseInt(id));
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
