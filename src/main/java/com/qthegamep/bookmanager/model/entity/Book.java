package com.qthegamep.bookmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is an entity.
 * There is an no args and all args constructors, getters and setters for fields,
 * override equals, hashcode and toString methods.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;

    private String name;

    private String author;

    private int printYear;

    private boolean isRead;
}
