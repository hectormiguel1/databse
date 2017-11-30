import java.sql.*;
import java.util.Scanner;

public class DabaseConnection {

   static Connection connection;
    private static String SQLusername = "UserManagerSFTW";
    private static String SQLpassword = "7895123.zZ";
    private final static String SQLurl = "jdbc:mysql://localhost:3306/Users";
   private static PreparedStatement preparedStatement;
   private enum OPTIONS { CONNECTDB, READDB, CREATENEWUSER, EXIT, NO_CHOICE };

    public static void main(String [] arguments){
        GraphicalEnvironment ui = new GraphicalEnvironment();
        ui.lunch();
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

    protected static boolean insertIntoDatabase(User newUser) {
       try{
            String updateString = "INSERT INTO Users VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(updateString);
           preparedStatement.setInt(1, newUser.getID());
           preparedStatement.setString(2, newUser.getUsername());
           preparedStatement.setString(3, newUser.getPassword());

           preparedStatement.executeUpdate();

            return true;

        }catch (SQLException ex){
           System.out.println(ex.getSQLState());
           System.out.println(ex.getErrorCode());
            return false;
        }
    }

    protected static boolean connectDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(SQLurl,SQLusername, SQLpassword);
            System.out.println("Connected");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
