package view;

import presenter.Presenter;

import java.util.Scanner;

public class ConsoleView implements View {
    private Presenter presenter;
    private Scanner scanner;
    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }
    public void init() {
        System.out.print("Type command --> ");
        String commands = this.scanner.nextLine();
        presenter.start(commands);
    }
}
