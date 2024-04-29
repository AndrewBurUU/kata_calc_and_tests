package ru.kata.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MainTest {

    @Test(expected = ScannerException.class)
    public void whenIncorrectFormulaThenFail() throws ScannerException {
        Main.calc("1");
        Main.calc("1 + 2 + 3");
        Main.calc("1 _ 2");
        Main.calc("11 + 2");
        Main.calc("1 + 11");
        Main.calc("1 1 1");
        Main.calc("/");
        Main.calc("а + б");
        Main.calc("a + b");
        Main.calc("5а + 1");
        Main.calc("5 + b4");
        Main.calc("1,5 + 1");
        Main.calc("1 + 1.5");
        Main.calc("1;5 + 1");
        Main.calc("I + 1");
        Main.calc("1 + I");
        Main.calc("I + IV + X");
        Main.calc("I - IV");
    }

    @Test
    public void whenOnePlusOneThenOk() throws ScannerException {
        String in = "1 + 1";
        String expected = "2";
        assertThat(Main.calc(in), is(expected));
    }

    @Test
    public void whenRomeNumThenTrue() {
        String in = "I";
        assertTrue(Main.isNumRome(in));
    }

    @Test
    public void whenGetRomeNumThenOk() {
        String in = "IV";
        int expected = 4;
        assertThat(Main.getArabicFromRome(in), is(expected));
    }

    @Test
    public void whenRomeOnePlusOneThenTwoRome() throws ScannerException {
        String in = "I + II";
        String expected = "III";
        assertThat(Main.calc(in), is(expected));
    }
}