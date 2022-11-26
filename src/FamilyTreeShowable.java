import java.util.HashSet;
import java.util.List;

public interface FamilyTreeShowable {
    void showChildren();
    void showParents();
    void showSpouse();
    void showSiblings(Gender gender);
    void showTreeParents();
    void showTreeDescendants();
    void showUnclesAunts(Gender gender);
}
