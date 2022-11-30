package model;

public class Terminal {
    public boolean start() {
        return true;
    }
    public boolean stop() {
        return false;
    }

    public void commandNotFound() {
        System.err.printf("\nCommand not found: list commands - '%s', if you want exit type - '%s'", cmd.list.name(), cmd.exit.name());
    }
    public void printCommands() {
        System.out.println("List commands: ");
        for (cmd command : cmd.values()) {
            System.out.printf("%s ", command.name());
        }
        System.out.println();
    }
}
