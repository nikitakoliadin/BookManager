package com.qthegamep.bookmanager.entity;

import lombok.Data;

/**
 * This class is an entity.
 * There is an no args constructor, getters and setters for fields, override equals, hashcode and toString methods.
 */
@Data
public class Book {

    private int id;

    private String name;

    private String author;

    private int printYear;

    private boolean isRead;
}
