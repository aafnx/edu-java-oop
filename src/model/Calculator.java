package model;

import presenter.Presenter;

public class Calculator {
    Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        presenter.setCalculator(this);
    }

    public Double calculate(String operator, double n1, double n2) {
        switch (operator) {
            case "+" -> {
                return this.sum(n1, n2);
            }
            case "-" -> {
                return this.minus(n1, n2);
            }
            case "*" -> {
                return this.multiplication(n1, n2);
            }
            case "/" -> {
                return this.dividing(n1, n2);
            }
            default -> {return null;}
        }
    }

    private Double sum(double n1, double n2) {
        return n1 + n2;
    }
    private Double minus(double n1, double n2) {
        return n1 - n2;
    }
    private Double multiplication(double n1, double n2) {
        return n1 * n2;
    }
    private Double dividing(double n1, double n2) {
        if (n2 == 0) {
            return null;
        }
        return n1 / n2;
    }
}
