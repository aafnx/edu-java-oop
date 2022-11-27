import java.util.Iterator;

public class Person {
    private int id;
    private static int count;
    private String name;
    private String job;
    static {
        count = 1;
    }
    public Person(String name, String job) {
        this.id = count++;
        this.name = name;
        this.job = job;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return String.format("id: %d, name: %s, job: %s", this.getId(), this.getName(), this.getJob());
    }
}
