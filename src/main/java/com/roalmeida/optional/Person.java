package com.roalmeida.optional;

import java.util.Optional;

public class Person {
    private String name;
    private int age;
    private String password;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
