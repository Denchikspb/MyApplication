package com.cherepanov.myapplication.model;

import java.util.UUID;

/**
 * Created by Денис on 29.09.2017.
 */

public class Remind {
    private UUID id;
    private String title;
    private String description;

    public Remind(String title, String description) {
        this.title = title;
        this.description = description;
        this.id = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }
}
