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

    public void parse(String text) throws NumberFormatException {
        List<String> commands = new ArrayList<>(Arrays.asList(text.split(" ")));
        commands.removeIf(string -> string.equals(""));
        if (checkCommands(commands)) {
            this.wrongData();
            return;
        }
        view.print(buildMessage(this.calc(commands)));
    }
    public String buildMessage(Double n) {
        if (n == null) {
            this.wrongData();
            return "";
        }
        return "Result - " + n;
    }

    public boolean checkCommands(List<String> list) {
        return list.size() < 3;
    }

    public void wrongData() {
        view.print("Wrong data");
    }

    public int getIndexPriorityOperator(List<String> commands) {
        int index;
        List<String> res = new ArrayList<>(commands);
        if (commands.contains("*") && commands.contains("/")) {
            index = Math.min(commands.indexOf("*"), commands.indexOf("/"));
            res.remove(res.get(index));
        } else if (commands.contains("*")) {
            index = commands.indexOf("*");
            res.remove(res.get(index));
        } else if (commands.contains("/")) {
            index = commands.indexOf("/");
            res.remove(res.get(index));
        } else {
            index = -1;
        }
        return index;
    }

    public Double calc(List<String> commands) {

        Double res = 0.0;

        for (int i = 1; i < commands.size(); i += 2) {
            int indexOperator = this.getIndexPriorityOperator(commands);
            if (indexOperator == -1) {
                break;
            }

            String operator = commands.get(indexOperator);
            double n1 = Double.parseDouble(commands.get(indexOperator - 1));
            double n2 = Double.parseDouble(commands.get(indexOperator + 1));

            String r = String.valueOf(calculator.calculate(operator, n1, n2));
            for (int j = 0; j < 3; j++) {
                commands.remove(indexOperator - 1);
            }
            commands.add(indexOperator -1, r);
        }

        for (int i = 1; i < commands.size() - 1; i += 2) {
            String operator = commands.get(i);

            double n;
            try {
                n = Double.parseDouble(commands.get(i+1));
                if (i == 1) {
                    res = Double.parseDouble(commands.get(0));
                }
            } catch (NumberFormatException exception) {
                view.print(exception.getMessage());
                this.wrongData();
                return null;
            }
            res = calculator.calculate(operator, res, n);
        }
        return res;

        }
    }

