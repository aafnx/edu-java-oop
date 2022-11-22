public class FamilyCommunication {
    private Human human;
    private TypeComunication typeComunication;

    public FamilyCommunication(Human human, TypeComunication typeComunication) {
        this.human = human;
        this.typeComunication = typeComunication;
    }

    public Human getHuman() {
        return human;
    }

    public TypeComunication getTypeComunication() {
        return typeComunication;
    }
}
