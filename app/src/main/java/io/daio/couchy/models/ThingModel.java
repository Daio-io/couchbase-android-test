package io.daio.couchy.models;

public class ThingModel {

    private String value;
    private String key;

    public ThingModel(String _key, String _value) {
        this.key = _key;
        this.value = _value;
    }

    public String getKey(){
        return this.key;
    }

    public String getValue(){
        return this.value;
    }

}
