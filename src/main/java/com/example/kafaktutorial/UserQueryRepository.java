package com.example.kafaktutorial;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserQueryRepository {
    Set<User> set = new HashSet<>();

    public List<User> findAll() {
        return set.stream().toList();
    }

    public void add(User user) {
        set.add(user);
    }
}