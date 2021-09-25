package com.toby.practice.example.Chapter3_Template;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

public class CalculatorTest {
    Calculator calculator;
    String numfilePath;

    @BeforeEach
    void setup(){
        this.calculator = new Calculator();
        this.numfilePath = getClass().getResource("/static/numbers.txt").getPath();
    }

    /*proto Calc Test*/
    @Test
    void sumOfNumbers_proto() throws IOException{
        assertThat(calculator.calcSum_proto(numfilePath), is(10));
    }

    /*File Template Test*/
    @Test
    void sumOfNumbers_file() throws IOException{
        assertThat(calculator.calcSum_file(numfilePath), is(10));
    }
    @Test
    void mulOfNumbers_file() throws IOException{
        assertThat(calculator.calcMul_file(numfilePath), is(24));
    }

    /*(Generic) Line Template Test*/
    @Test
    void sumOfNumbers_line() throws IOException{
        assertThat(calculator.calcSum_line(numfilePath), is(10));
    }
    @Test
    void mulOfNumbers_line() throws IOException{
        assertThat(calculator.calcMul_line(numfilePath), is(24));
    }
    @Test
    void concatenate() throws IOException{
        assertThat(calculator.concatenate(numfilePath), is("1234"));
    }
}
