import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataBasePersons implements Iterable<Person> {

    private List<Person> dataBase;

    public DataBasePersons() {
        this.dataBase = new ArrayList<>();
    }

    public void add(Person item) {
        dataBase.add(item);
    }

    public Person get(int index) {
            return dataBase.get(index);
    }
    public Person getByName(String name) {
        for (Person person : dataBase) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        return null;
    }


    @Override
    public Iterator<Person> iterator() {
        return new PersonIterator(dataBase);
    }
}
