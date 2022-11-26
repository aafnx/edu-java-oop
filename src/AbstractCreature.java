import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class AbstractCreature implements FamilyTreeShowable {
    private static int count;
    protected final int id;
    protected String firstName;
    protected Gender gender;

    protected AbstractCreature father;
    protected AbstractCreature mother;
    protected List<AbstractCreature> children;

    static {
        count = 1;
    }

    public AbstractCreature(String firstName, Gender gender) {
        this.id = count++;
        this.setFirstName(firstName);
        this.setGender(gender);
        this.initChildren();
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void initChildren() {
        this.children = new ArrayList<>();
    }
    public void addChild(AbstractCreature child) {
        this.children.add(child);
    }
    public void setFather(AbstractCreature father) {
        if (father.gender == Gender.man) {
            this.father = father;
            this.father.addChild(this);
        } else {
            System.out.println("Введено некорректное значение");
        }
    }

    public void setMother(AbstractCreature mother) {
        if (mother.gender == Gender.woman) {
            this.mother = mother;
            this.mother.addChild(this);
        } else {
            System.out.println("Введено некорректное значение");
        }
    }
    public int getId() { return this.id; }
    public Gender getGender() {
        return this.gender;
    }
    public List<AbstractCreature> getChildren() {
        if (this.children.size() == 0) {
            return null;
        }
        return this.children;
    }
    public void showChildren() {
        List<AbstractCreature> children = this.getChildren();
        if (children == null) {
//            System.out.printf("У %s, нет детей\n", this.firstName);
            System.out.printf("У %s нет детей\n", this);
        } else {
            System.out.printf("Дети %s:\n", this);
            for (AbstractCreature child : children) {
//                System.out.printf("%s %s %s\n", child.firstName, child.lastName, child.gender);
                System.out.println(child);
            }
            System.out.print("-------\n");
        }
    }
    public AbstractCreature getFather() {
        return this.father;
    }

    public AbstractCreature getMother() {
        return this.mother;
    }

    public List<AbstractCreature> getParents() {
        List<AbstractCreature> result = new ArrayList<>();
        AbstractCreature father = this.getFather();
        AbstractCreature mother = this.getMother();
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
        List<AbstractCreature> parents = this.getParents();
        if (parents == null) {
            System.out.printf("Родители %s неизвестны\n", this);
        } else {
            System.out.printf("Родители %s: \n", this);
            for (AbstractCreature parent : parents) {
//                System.out.printf("%s %s %s\n", parent.firstName, parent.lastName, parent.gender);
                System.out.println(parent);
            }
            System.out.print("------\n");
        }
    }
    public AbstractCreature getSpouse() {
        if (this.getChildren() == null) {
            return null;
        }

        AbstractCreature child = this.getChildren().get(0);
        if (this.getGender() == Gender.man) {
            return child.getMother();
        }
        if (this.getGender() == Gender.woman) {
            return child.getFather();
        }
        return null;
    }

    public void showSpouse() {
        AbstractCreature spouse = this.getSpouse();
        String word = this.gender == Gender.man ? "супруга" : "супруг";
        if (spouse == null) {
            System.out.printf("У %s отсутсвует %s\n", this, word);
            return;
        }
        System.out.printf("У %s есть %s – %s \n", this, word, spouse);
    }
    public HashSet<AbstractCreature> getSiblings(Gender gender) {
        HashSet<AbstractCreature> result = new HashSet<>();
        AbstractCreature father = this.getFather();
        AbstractCreature mother = this.getMother();
        result.addAll(father.getChildren());
        result.addAll(mother.getChildren());
        result.remove(this);
        result.removeIf(human -> human.getGender() != gender);

        if (result.size() == 0) {
            return null;
        }
        return result;
    }
    public void showSiblings(Gender gender) {
        HashSet<AbstractCreature> result = this.getSiblings(gender);
        String word = gender == Gender.man ? "брат" : "сестра";
        if (result == null) {
            System.out.printf("У %s нет %s", this, word);
            return;
        }

        System.out.printf("У %s есть %s :\n", this, word);
        for (AbstractCreature person : result ) {
//            System.out.printf("%s %s %s\n", human.firstName, human.lastName, human.gender);
            System.out.println(person);
        }
        System.out.print("------\n");
    }

    public void showTreeParents() {
        if (this.getParents() == null) {
            System.out.printf("Предки %s неизвестны\n", this);
            return;
        }
        System.out.print("Все предки ");
        this.generateTreeParents(this, "");
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
    public void showTreeDescendants() {
        if (this.getChildren() == null) {
            System.out.printf("Потомки %s неизвестны\n",this);
            return;
        }
        System.out.print("Все потомки ");
        this.generateTreeDescendants(this, "");
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
    public HashSet<AbstractCreature> getUnclesAunts(Gender gender) {
        HashSet<AbstractCreature> result = new HashSet<>();
        HashSet<AbstractCreature> motherBrothersSisters = this.getMother().getSiblings(gender);
        HashSet<AbstractCreature> fatherBrothersSisters = this.getFather().getSiblings(gender);

        if (motherBrothersSisters != null) {
            result.addAll(motherBrothersSisters);
        }
        if (fatherBrothersSisters != null) {
            result.addAll(fatherBrothersSisters);
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }
    public void showUnclesAunts(Gender gender) {
        HashSet<AbstractCreature> unclesOrAunts = this.getUnclesAunts(gender);
        String word = gender == Gender.man ? "дядя" : "тетя";
        if (unclesOrAunts == null) {
            System.out.printf("У %s нет %s\n", this, word);
            return;
        }
        System.out.printf("У %s есть %s :\n", this, word);
        for (AbstractCreature uncleOrAunt : unclesOrAunts) {
//            System.out.printf("%s %s %s\n", uncleAunt.firstName, uncleAunt.lastName, uncleAunt.gender);
            System.out.println(uncleOrAunt);
        }
        System.out.println("-------");
    }
    @Override
    public String toString() {
        return String.format("name: %s, gender: %s", this.firstName, this.gender);
    }
    @Override
    public boolean equals(Object person) {
        return this.getId() == ((AbstractCreature) person).getId();
    }
}
