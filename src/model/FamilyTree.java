package model;

import view.View;

import java.util.ArrayList;
import java.util.List;

public class FamilyTree<T extends AbstractCreature> {
    private List<T> list;
    public FamilyTree() {
        this.list = new ArrayList<>();
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
        System.out.println("Not found");
        return null;
    }
    public void show(String name) {
        T person = this.findByName(name);
        if (person != null) {
            System.out.println(person);
        }
    }
    public void showFather(String name) {
        T person = this.findByName(name);
        if (person != null) {
            System.out.println(person.getFather());
        }
    }
    public void showMother(String name) {
        T person = this.findByName(name);
        if (person != null) {
            System.out.println(person.getMother());
        }
    }
    public void showParents(String name) {
        T person = this.findByName(name);
        if (person != null) {
            person.showParents();
        }
    }
    public void showSpouse(String name) {
        T person = this.findByName(name);
        if (person != null) {
            person.showSpouse();
        }
    }
    public void showChildren(String name) {
        T person = this.findByName(name);
        if (person != null) {
            person.showChildren();
        }
    }
    public void showSiblings(String name, Gender gender) {
        T person = this.findByName(name);
        if (person != null) {
            person.showSiblings(gender);
        }
    }
    public void showUnclesAunts(String name,Gender gender) {
        T person = this.findByName(name);
        if (person != null) {
            person.showUnclesAunts(gender);
        }
    }
    public void showTreeParents(String name) {
        T person = this.findByName(name);
        if (person != null) {
            person.showTreeParents();
        }
    }
    public void showTreeDescendants(String name) {
        T person = this.findByName(name);
        if (person != null) {
            person.showTreeDescendants();
        }
    }
    public void showAll() {
        List<T> list = this.getList();
        if (list.size() == 0) {
            System.out.println("Tree is empty");
        }
        System.out.println("In Tree:");
        for (T person : list) {
            System.out.println(person);
        }
    }
}
