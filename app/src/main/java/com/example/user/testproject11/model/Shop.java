package com.example.user.testproject11.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import io.realm.RealmObject;

@Root(name = "shop", strict = false)
public class Shop extends RealmObject {

    @Element
    private Offers offers;
    @Element
    private Categories categories;

    public Offers getOffers() {
        return offers;
    }
    public Categories getCategories() {
        return categories;
    }
}