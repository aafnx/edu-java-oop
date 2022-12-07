package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class AbstractCreature implements FamilyTreeGeneratable<AbstractCreature> {
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
    public String getName() {
        return this.firstName;
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
    public boolean setFather(AbstractCreature father) {
        if (father.gender == Gender.man) {
            this.father = father;
            this.father.addChild(this);
            return true;
        }
        return false;
    }

    public boolean setMother(AbstractCreature mother) {
        if (mother.gender == Gender.woman) {
            this.mother = mother;
            this.mother.addChild(this);
            return true;
        }
        return false;
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

    public HashSet<AbstractCreature> getSiblings(Gender gender) {
        HashSet<AbstractCreature> result = new HashSet<>();
        AbstractCreature father = this.getFather();
        AbstractCreature mother = this.getMother();
        if (father != null) {
            result.addAll(father.getChildren());
        }
        if (mother != null) {
            result.addAll(mother.getChildren());
        }
        result.remove(this);
        result.removeIf(human -> human.getGender() != gender);

        if (result.size() == 0) {
            return null;
        }
        return result;
    }
    public HashSet<AbstractCreature> getUnclesAunts(Gender gender) {
        HashSet<AbstractCreature> result = new HashSet<>();
        HashSet<AbstractCreature> motherBrothersSisters = null;
        HashSet<AbstractCreature> fatherBrothersSisters = null;
        if (this.getMother() != null) {
            motherBrothersSisters = this.getMother().getSiblings(gender);
        }
        if (this.getFather() != null) {
            fatherBrothersSisters = this.getFather().getSiblings(gender);
        }

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





    @Override
    public String toString() {
        return String.format("name: %s, gender: %s", this.firstName, this.gender);
    }
    @Override
    public boolean equals(Object person) {
        return this.getId() == ((AbstractCreature) person).getId();
    }
}
