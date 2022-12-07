package model;

public interface FamilyTreeShowable {
    void show(String name);
    void showFather(String name);
    void showMother(String name);
    void showParents(String name);
    void showSpouse(String name);
    void showChildren(String name);
    void showSiblings(String name, Gender gender);
    void showUnclesAunts(String name, Gender gender);
    void showTreeParents(String name);
    void showTreeDescendants(String name);
    void showAll();
}
