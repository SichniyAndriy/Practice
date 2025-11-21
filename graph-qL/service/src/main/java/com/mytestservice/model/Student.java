package com.mytestservice.model;

public record Student(
    String firstName,
    String lastName,
    String city,
    String address,
    int age
) { }
