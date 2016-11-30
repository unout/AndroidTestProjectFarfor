package com.example.user.testproject11.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import io.realm.RealmObject;


@Root(name = "yml_catalog", strict = false)
public class Catalog extends RealmObject {

    @Element(name = "shop")
    private Shop shop;
    @Attribute(name = "date", required = false)
    private String date;

    public String getDate() {
        return date;
    }
    public Shop getShop() {
        return shop;
    }
}