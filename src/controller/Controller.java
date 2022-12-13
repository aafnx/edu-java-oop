package controller;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller<T extends AbstractCreature> {
    private FamilyTreeRepository<T> familyTreeRepository;
    private Terminal terminal;

    public Controller() {;
    }
    public void setFamilyTreeRepository(FamilyTreeRepository<T> familyTreeRepository) {
        this.familyTreeRepository = familyTreeRepository;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
    public boolean working(String data) {
        List<String> parsed = this.parse(data);
        String action = parsed.get(0);
        String name = parsed.get(1);

        return this.task(action, name);
    }
    public List<String> parse(String data) {
        List<String> in = new ArrayList<>(Arrays.asList(data.split(" ", 2)));
        List<String> result = new ArrayList<>();

        String action = in.get(0);
        String name = "";

        if (in.size() > 1) {
            name = in.get(1);
        }
        result.add(action);
        result.add(name);
        return  result;


    }
    public boolean task(String action, String name) {
        action = action.toLowerCase();

        if (action.equals(cmd.show.name())) familyTreeRepository.show(name);
        else if (action.equals(cmd.father.name())) familyTreeRepository.showFather(name);
        else if (action.equals(cmd.mother.name())) familyTreeRepository.showMother(name);
        else if (action.equals(cmd.parents.name())) familyTreeRepository.showParents(name);
        else if (action.equals(cmd.spouse.name())) familyTreeRepository.showSpouse(name);
        else if (action.equals(cmd.children.name())) familyTreeRepository.showChildren(name);
        else if (action.equals(cmd.sister.name())) familyTreeRepository.showSiblings(name, Gender.woman);
        else if (action.equals(cmd.brother.name())) familyTreeRepository.showSiblings(name, Gender.man);
        else if (action.equals(cmd.aunt.name())) familyTreeRepository.showUnclesAunts(name, Gender.woman);
        else if (action.equals(cmd.uncle.name())) familyTreeRepository.showUnclesAunts(name, Gender.man);
        else if (action.equals(cmd.treeparents.name())) familyTreeRepository.showTreeParents(name);
        else if (action.equals(cmd.treedescendants.name())) familyTreeRepository.showTreeDescendants(name);
        else if (action.equals(cmd.showall.name())) familyTreeRepository.showAll();
        else if (action.equals(cmd.list.name())) terminal.printCommands();
        else if (action.equals(cmd.exit.name())) return terminal.stop();
        else terminal.commandNotFound();

        return terminal.start();
    }
}
