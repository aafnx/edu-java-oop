package model;

import presenter.Presenter;

public class Calculator {
    Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        presenter.setCalculator(this);
    }

    public double sum(double n1, double n2) {
        return n1 + n2;
    }
    public double minus(double n1, double n2) {
        return n1 - n2;
    }
    public double multiplication(double n1, double n2) {
        return n1 * n2;
    }
    public double dividing(double n1, double n2) {
//        double res = n1 / n2;
//        return Double.isInfinite(res) ? 0 : res;
        return n1 / n2;
    }
}
