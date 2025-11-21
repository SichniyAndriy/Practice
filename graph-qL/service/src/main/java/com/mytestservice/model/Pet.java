package com.mytestservice.model;

public record Pet(
    String name,
    String color,
    Person owner,
    Person previousOwner,
    String description
) { }
