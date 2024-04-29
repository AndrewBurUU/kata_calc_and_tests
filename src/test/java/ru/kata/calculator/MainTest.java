package ru.kata.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MainTest {

    @Test
    public void whenOnePlusOneThenOk() throws ScannerException {
        String in = "1 + 1";
        String expected = "2";
        assertThat(Main.calc(in), is(expected));
    }

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
    }

}