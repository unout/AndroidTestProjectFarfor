package com.example.user.testproject11.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

import io.realm.RealmObject;

@Element(name = "param")
public class Param extends RealmObject {

    @Attribute(name = "name")
    private String name;
    @Text()
    private String content;

    public String getName() {
        return name;
    }
    public String getContent() {
        return content;
    }

}
