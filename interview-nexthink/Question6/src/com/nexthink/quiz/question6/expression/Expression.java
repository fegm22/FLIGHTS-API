package com.nexthink.quiz.question6.expression;

import com.nexthink.quiz.question6.expression.util.Evaluator;
import com.nexthink.quiz.question6.expression.util.ArithmeticManager;
import com.nexthink.quiz.question6.expression.util.IntegerNumber;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Expression implements Evaluator {
    private String op;
    private IntegerNumber leftValue;
    private IntegerNumber rightValue;
    private boolean isUnary;
    private Expression left;
    private Expression right;

    public Expression(int leftValue, String op, int rightValue) {
        ArithmeticManager.isOperator(op);

        this.leftValue = new IntegerNumber(leftValue);
        this.op = op;
        this.rightValue = new IntegerNumber(rightValue);
    }

    public Expression(Expression left, String op, Expression right) {
        ArithmeticManager.isOperator(op);
        if (left == null) {
            throw new IllegalArgumentException("left is null");
        }
        if (right == null) {
            throw new IllegalArgumentException("right is null");
        }
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public Expression(int leftValue) {
        this.leftValue = new IntegerNumber(leftValue);
        isUnary = true;
    }

    public Element toXml(Document document) {
        if (isUnary) {
            org.w3c.dom.Element elem = document.createElement("Value");
            elem.appendChild(document.createTextNode(String
                    .valueOf(this.getLeftValue())));
            return elem;
        } else if (this.getLeft() != null
                && this.getRight() != null) {
            Element elem = document.createElement(ArithmeticManager.xmlGetNodeName(op));
            elem.appendChild(this.getLeft().toXml(document));
            elem.appendChild(this.getRight().toXml(document));
            return elem;
        } else if (this.getLeft() == null
                && this.getRight() == null) {
            Element elem = document.createElement(ArithmeticManager.xmlGetNodeName(op));
            elem.appendChild(new Expression(this.getLeftValue()).toXml(document));
            elem.appendChild(new Expression(this.getRightValue()).toXml(document));
            return elem;
        } else {
            // We should never get here
            System.out.println("Invalid state!");
            return null;
        }
    }

    @Override
    public int evaluate() {
        if (isUnary) {
            return leftValue.getLeftValue();
        }else if (left != null
                && right != null) {
            return ArithmeticManager.evaluation(op, left, right);
        }

        return ArithmeticManager.evaluation(op, leftValue, rightValue);
    }

    @Override
    public int getLeftValue() {
        return leftValue.getLeftValue();
    }

    @Override
    public int getRightValue() {
        return rightValue.getRightValue();
    }

    @Override
    public String toString() {
        if (isUnary) {
            return Integer.toString(leftValue.getLeftValue());
        } else if (left != null
                && right != null) {
            return "("
                    + left.toString()
                    + " "
                    + op
                    + " "
                    + right.toString()
                    + ")";
        } else if (left == null
                && right == null) {
            return "("
                    + leftValue.getLeftValue()
                    + " "
                    + op
                    + " "
                    + rightValue.getRightValue()
                    + ")";
        } else {
            // We should never get here
            throw new IllegalStateException("Invalid state!");
        }
    }

    public Expression getLeft() {
        return left;
    }
    public Expression getRight() {
        return right;
    }
}
