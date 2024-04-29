package ru.kata.calculator;

public enum NumRome {
    I("I");

    private String number;

    NumRome(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

}
