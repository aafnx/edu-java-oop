// Провести рефактор используя SOLID

import controller.Controller;
import model.*;
import view.View;

public class Main {
    public static void main(String[] args) {

        CreateTreeHuman createTreeHuman = new CreateTreeHuman();
        FamilyTreeRepository<Human> familyTreeRepository = new FamilyTreeRepository<>();
        familyTreeRepository.addAll(createTreeHuman.getList());


        Controller<Human> controller = new Controller<>(familyTreeRepository);
        controller.setTerminal(new Terminal());
        View<Human> view = new View<>(controller);
        view.init();

    }
}
