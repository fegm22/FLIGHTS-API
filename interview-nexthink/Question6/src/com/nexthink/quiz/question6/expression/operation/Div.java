package com.nexthink.quiz.question6.expression.operation;

import com.nexthink.quiz.question6.expression.util.Evaluator;
import com.nexthink.quiz.question6.expression.util.IntegerNumber;

/**
 * Created by fogd on 12.06.17.
 */
public class Div  implements Evaluator {

    private final Evaluator left;
    private final Evaluator right;

    public Div(Evaluator leftExpression, Evaluator rightExpression  ){
        this.left = leftExpression;
        this.right = rightExpression;
    }

    @Override
    public int evaluate() {

        if(left instanceof IntegerNumber && right instanceof IntegerNumber){
            if (right.getRightValue() == 0) {
                throw new ArithmeticException("Cannot divide by 0.");
            }
            return left.getLeftValue()
                    / right.getRightValue();
        }

        int rightEvaluatedValue = right.evaluate();
        if (rightEvaluatedValue == 0) {
            throw new ArithmeticException("Cannot divide by 0.");
        }
        return left.evaluate()
                / right.evaluate();
    }

    @Override
    public int getLeftValue() {
        return left.getLeftValue();
    }

    @Override
    public int getRightValue() {
        return right.getRightValue();
    }

}
