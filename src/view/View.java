package view;

import controller.Controller;
import model.AbstractCreature;

import java.util.Scanner;

public class View<T extends AbstractCreature> {
    private Controller controller;
    private Scanner scanner;
    private String dataIn;
    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        boolean active = this.start();
        while (active) {
            System.out.print("Print command --> ");
            this.dataIn = scanner.nextLine();
            active = controller.working(dataIn);
        }
        this.stop();
    }
    private boolean start() {
        scanner = new Scanner(System.in);
        return true;
    }
    private void stop() {
        scanner.close();
    }
}
