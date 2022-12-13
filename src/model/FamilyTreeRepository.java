package model;

import view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FamilyTreeRepository<T extends AbstractCreature> implements FamilyTreeShowable {
    private List<T> list;
    private View view;
    public FamilyTreeRepository() {
        this.list = new ArrayList<>();
    }

    public void setView(View view) {
        this.view = view;
    }
    public void add(T creature) {
        this.list.add(creature);
    }
    public void addAll(List<T> list) {
        this.list.addAll(list);
    }
    public List<T> getList() {
        return this.list;
    }
    public T findByName(String name) {
        name = name.toLowerCase();
        for (T person : this.list) {
            if (person.getName().toLowerCase().equals(name)) {
                return person;
            }
        }
        return null;
    }
    public void show(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        view.print(person.toString());
    }
    public void showFather(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        if (person.getFather() == null) {
            view.print("Папа " + person + " неизвестен");
            return;
        }
        view.print(person.getFather().toString());
    }
    public void showMother(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        if (person.getMother() == null) {
            view.print("Мама " + person + " неизвестена");
            return;
        }
        view.print(person.getMother().toString());
    }
    public void showNotFoundPerson() {
        view.printError("Not found person");
    }

    public void showParents(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        List<AbstractCreature> parents = person.getParents();
        if (parents == null) {
            view.print("Родители " + person + " неизвестны");
        } else {
            view.print("Родители " + person + ":");
            for (AbstractCreature parent : parents) {
                view.print(parent.toString());
            }
            view.print("------");
        }
    }

    public void showSpouse(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        AbstractCreature spouse = person.getSpouse();
        String word = person.gender == Gender.man ? "супруга" : "супруг";
        if (spouse == null) {
            view.print("У " + person + " отсутсвует " + word);
            return;
        }
        view.print("У " + person + " есть " + word + " – " + spouse);
    }

    public void showChildren(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        List<AbstractCreature> children = person.getChildren();
        if (children == null) {
            view.print("У " + person + " нет детей");
        } else {
            view.print("Дети " + person + ":");
            for (AbstractCreature child : children) {
                view.print(child.toString());
            }
            view.print("------");
        }
    }

    public void showSiblings(String name, Gender gender) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        HashSet<AbstractCreature> result = person.getSiblings(gender);
        String word = gender == Gender.man ? "брат" : "сестра";
        if (result == null) {
            view.print("У " + person + " нет " + word);
            return;
        }

        view.print("У " + person + " есть " + word);
        for (AbstractCreature p : result ) {
            view.print(p.toString());
        }
        view.print("------");
    }

    public void showUnclesAunts(String name, Gender gender) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        HashSet<AbstractCreature> unclesOrAunts = person.getUnclesAunts(gender);
        String word = gender == Gender.man ? "дяди" : "тети";
        if (unclesOrAunts == null) {
            view.print("У " + person + " нет " + word);
            return;
        }
        view.print("У " + person + " есть " + word);
        for (AbstractCreature uncleOrAunt : unclesOrAunts) {
            view.print(uncleOrAunt.toString());
        }
        view.print("------");
    }

    public void showTreeParents(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        if (person.getParents() == null) {
            view.print("Предки " + this + " неизвестны");
            return;
        }
        view.print("Все предки");
        this.generateTreeParents(person, "");
        view.print("------");
    }
    private void generateTreeParents(AbstractCreature person, String spaces) {
        if (person == null) {
            return;
        }
        view.print(spaces + " " + person);
        spaces += "  ";
        generateTreeParents(person.father, spaces);
        generateTreeParents(person.mother, spaces);
    }

    public void showTreeDescendants(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        if (person.getChildren() == null) {
            view.print("Потомки " + this + " неизвестны");
            return;
        }
        view.print("Все потомки ");
        this.generateTreeDescendants(person, "");
        view.print("------");
    }
    private void generateTreeDescendants(AbstractCreature person, String spaces) {
        if (person == null) {
            return;
        }
        view.print(spaces + " " + person);
        spaces += "  ";
        for (AbstractCreature child : person.children) {
            generateTreeDescendants(child, spaces);
        }

    }
    public void showAll() {
        List<T> list = this.getList();
        if (list.size() == 0) {
            view.print("Tree is empty");
        }
        view.print("In Tree: ");
        for (T person : list) {
            view.print(person.toString());
        }
    }
}
