package com.ims.tasol.ims;

/**
 * Created by tasol on 10/3/17.
 */

public class FormDataPojo {
    public String name;
    public String type;
    public String required;
    public String caption;
    public String value;

    public FormDataPojo(String name, String type, String required, String caption, String value) {
        this.name = name;
        this.type = type;
        this.required = required;
        this.caption = caption;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRequired() {
        return required;
    }

    public String getCaption() {
        return caption;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
