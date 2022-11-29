import java.util.HashSet;
import java.util.List;

public interface FamilyTreeShowable<T extends AbstractCreature> {
    T getFather();
    T getMother();
    List<T> getParents();
    T getSpouse();
    HashSet<T> getSiblings(Gender gender);
    HashSet<T> getUnclesAunts(Gender gender);
    void showChildren();
    void showParents();
    void showSpouse();
    void showSiblings(Gender gender);
    void showTreeParents();
    void showTreeDescendants();
    void showUnclesAunts(Gender gender);
}
