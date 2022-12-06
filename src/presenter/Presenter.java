package presenter;

import model.Calculator;
import model.Parser;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presenter {
    private View view;
    private Calculator calculator;
    private Parser parser;
    public Presenter() {
        this.parser = new Parser();
    }
    public void setView(View view) {
        this.view = view;
    }
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
    public void start(String text) {
        List<String> commands = parser.parseLine(text);
        if (commands == null) {
            this.wrongData();
            return;
        }
        view.print(buildMessage(this.calculate(commands)));
    }
    private String buildMessage(Double n) {
        if (n == null) {
            this.wrongData();
            return "";
        }
        return "Result - " + n;
    }
    private void wrongData() {
        view.print("Wrong data");
    }
    private void calcByPriorityOperator(List<String> commands) {
        for (int i = 1; i < commands.size(); i += 2) {
            int indexOperator = parser.getIndexPriorityOperator(commands);
            if (indexOperator == -1) {
                return;
            }
            String operator = parser.parseOperator(commands, indexOperator);
            Double n1 = parser.parseNumber(commands, indexOperator - 1);
            Double n2 = parser.parseNumber(commands, indexOperator + 1);
            if (parser.numbersIsNull(n1, n2)) {
                return;
            }
            String r = String.valueOf(calculator.calculate(operator, n1, n2));
            for (int j = 0; j < 3; j++) {
                commands.remove(indexOperator - 1);
            }
            commands.add(indexOperator -1, r);
        }
    }
    private Double calculate(List<String> commands) {
        Double res = 0.0;
        this.calcByPriorityOperator(commands);
        for (int i = 1; i < commands.size(); i += 2) {
            if (i == 1) {
                res = parser.parseNumber(commands, 0);
            }

            String operator = parser.parseOperator(commands, i);
            Double n = parser.parseNumber(commands, i + 1);
            if (parser.numbersIsNull(res, n)) {
                return null;
            }
            res = calculator.calculate(operator, res, n);
        }
        return res;
        }
    }