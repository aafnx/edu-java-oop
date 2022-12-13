import controller.Controller;
import model.*;
import view.View;

public class Main {
    public static void main(String[] args) {

        CreateTreeHuman createTreeHuman = new CreateTreeHuman();

        FamilyTreeRepository<Human> familyTreeRepository = new FamilyTreeRepository<>();
        familyTreeRepository.addAll(createTreeHuman.getList());
        Terminal terminal = new Terminal();

        Controller<Human> controller = new Controller<>();

        View view = new View(controller);

        controller.setTerminal(terminal);
        controller.setFamilyTreeRepository(familyTreeRepository);

        familyTreeRepository.setView(view);
        terminal.setView(view);

        view.init();
    }
}
