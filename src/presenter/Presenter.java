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
        String operator = commands.get(1);
        double n1;
        double n2;
        try {
            n1 = Double.parseDouble(commands.get(0));
            n2 = Double.parseDouble(commands.get(2));
        } catch (NumberFormatException exception) {
            view.print(exception.getMessage());
            this.wrongData();
            return;
        }

        view.print(buildMessage(calculator.calculate(operator, n1, n2)));

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
}
