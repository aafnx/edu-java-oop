package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FamilyTreeRepository<T extends AbstractCreature> implements FamilyTreeShowable {
    private List<T> list;
    public FamilyTreeRepository() {
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
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        System.out.println(person);
    }
    public void showFather(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        System.out.println(person.getFather());
    }
    public void showMother(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        System.out.println(person.getMother());
    }
    public void showNotFoundPerson() {
        System.err.println("Not found person");
    }

    public void showParents(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        List<AbstractCreature> parents = person.getParents();
        if (parents == null) {
            System.out.printf("Родители %s неизвестны\n", person);
        } else {
            System.out.printf("Родители %s: \n", person);
            for (AbstractCreature parent : parents) {
                System.out.println(parent);
            }
            System.out.print("------\n");
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
            System.out.printf("У %s отсутсвует %s\n", person, word);
            return;
        }
        System.out.printf("У %s есть %s – %s \n", person, word, spouse);
    }

    public void showChildren(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        List<AbstractCreature> children = person.getChildren();
        if (children == null) {
            System.out.printf("У %s нет детей\n", person);
        } else {
            System.out.printf("Дети %s:\n", person);
            for (AbstractCreature child : children) {
                System.out.println(child);
            }
            System.out.print("-------\n");
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
            System.out.printf("У %s нет %s\n", person, word);
            return;
        }

        System.out.printf("У %s есть %s:\n", person, word);
        for (AbstractCreature p : result ) {
            System.out.println(p);
        }
        System.out.print("------\n");
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
            System.out.printf("У %s нет %s\n", person, word);
            return;
        }
        System.out.printf("У %s есть %s:\n", person, word);
        for (AbstractCreature uncleOrAunt : unclesOrAunts) {
            System.out.println(uncleOrAunt);
        }
        System.out.println("-------");
    }

    public void showTreeParents(String name) {
        T person = this.findByName(name);
        if (person == null) {
            this.showNotFoundPerson();
            return;
        }
        if (person.getParents() == null) {
            System.out.printf("Предки %s неизвестны\n", this);
            return;
        }
        System.out.print("Все предки ");
        this.generateTreeParents(person, "");
        System.out.println("------");
    }
    private void generateTreeParents(AbstractCreature person, String spaces) {
        if (person == null) {
            return;
        }
        System.out.printf("%s %s \n", spaces, person);
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
            System.out.printf("Потомки %s неизвестны\n",this);
            return;
        }
        System.out.print("Все потомки ");
        this.generateTreeDescendants(person, "");
        System.out.println("------");
    }
    private void generateTreeDescendants(AbstractCreature person, String spaces) {
        if (person == null) {
            return;
        }
        System.out.printf("%s %s \n", spaces, person);
        spaces += "  ";
        for (AbstractCreature child : person.children) {
            generateTreeDescendants(child, spaces);
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
