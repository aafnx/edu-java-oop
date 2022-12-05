package presenter;

import model.Calculator;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    private View view;
    private Calculator calculator;

    public void setView(View view) {
        this.view = view;
    }
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void parse(String text) {
        List<String> commands = new ArrayList<>(Arrays.asList(text.split(" ")));
        String operator = commands.get(1);
        double n1 = Double.parseDouble(commands.get(0));
        double n2 = Double.parseDouble(commands.get(2));
        this.calculate(operator, n1, n2);
    }
    public String buildMessage(Double n) {

        if (Double.isInfinite(n)) {
            return "На ноль делить нельзя!";
        }
        return "Result - " + n;
    }
    public void calculate(String operator, double n1, double n2) {
        switch (operator) {
            case "+" -> view.print(this.buildMessage(calculator.sum(n1, n2)));
            case "-" -> view.print(this.buildMessage(calculator.minus(n1, n2)));
            case "*" -> view.print(this.buildMessage(calculator.multiplication(n1, n2)));
            case "/" -> view.print(this.buildMessage(calculator.dividing(n1, n2)));
            default -> view.print("Wrong operator");
        }
    }
}
