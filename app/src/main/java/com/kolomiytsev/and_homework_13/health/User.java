package com.kolomiytsev.and_homework_13.health;

public class User {
    private String fio;
    private int age;

    public User(String fio, int age) {
        this.fio = fio;
        this.age = age;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("ФИО: %s%nВозраст: %d", fio, age);
    }
}
