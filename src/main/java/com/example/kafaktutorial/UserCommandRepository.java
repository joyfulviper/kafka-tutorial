package com.example.kafaktutorial;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserCommandRepository {
    Set<User> set = new HashSet<>();

    public void add(User user) {
        set.add(user);
    }
    public boolean remove(User user) {
        return set.remove(user);
    }
}