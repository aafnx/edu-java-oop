import java.util.ArrayList;
import java.util.List;

public class Human {
    private List<FamilyCommunication> listCommunication = new ArrayList<>();

    public void addCommunication(FamilyCommunication communication){
        listCommunication.add(communication);
    }

    public void addCommunication(Human human, TypeComunication typeComunication){
        addCommunication(new FamilyCommunication(human, typeComunication));
    }

    public void showCommunication(TypeComunication typeComunication){
        for (FamilyCommunication communication: listCommunication){
            if (communication.getTypeComunication() == typeComunication){
                System.out.println(communication.getHuman());
            }
        }
    }
}
