package org.example;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String address;


    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    private PersonType type;

    public String getName() {
        return this.name;
    }

    public int getAge(Person p) {
        return p.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age, PersonType type, String address) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Person " + getName() + " at location " + address;
    }


}