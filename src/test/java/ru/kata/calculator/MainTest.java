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
        Main.calc("11 1");
        Main.calc("111");
        Main.calc("1+11");
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
        Main.calc("XI - IV");
        Main.calc("X- IV");
        Main.calc("X-IV");
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
        assertThat(Main.convertToArabic(in), is(expected));
    }

    @Test
    public void whenRomeOnePlusOneThenTwoRome() throws ScannerException {
        String in = "I + II";
        String expected = "III";
        assertThat(Main.calc(in), is(expected));
    }

    @Test
    public void whenRomeTenPlusOneThenElevenRome() throws ScannerException {
        String in = "X + I";
        String expected = "XI";
        assertThat(Main.calc(in), is(expected));
    }

    @Test
    public void whenRomeTenMyltiplyTenThenRomeC() throws ScannerException {
        String in = "X * X";
        String expected = "C";
        assertThat(Main.calc(in), is(expected));
    }

    @Test
    public void whenRomeTenMyltiplyFiveThenRomeL() throws ScannerException {
        String in = "X * V";
        String expected = "L";
        assertThat(Main.calc(in), is(expected));
    }

    @Test
    public void whenRomeNineMyltiplyEightThenRome72() throws ScannerException {
        String in = "IX * VIII";
        String expected = "LXXII";
        assertThat(Main.calc(in), is(expected));
    }

}