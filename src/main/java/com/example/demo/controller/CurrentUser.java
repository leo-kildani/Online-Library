package com.example.demo.controller;

import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public @Data class CurrentUser {
    private User currentUser;
}
