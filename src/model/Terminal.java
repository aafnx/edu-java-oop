package model;

import view.View;

public class Terminal {
    private View view;
    public void setView(View view) {
        this.view = view;
    }
    public boolean start() {
        return true;
    }
    public boolean stop() {
        return false;
    }

    public void commandNotFound() {
        view.printError("Command not found: list commands - '" + cmd.list.name() + "', if you want exit type - '" + cmd.exit.name() + "'");
    }
    public void printCommands() {
        view.print("List commands: ");
        for (cmd command : cmd.values()) {
            view.print(command.name());
        }
        view.print("-------");

    }
}
