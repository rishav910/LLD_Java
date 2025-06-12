package com.rishav.store;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "Person")
public class DataModel {
    @Id
    private String id;

    @Field("name")
    private String name;

    public DataModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Setters and getters
    public String getID () {
        return this.id;
    }
}
