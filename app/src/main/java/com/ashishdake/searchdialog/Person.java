package com.ashishdake.searchdialog;

public class Person extends BasePerson{
    private int age;
    private String address;

    public Person(String code, String name) {
        super(code, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
