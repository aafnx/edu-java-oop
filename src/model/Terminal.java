package model;

public class Terminal {
    private String answer;
    public boolean start() {
        return true;
    }
    public boolean stop() {
        return false;
    }

    public void commandNotFound() {
        this.answer = "\nCommand not found: list commands - 'list', if you want exit type - 'exit'";
        System.err.println(answer);
    }
    public void printCommands() {
        this.answer = "List commands:\nshow, father, mother, parents, spouse, children, sister, brother, aunt, uncle, TreeParents, TreeDescendants, showAll";
        System.out.println(answer);
    }
}
