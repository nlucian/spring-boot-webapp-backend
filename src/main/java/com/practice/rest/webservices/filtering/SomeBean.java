package com.practice.rest.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

//static filtering example
public class SomeBean {

    //will be ignored/filtered when this bean is returned in a request
    // you can also use @JsonIgnoreProperties at class level
    @JsonIgnore
    private String field1;

    private String field2;

    private String field3;

    public SomeBean() {
    }

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
