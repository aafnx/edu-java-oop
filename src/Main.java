// 1. Создать метод, который принимает массив int и сортирует его
// по последней цифре. Используйте метод Arrays.sort.
// Для его работы создайте свой компаратор. Имеется в виду последняя цифра
// в записи числа, например в числе 123, последння цифра 3.
// Надо сделать сортировку, которая учитывает только эту последнюю цифру в числе.

// 2. Создайте класс, который представляет из себя коллекцию,
// добавьте 2 метода add и get для работы с коллекцией.
// Реализуйте возможность использования цикла for-each
// для работы с данной коллекцией.
// Для этого реализуйте интерфейс Iterable и создайте итератор


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{234, 56, 10, 6723, 97, 2, 348, -19};
        printArr(arr);
        sort(arr);
        printArr(arr);

        DataBasePersons db = new DataBasePersons();
        db.add(new Person("Jack", "Programmer"));
        db.add(new Person("Petr", "Builder"));
        db.add(new Person("Mohamad", "Politic"));

        for (Person person : db) {
            System.out.println(person);
        }
    }
    public static void sort(int[] arr) {
        Integer[] arrWrapper = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrWrapper[i] = arr[i];
        }
        Arrays.sort(arrWrapper, new ArrayComparatorByLastDigit());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrWrapper[i];
        }
    }
    public static void printArr(int[] arr) {
        for (int n : arr) {
            System.out.printf("%d ", n);
        }
        System.out.println();
    }
}
