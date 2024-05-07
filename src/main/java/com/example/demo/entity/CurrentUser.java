package com.example.demo.entity;

import java.util.concurrent.atomic.AtomicReference;
import java.time.LocalDate;

public class CurrentUser {
    
    private static final AtomicReference<CurrentUser> INSTANCE = new AtomicReference<>();
    private User user;

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        while (true) {
            CurrentUser current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new CurrentUser();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Method to create a fake user for testing
    public static void createFakeUser() {
        User aliceUser = new User("alice123", "pass1234", "alice123@example.com", "Alice", "Johnson", true, LocalDate.of(1995, 1, 12));

        CurrentUser.getInstance().setUser(aliceUser);
    }
}
