package com.nexthink.quiz.question6.expression.operation;

import com.nexthink.quiz.question6.expression.util.Evaluator;
import com.nexthink.quiz.question6.expression.util.IntegerNumber;

/**
 * Created by fogd on 12.06.17.
 */
public class Multiply implements Evaluator {

	private final Evaluator left;
	private final Evaluator right;

	public Multiply(Evaluator leftExpression, Evaluator rightExpression ){
		this.left = leftExpression;
		this.right = rightExpression;
	}

	@Override
	public int evaluate() {
		if(left instanceof IntegerNumber && right instanceof IntegerNumber){
			return left.getLeftValue() * right.getRightValue();
		}

		return left.evaluate() * right.evaluate();
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
