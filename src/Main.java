// Продолжаем грейдить наш проект с гениологическим древом.
// Изменить древо, сделать класс параметизированным.
// Продумать класс общения с пользователем, сделать набор команд, для операций над деревом

import controller.Controller;
import model.*;
import view.View;

public class Main {
    public static void main(String[] args) {

        CreateTreeHuman createTreeHuman = new CreateTreeHuman();
        FamilyTree<Human> familyTree = new FamilyTree<>();
        familyTree.addAll(createTreeHuman.getList());


        Controller<Human> controller = new Controller<>(familyTree, new Terminal());
        View<Human> view = new View<>(controller);
        view.init();
    }
}
