package com.nexthink.quiz.question6;

import java.io.IOException;

import com.nexthink.quiz.question6.expression.Expression;
import com.nexthink.quiz.question6.expression.util.Writer;

/**
 * Copyright (C) 2011 by NEXThink SA, Switzerland. Any usage, copy or
 * partial copy of this code without the explicit agreement of NEXThink SA
 * is prohibited and will be pursued to the full extend of the law.
 */
public class ProgramOriginal {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Expression expression = new Expression(new Expression(
                new Expression(5, "+", 4), "*", new Expression(6)), "/",
                new Expression(3, "-", 1));

        Writer writer = new Writer(expression);

        try {
            System.out.println(expression.toString()
                    + " = "
                    + expression.evaluate());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        if (args.length > 0) {
            System.out.println("Serializing XML to '"
                    + args[0]
                    + "' ...");
            boolean success = writer.serializeToXml(args[0]);
            System.out
                    .println(success ? "Success!" : "An error occurred.");
        }

        System.out.println("Press Enter to exit.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
