package com.example.user.testproject11.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import io.realm.RealmList;
import io.realm.RealmObject;

@Root(name = "categories", strict = false)
public class Categories extends RealmObject {

    @ElementList(name = "category", inline = true)
    private RealmList<Category> category;

    public RealmList<Category> getCategory() {
        return category;
    }

}
