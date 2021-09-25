package com.toby.practice.example.Chapter3_Template;

public interface LineCallback<T>{
    T doSomethingWithLine(String line, T value);
}
