public class Human extends AbstractCreature {
    private String lastName;

    public Human(String firstName, String lastName, Gender gender) {
        super(firstName, gender);
        this.setLastName(lastName);
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", super.firstName, this.lastName, super.gender);
    }
}
