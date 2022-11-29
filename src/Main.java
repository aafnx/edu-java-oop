// Продолжаем грейдить наш проект с гениологическим древом.
// Изменить древо, сделать класс параметизированным.
// Продумать класс общения с пользователем, сделать набор команд,
// для операций над деревом

public class Main {
    public static void main(String[] args) {
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

        System.out.println(NikitaIvanov);

        NikitaIvanov.showTreeParents();
        RomanPetrov.showTreeDescendants();
        AlexeyKazanskiy.showSiblings(Gender.woman);
        NikitaIvanov.showUnclesAunts(Gender.man);
        IvanIvanov.showSpouse();
        KonstantinKazanskiy.showChildren();
        IvanIvanov.showParents();
        NikitaIvanov.showSpouse();

        Cat barsik = new Cat("barsik", Gender.man);
        Cat pushok = new Cat("pushok", Gender.woman);
        pushok.setFather(barsik);
        barsik.showTreeDescendants();
    }
}
