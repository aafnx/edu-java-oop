import model.Calculator;
import presenter.Presenter;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        Presenter presenter = new Presenter();
        Calculator calculator = new Calculator();
        view.setPresenter(presenter);
        calculator.setPresenter(presenter);
        view.init();
    }
}
