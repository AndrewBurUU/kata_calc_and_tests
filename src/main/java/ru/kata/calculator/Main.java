package ru.kata.calculator;

import java.util.Scanner;

public class Main {

    public static final int COUNT_ELEMENTS = 3;

    public static final int MAX_NUMBER = 10;

    public static boolean isRomeNumSystem;

    public static boolean isNumeric(String str) throws ScannerException {
        boolean res = false;
        if (str.contains(".") || str.contains(",")) {
            throw new ScannerException("Дробное число!");
        } else {
            try {
                if (Integer.parseInt(str) > MAX_NUMBER) {
                    throw new ScannerException("Число должно быть в диапазоне от 1 до 10 включительно.");
                } else {
                    res = true;
                }
            } catch (NumberFormatException e) {
            }
        }
        return res;
    }

    public static boolean isNumRome(String str) {
        for (int i = 0; i < str.length(); i++) {
            boolean elementExists = false;
            String s = str.substring(i, i + 1);
            for (RomeNumbers romeNum : RomeNumbers.values()) {
                if (romeNum.name().equals(s)) {
                    elementExists = true;
                    break;
                }
            }
            if (!elementExists) {
                return false;
            }
        }
        return true;
    }

    public static int convertToArabic(String romanNumber) {
        int res = 0;
        int i = 0;
        while (i < romanNumber.length()) {
            String s = romanNumber.substring(i,i + 1);
            int numArab = RomeNumbers.valueOf(s).getNumber();
            if (numArab == 5 || numArab == 10 && res > 0) {
                res = numArab - res;
            } else {
                res += numArab;
            }
            i++;
        }
        return res;
    }

    public static String convertToRoman(int number) {
        StringBuilder romanNumber = new StringBuilder();
        int i = RomeNumbers.values().length - 1;
        while (number > 0) {
            if (number >= RomeNumbers.values()[i].getNumber()) {
                romanNumber.append(RomeNumbers.values()[i].name());
                number -= RomeNumbers.values()[i].getNumber();
            } else {
                i--;
            }
        }
        return romanNumber.toString();
    }

    public static void checkStringLength(String[] elements) throws ScannerException {
        if (elements.length < COUNT_ELEMENTS) {
            throw new ScannerException("Слишком мало значений! Строка не является математической операцией! ");
        }
        if (elements.length > COUNT_ELEMENTS) {
            throw new ScannerException("Слишком много значений!");
        }
    }

    public static void checkNumericSystem(String[] elements) throws ScannerException {
        boolean isArab = false;
        boolean isRome = false;
        for (int i = 0; i < elements.length; i++) {
            if (i % 2 == 0){
                if (isNumeric(elements[i])) {
                    isArab = true;
                } else {
                    isRome = true;
                }
            }
        }
        if (isArab && isRome) {
            throw new ScannerException("Разные системы счисления!");            
        }
        isRomeNumSystem = isRome ? true : false;
    }


    public static String calc(String input) throws ScannerException {
        int[] nums = new int[COUNT_ELEMENTS - 1]; // массив чисел
        int indexNum = 0; // индекс массива чисел

        String accepted = "-+*/"; // строка со списком возможных операций над числами
        String[] operations = new String[COUNT_ELEMENTS - 2]; // массив операций
        int indexOper = 0; // индекс массива операций
        char operation = ' ';

        String[] elements = input.split(" "); // получить массив из строки с выражением
        checkStringLength(elements);
        checkNumericSystem(elements);
        for (int indexElement = 0; indexElement < elements.length; indexElement++) {
            String element = elements[indexElement];
            // четный индекс массива - число, нечетный - операция
            if (indexElement % 2 == 0) {
                if (isNumeric(element)) {
                    nums[indexNum++] = Integer.parseInt(element);
                } else if (isNumRome(element)) {
                    nums[indexNum++] = convertToArabic(element);
                } else {
                    throw new ScannerException("Не правильное число!");
                }
            } else {
                if (element.length() == 1) {
                    operation = element.charAt(0);
                    if (accepted.indexOf(operation) < 0) {
                        throw new ScannerException("Не правильная операция!");
                    } else {
                        operations[indexOper++] = element;
                    }
                }
            }
        }
        int res = 0;
        switch (operation) {
            case '+': res = nums[0] + nums[1]; break;
            case '-': res = nums[0] - nums[1]; break;
            case '*': res = nums[0] * nums[1]; break;
            case '/': res = nums[0] / nums[1]; break;
        }
        if (isRomeNumSystem) {
            if (res < 0) {
                throw new ScannerException("В римской системе нет отрицательных чисел!");
            }
            return convertToRoman(res);
        } else {
            return String.valueOf(res);
        }
    }

    public static void main(String[] args) throws ScannerException {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите выражение вида 'a операция b': ");
            String expr = in.nextLine();
            System.out.println(calc(expr));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}