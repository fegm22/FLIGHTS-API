package com.nexthink.quiz.question6.expression.util;

import com.nexthink.quiz.question6.expression.operation.Add;
import com.nexthink.quiz.question6.expression.operation.Div;
import com.nexthink.quiz.question6.expression.operation.Multiply;
import com.nexthink.quiz.question6.expression.operation.Subtract;

/**
 * Created by fogd on 12.06.17.
 */
public class ArithmeticManager {

	/**
	 * This method validate if the operator is valid or not
	 *
	 * @param op
	 * @return an exception if the operator is not valid
	 */
	public static void isOperator(String op) {
		if (op != "+"
				&& op != "-"
				&& op != "*"
				&& op != "/") {
			throw new IllegalArgumentException("Invalid operator: "
					+ op
					+ ".");
		}
	}

	/**
	 * This method evaluate the left and the right parameter depending on the arithmetic operation
	 *
	 * @param s
	 * @param left
	 * @param right
	 * @return a int number with the evaluation result
	 */
	public static int evaluation(String s, Evaluator left, Evaluator right) {
		switch (s) {
			case "+":
				Add add = new Add(left, right);
				return add.evaluate();
			case "-":
				Subtract subtract = new Subtract(left, right);
				return subtract.evaluate();
			case "*":
				Multiply multiply = new Multiply(left, right);
				return multiply.evaluate();
			case "/":
				Div div = new Div(left, right);
				return div.evaluate();
		}
		return 0;
	}

	/**
	 * This method return the name of the arithmetic operation
	 *
	 * @param op
	 * @return a String with the name of the operation
	 */
	public static String xmlGetNodeName(String op) {
		switch (op) {
			case "+":
				return "Add";
			case "-":
				return "Subtract";
			case "*":
				return "Multiply";
			case "/":
				return "Divide";
		}
		// We should never get here
		System.out.println("Invalid state!");
		return null;
	}

}