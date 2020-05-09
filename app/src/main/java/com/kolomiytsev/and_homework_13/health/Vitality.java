package com.kolomiytsev.and_homework_13.health;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Vitality implements Comparable<Vitality> {
    private float weight;
    private int steps;
    private Calendar time;

    public Vitality(float weight, int steps) {
        this.weight = weight;
        this.steps = steps;
        this.time = Calendar.getInstance();
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return String.format("Дата: %s%n" +
                "Масса тела: %.2f%n" +
                "Количество шагов: %d%n", dateFormat.format(time.getTime()), weight, steps);
    }

    @Override
    public int compareTo(Vitality o) {
        return this.time.compareTo(o.time);
    }
}
