package ru.kata.calculator;

import java.util.Scanner;

public class Main {

    public static boolean isRomeNumSystem;

    public static boolean isNumeric(String str) throws ScannerException {
        boolean res = false;
        if (str.contains(".") || str.contains(",")) {
            throw new ScannerException("Дробное число!");
        } else {
            try {
                if (Integer.parseInt(str) > 10) {
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
        boolean res = false;
        for (NumRome numRome : NumRome.values()) {
            if (numRome.name().equals(str)) {
                res = true;
            }
        }
        return res;
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

    public static void checkStringLength(String[] parts) throws ScannerException {
        if (parts.length < 3) {
            throw new ScannerException("Слишком мало значений! Строка не является математической операцией! ");
        }
        if (parts.length > 3) {
            throw new ScannerException("Слишком много значений!");
        }
    }

    public static void checkNumericSystem(String[] parts) throws ScannerException {
        if (isNumeric(parts[0]) && isNumRome(parts[2])) {
            throw new ScannerException("Разные системы счисления!");
        }
        if (isNumRome(parts[0]) && isNumeric(parts[2])) {
            throw new ScannerException("Разные системы счисления!");
        }
        if (isNumRome(parts[0]) && isNumRome(parts[2])) {
            isRomeNumSystem = true;
        } else {
            isRomeNumSystem = false;
        }
    }

    public static String calc(String input) throws ScannerException {
        String accepted = "-+*/";
        int[] nums = new int[2];
        int i = 0;
        char operation = ' ';
        String [] parts = input.split(" ");
        checkStringLength(parts);
        checkNumericSystem(parts);
        for (int j = 0; j < 3; j++) {
            String part = parts[j];
            if (j == 0 || j == 2) {
                if (isNumeric(part)) {
                    nums[i++] = Integer.parseInt(part);
                } else if (isNumRome(part)) {
                    nums[i++] = convertToArabic(part);
                } else {
                    throw new ScannerException("Не правильное число!");
                }
            }
            if (j == 1) {
                if (part.length() == 1) {
                    operation = part.charAt(0);
                    if (accepted.indexOf(operation) < 0) {
                        throw new ScannerException("Не правильная операция!");
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