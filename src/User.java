import java.util.Random;

public class User {

    private int ID;
    private String username;
    private String password;
    private String fName;
    private String lName;
    private final int PASSWORDLENGTH = 10;

    public User(int ID, String username, String password, String fName, String lName) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
    }

    public User(String fName, String lName) {
        this.ID = generateID();
        this.fName = fName;
        this.lName = lName;
        this.username = generateUsername();
        this.password = generatePassword();

    }

    public User(int ID, String fName, String lName) {
        this.ID = ID;
        this.fName = fName;
        this.lName = lName;
    }

    public User(int ID, String password, String fName, String lName) {
        this.ID = generateID();
        this.password = generatePassword();
        this.fName = fName;
        this.lName = lName;
        this.username = getUsername();
        System.out.println("HERE!");
    }

    private String generatePassword() {
        return new RandomString(PASSWORDLENGTH,new Random()).nextString();
    }

    private String generateUsername() {
        char[] fnamearray = fName.toUpperCase().toCharArray();
        return fnamearray[new Random().nextInt(fnamearray.length)] + lName + new Random().nextInt(1000);

    }

    private int generateID() {
        return new Random().nextInt(1000000000) + 10000;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void newUserID(){
        ID = generateID();
    }
}
