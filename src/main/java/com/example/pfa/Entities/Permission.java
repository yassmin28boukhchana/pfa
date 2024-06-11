package com.example.pfa.Entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    Admin_READ("admin:read"),
    Admin_UPDATE("admin:update"),
    Admin_CREATE("admin:create"),
    Admin_DELETE("admin:delete"),


    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete");

    @Getter
    private final String permission;

}