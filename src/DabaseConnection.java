import java.sql.*;
import java.util.Scanner;

public class DabaseConnection {

   static Connection connection;
   private static String SQLusername = "sql9205470";
   private static String SQLpassword = "G7IJzs4aeP";
   private final static String SQLurl = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9205470";
   private static PreparedStatement preparedStatement;
   private enum OPTIONS { CONNECTDB, READDB, CREATENEWUSER, EXIT, NO_CHOICE };

    public static void main(String [] arguments){
          OPTIONS choice = null;
           do{
               choice = displayMenu();
               switch(choice){
                   case CONNECTDB:{
                       connectDataBase();
                       break;
                   }
                   case READDB:{
                       readDataBase();
                       break;
                   }
                   case CREATENEWUSER:{
                       createNewUserMenu();
                       break;
                   }
                   case EXIT:{
                       break;
                   }
                   default:{
                       System.out.println("NOT A VALID CHOICE");
                   }
               }

           }while(!(choice.equals(OPTIONS.EXIT)));



    }

    private static void createNewUserMenu() {
    }

    private static OPTIONS displayMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Connect to DB");
        System.out.println("2 - Read from DB");
        System.out.println("3 - Add new User");
        System.out.println("4 - Quit");

        selection = input.nextInt();

        switch (selection){
            case 1:{
                return OPTIONS.CONNECTDB;
            }
            case 2:{
                return OPTIONS.READDB;
            }
            case 3:{
                return OPTIONS.CREATENEWUSER;
            }
            case 4:{
                return OPTIONS.EXIT;
            }
            default:{
                return OPTIONS.NO_CHOICE;
            }
        }

    }

    private static void deleteDataBase() {
        String sqlStatement = "TRUNCATE TABLE Users";
        try {
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to Truncate Databse, make sure you are connected");
        }
    }

    private static void readDataBase() {
        String sqlStatement = "SELECT * FROM Users";
        try {
            preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Failed to read from database, check connection");
        }
    }

    private static void createNewUser(String fName, String lName) {
        User newUser = new User(fName,lName);
        while(!insertIntoDatabase(newUser.getID(), newUser.getUsername(), newUser.getPassword())){
            newUser.newUserID();
            System.out.println("ERROR INSERTING INTO DB, TRYING AGAIN");
        }
    }

    private static boolean insertIntoDatabase(int ID, String username, String password){
       try{
            String updateString = "INSERT INTO Users VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(updateString);
           preparedStatement.setInt(1,ID);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3, password);

           preparedStatement.executeUpdate();

            return true;

        }catch (SQLException ex){
           System.out.println(ex.getSQLState());
           System.out.println(ex.getErrorCode());
            return false;
        }
    }

    private static void connectDataBase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(SQLurl,SQLusername, SQLpassword);
            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
