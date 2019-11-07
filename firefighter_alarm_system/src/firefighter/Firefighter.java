package firefighter;

public class Firefighter implements IFirefighter {
    String name;
    String surname;
    String phoneNumber;

    public Firefighter(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendSms() {
        System.out.println("Wysyłam SMS do " + this.name + " " + this.surname);
    }

}
