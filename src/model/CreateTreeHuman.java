package model;

import java.util.ArrayList;
import java.util.List;

public class CreateTreeHuman {
    private List<Human> list;
    public CreateTreeHuman() {
        Human NikitaIvanov = new Human("Nikita", "Ivanov", Gender.man);

        Human IvanIvanov = new Human("Ivan", "Ivanov", Gender.man);
        Human IrinaIvanova = new Human("Irina", "Ivanova", Gender.woman);
        Human AlexeyKazanskiy = new Human("Alexey", "Kazanskiy", Gender.man);

        Human PetrIvanov = new Human("Petr", "Ivanov", Gender.man);
        Human MariaAndroonova = new Human("Maria", "Androonova", Gender.woman);
        Human KonstantinKazanskiy = new Human("Konstantin", "Kazanskiy", Gender.man);
        Human SvetlanaGagarina = new Human("Svetlana", "Gagarina", Gender.woman);

        Human RomanPetrov = new Human("Roman", "Petrov", Gender.man);

        NikitaIvanov.setFather(IvanIvanov);
        NikitaIvanov.setMother(IrinaIvanova);

        IvanIvanov.setFather(PetrIvanov);
        IvanIvanov.setMother(MariaAndroonova);
        IrinaIvanova.setFather(KonstantinKazanskiy);
        IrinaIvanova.setMother(SvetlanaGagarina);
        AlexeyKazanskiy.setFather(KonstantinKazanskiy);
        AlexeyKazanskiy.setMother(SvetlanaGagarina);

        SvetlanaGagarina.setFather(RomanPetrov);

        this.list = new ArrayList<>();

        list.add(NikitaIvanov);
        list.add(IvanIvanov);
        list.add(IrinaIvanova);
        list.add(AlexeyKazanskiy);
        list.add(PetrIvanov);
        list.add(MariaAndroonova);
        list.add(KonstantinKazanskiy);
        list.add(SvetlanaGagarina);
        list.add(RomanPetrov);
    }
    public List<Human> getList() {
        return this.list;
    }
}
