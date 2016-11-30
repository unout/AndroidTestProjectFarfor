package com.example.user.testproject11.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import io.realm.RealmList;
import io.realm.RealmObject;

@Root(name = "offers", strict = false)
public class Offers extends RealmObject {
    @ElementList(name = "offer", inline = true)
    private RealmList<Offer> offer;

    public RealmList<Offer> getOffer() {
        return offer;
    }
}
