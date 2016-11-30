package com.example.user.testproject11.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import io.realm.RealmList;
import io.realm.RealmObject;

@Root(name = "offer", strict = false)
public class Offer extends RealmObject {

    @ElementList(name = "param", required = false, inline = true)
    private RealmList<Param> param;
    @Attribute(name = "id")
    private Integer id;
    @Element(name = "categoryId")
    private Integer categoryId;
    @Element(name = "name")
    private String name;
    @Element(name = "description", required = false)
    private String description;
    @Element(name = "picture", required = false)
    private String picture;
    @Element(name = "price")
    private String price;
    @Element(name = "url")
    private String url;

    public RealmList<Param> getParams() {
        return param;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public String getUrl() {
        return url;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPicture() {
        return picture;
    }
    public String getPrice() {
        return price;
    }

}