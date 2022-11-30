package controller;

import model.AbstractCreature;
import model.FamilyTree;
import model.Gender;
import model.Terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller<T extends AbstractCreature> {
    private FamilyTree<T> familyTree;
    private Terminal terminal;

    public Controller(FamilyTree<T> familyTree, Terminal terminal) {
        this.familyTree = familyTree;
        this.terminal = terminal;
    }
    public boolean scanCommand(String data) {
        List<String> in = new ArrayList<>(Arrays.asList(data.split(" ", 2)));

        String action = in.get(0);
        String name = "";

        if (in.size() > 1) {
            name = in.get(1);
        }

        return this.task(action, name);
    }
    public boolean task(String action, String name) {
        switch (action.toLowerCase()) {
            case "show" -> familyTree.show(name);
            case "father" -> familyTree.showFather(name);
            case "mother" -> familyTree.showMother(name);
            case "parents" -> familyTree.showParents(name);
            case "spouse" -> familyTree.showSpouse(name);
            case "children" -> familyTree.showChildren(name);
            case "sister" -> familyTree.showSiblings(name, Gender.woman);
            case "brother" -> familyTree.showSiblings(name, Gender.man);
            case "aunt" -> familyTree.showUnclesAunts(name, Gender.woman);
            case "uncle" -> familyTree.showUnclesAunts(name, Gender.man);
            case "treeparents" -> familyTree.showTreeParents(name);
            case "treedescendants" -> familyTree.showTreeDescendants(name);
            case "showall" -> familyTree.showAll();
            case "list" -> terminal.printCommands();
            case "exit" -> {
                return terminal.stop();
            }
            default -> terminal.commandNotFound();
        }
        return terminal.start();
    }
}
