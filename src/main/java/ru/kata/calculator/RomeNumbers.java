package ru.kata.calculator;

enum RomeNumbers {
    I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100);

    private int number;

    RomeNumbers(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }
}
