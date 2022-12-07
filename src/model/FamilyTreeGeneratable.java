package model;

import java.util.List;

public interface FamilyTreeGeneratable<T extends AbstractCreature> {
    T getFather();
    T getMother();
    List<T> getChildren();
}
