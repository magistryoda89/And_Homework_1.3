package com.kolomiytsev.and_homework_13.health;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Pressure implements Comparable<Pressure> {
    private int down;
    private int up;
    private int pulse;
    private boolean tachycardia;
    private Calendar time;

    public Pressure(int down, int up, int pulse, boolean tachycardia) {
        this.down = down;
        this.up = up;
        this.pulse = pulse;
        this.tachycardia = tachycardia;
        this.time = Calendar.getInstance();
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public boolean isTachycardia() {
        return tachycardia;
    }

    public void setTachycardia(boolean tachycardia) {
        this.tachycardia = tachycardia;
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
        if (tachycardia) {
            return String.format("Дата измерения: %s%n" +
                    "Давление: %d на %d%n" +
                    "Пульс: %d уд/мин%n" +
                    "Тахикардия: Да.", dateFormat.format(time.getTime()), up, down, pulse);
        } else {
            return String.format("Дата измерения: %s%n" +
                    "Давление: %d на %d%n" +
                    "Пульс: %d уд/мин%n" +
                    "Тахикардия: Нет.", dateFormat.format(time.getTime()), up, down, pulse);
        }
    }

    @Override
    public int compareTo(Pressure o) {
        return this.time.compareTo(o.time);
    }
}
