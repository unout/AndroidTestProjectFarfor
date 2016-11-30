package com.example.user.testproject11.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import io.realm.RealmObject;

@Root(name = "category", strict = false)
public class Category extends RealmObject {
    @Attribute(name = "id")
    private Integer id;
    @Text
    private String category;

    public Integer getId() {
        return id;
    }
    public String getContent() {
        return category;
    }

}