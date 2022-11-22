import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// @Liberate520
// мой тг

public class Main{
    public static void main(String[] args) {
//        Cat cat1 = new Cat();
//        Dog dog1 = new Dog();
//        List<Speakable> list = new ArrayList<>();
//        list.add(cat1);
//        list.add(dog1);
//        for (Speakable speakable: list){
//            speakable.speak();
//        }
//        List<String> list1 = new ArrayList<>();
//        List<String> list2 = new LinkedList<>();
//
//        sort(list1);
//        IEngine engine = new Engine();
//        Car car = new Car(engine);

        Human human1 = new Human();
        Human human2 = new Human();
        human1.addCommunication(human2, TypeComunication.Son);
        human1.showCommunication(TypeComunication.Son);
    }

    public static void sort(List<String> list){
        Collections.sort(list);
    }
}
