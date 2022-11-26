import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Human {
    private final int id;
    private String firstName;
    private String lastName;
    private Gender gender;

    private Human father;
    private Human mother;
    private List<Human> childs;

    public Human(int id, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.initChilds();
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void initChilds() {
        this.childs = new ArrayList<>();
    }
    public void addChild(Human child) {
        this.childs.add(child);
    }
    public void setFather(Human father) {
        if (father.gender == Gender.man) {
            this.father = father;
            this.father.addChild(this);
        } else {
            System.out.println("Введено некорректное значение");
        }
    }

    public void setMother(Human mother) {
        if (mother.gender == Gender.woman) {
            this.mother = mother;
            this.mother.addChild(this);
        } else {
            System.out.println("Введено некорректное значение");
        }
    }

    public Gender getGender() {
        return this.gender;
    }

    public List<Human> getChilds() {
        if (this.childs.size() == 0) {
            return null;
        }
        return this.childs;
    }
    public void showChilds() {
        List<Human> childs = getChilds();
        if (childs.size() == 0) {
            System.out.printf("У %s %s, нет детей\n", this.firstName, this.lastName);
        } else {
            System.out.printf("Дети %s %s:\n", this.firstName, this.lastName);
            for (Human child : childs) {
                System.out.printf("%s %s %s\n", child.firstName, child.lastName, child.gender);
            }
            System.out.printf("-------\n");
        }
    }
    public Human getFather() {
        return this.father;
    }

    public Human getMother() {
        return this.mother;
    }

    public List<Human> getParents() {
        List<Human> result = new ArrayList<>();
        Human father = this.getFather();
        Human mother = this.getMother();
        if (father != null) {
            result.add(father);
        }
        if (mother != null) {
            result.add(mother);
        }
        if (father == null && mother == null) {
            result = null;
        }
        return result;
    }
    public void showParents() {
        List<Human> parents = this.getParents();
        if (parents == null) {
            System.out.printf("Родители %s %s  неизвестны\n", this.firstName, this.lastName);
        } else {
            System.out.printf("Родители %s %s: \n", this.firstName, this.lastName);
            for (Human parent : parents) {
                System.out.printf("%s %s %s\n", parent.firstName, parent.lastName, parent.gender);
            }
            System.out.printf("------\n");
        }
    }

    public Human getSpouse() {
        if (this.getChilds() == null) {
            return null;
        }

        Human child = this.getChilds().get(0);
        if (this.getGender() == Gender.man) {
            return child.getMother();
        }
        if (this.getGender() == Gender.woman) {
            return child.getFather();
        }
        return null;
    }

    public void showSpouse() {
        Human spouse = this.getSpouse();
        if (spouse == null) {
            System.out.printf("У %s %s нет супруги / супруга\n", this.firstName, this.lastName);
            return;
        }

        if (this.gender == Gender.man) {
            System.out.printf("Супругой %s %s является %s %s\n", this.firstName, this.lastName, spouse.firstName, spouse.lastName);
        } else if (this.gender == Gender.woman) {
            System.out.printf("Супругом %s %s является %s %s\n", this.firstName, this.lastName, spouse.firstName, spouse.lastName);
        }
    }
    public HashSet<Human> getSiblings(Gender gender) {
        HashSet<Human> result = new HashSet<>();
        Human father = this.getFather();
        Human mother = this.getMother();
        result.addAll(father.getChilds());
        result.addAll(mother.getChilds());
        result.remove(this);
        result.removeIf(human -> human.getGender() != gender);

        if (result.size() < 1) {
            return null;
        }
        return result;
    }
    public void showSiblibgs(Gender gender) {
        HashSet<Human> result = this.getSiblings(gender);
        if (result == null) {
            System.out.printf("У %s %s нет братьев / сестер", this.firstName, this.lastName);
            return;
        }

        System.out.printf("Братья / сестры %s %s: \n", this.firstName, this.lastName);
        for (Human human : result ) {
            System.out.printf("%s %s %s\n", human.firstName, human.lastName, human.gender);
        }
        System.out.printf("------\n");
    }

    public void showTreeParents() {
        if (this.getParents() == null) {
            System.out.printf("Предки %s %s неизвестны\n",this.firstName, this.lastName);
            return;
        }
        System.out.print("Все предки");
        this.generateTreeParents(this, "");
        System.out.println("------");
    }
    private void generateTreeParents(Human human, String spaces) {
        if (human == null) {
            return;
        }
        System.out.printf("%s %s %s\n", spaces, human.firstName, human.lastName);
        spaces += "  ";
        generateTreeParents(human.father, spaces);
        generateTreeParents(human.mother, spaces);
    }
    public void showTreeDescendants() {
        if (this.getChilds() == null) {
            System.out.printf("Потомки %s %s неизвестны\n",this.firstName, this.lastName);
            return;
        }
        System.out.printf("Все потомки");
        this.generateTreeDescendants(this, "");
        System.out.println("------");
    }
    private void generateTreeDescendants(Human human, String spaces) {
        if (human == null) {
            return;
        }
        System.out.printf("%s %s %s\n", spaces, human.firstName, human.lastName);
        spaces += "  ";
        for (Human child : human.childs) {
            generateTreeDescendants(child, spaces);
        }

    }
    public HashSet<Human> getUnclesAunts(Gender gender) {
        HashSet<Human> result = new HashSet<>();
        HashSet<Human> motherBrothersSisters = this.getMother().getSiblings(gender);
        HashSet<Human> fatherBrothersSisters = this.getFather().getSiblings(gender);

        if (motherBrothersSisters != null) {
            result.addAll(motherBrothersSisters);
        }
        if (fatherBrothersSisters != null) {
            result.addAll(fatherBrothersSisters);
        }

        if (result.size() < 1) {
            return null;
        }
        return result;
    }
    public void showUnclesAunts(Gender gender) {
        HashSet<Human> unclesAunts = this.getUnclesAunts(gender);
        if (unclesAunts.size() < 1) {
            System.out.printf("У %s %s нет дяди / тети\n", this.firstName, this.lastName);
            return;
        }
        System.out.printf("Дяди / тети %s %s:\n", this.firstName, this.lastName);
        for (Human uncleAunt : unclesAunts) {
            System.out.printf("%s %s %s\n", uncleAunt.firstName, uncleAunt.lastName, uncleAunt.gender);
        }
        System.out.println("-------");
    }
    @Override
    public String toString() {
        return String.format("id: %d, name: %s %s, gender: %s", this.id, this.firstName, this.lastName, this.gender);
    }
    @Override
    public boolean equals(Object human) {
        return this.id == ((Human) human).id;
    }
}
