package com.example.lab2;

import com.example.lab2.data.ContactInfo;
import com.example.lab2.data.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private final ArrayList<User> users = new ArrayList<>(List.of(
            new User(new ContactInfo("+380123123123", ""), "User1"),
            new User(new ContactInfo("+386666666666", "test@gmail.com"), "User2")
    ));

    public User getUser(UUID id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User getUser(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public List<User> getAllUsers() {
         return users;
    }
}
