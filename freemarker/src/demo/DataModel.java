package demo;

import java.util.List;
import java.util.Map;

public class DataModel {

    private int id;
    private String name;
    private String user;

    private Map<String, Animal> animals;
    private String message;

}

class Animal {
    private String name;
    private String size;
    private float price;
}
