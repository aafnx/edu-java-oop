// Продолжаем грейдить наш проект с гениологическим древом.
// Изменить древо, сделать класс параметизированным.
// Продумать класс общения с пользователем, сделать набор команд, для операций над деревом

import controller.Controller;
import model.*;
import view.View;

public class Main {
    public static void main(String[] args) {

        CreateTreeHuman createTreeHuman = new CreateTreeHuman();
        FamilyTreeRepository<Human> familyTreeRepository = new FamilyTreeRepository<>();
        familyTreeRepository.addAll(createTreeHuman.getList());


        Controller<Human> controller = new Controller<>(familyTreeRepository, new Terminal());
        View<Human> view = new View<>(controller);
        view.init();

    }
}
