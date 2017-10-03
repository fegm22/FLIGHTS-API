package com.nexthink.quiz.question6.expression.util;

/**
 * Created by fogd on 13.06.17.
 */
public class IntegerNumber implements Evaluator {
    private final int number;

    public IntegerNumber(int n){
        this.number = n;
    }

    @Override
    public int evaluate() {
        return number;
    }

    @Override
    public int getLeftValue() {
        return number;
    }

    @Override
    public int getRightValue() {
        return number;
    }

}
