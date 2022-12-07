package model;

import java.util.List;

public interface FamilyTreeGeneratable<T extends AbstractCreature> {
    boolean setFather(T father);
    T getFather();
    boolean setMother(T mother);
    T getMother();
    void addChild(T child);
    List<T> getChildren();
}
