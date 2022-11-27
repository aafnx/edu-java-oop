import java.util.Iterator;
import java.util.List;

public class PersonIterator implements Iterator<Person> {
    private List<Person> personList;
    private int index = 0;

    public PersonIterator(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public boolean hasNext() {
        return index < personList.size();
    }

    @Override
    public Person next() {
        return personList.get(index++);
    }
}
