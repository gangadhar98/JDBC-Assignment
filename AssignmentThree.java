import java.sql.*;
import java.util.Scanner;

public class AssignmentThree {
    public static void main(String[] args) throws SQLException {
        //Components used in the project
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        //Menu
        System.out.println("Welcome to the Customer Data Management System");
        System.out.println("Menu:");
        System.out.println("1. Add Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Read the data from customer table");
        System.out.println("Enter the choice");
        int choice = scanner.nextInt();

        //Database details
        String url = "jdbc:mysql://localhost:3306/customer";
        String username = "root";
        String password = "qwerty";
        String insertSqlQuery = "insert into customers values(?,?,?)";


        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null)
                switch (choice)
                {
                    //Inserting data into DB
                    case 1:
                        System.out.println("Enter the ID of the customer");
                        int cid = scanner.nextInt();
                        System.out.println("Enter the name of the customer");
                        String cname = scanner.next();
                        System.out.println("Enter the age of the customer");
                        int cage = scanner.nextInt();
                    pstmt = connection.prepareStatement(insertSqlQuery);
                    if(pstmt !=null)
                    {
                        pstmt.setInt(1,cid);
                        pstmt.setString(2,cname);
                        pstmt.setInt(3,cage);
                        int noOfRows = pstmt.executeUpdate();
                        System.out.println("No of rows added are:"+noOfRows);
                        System.out.println("Data has been added to the Database, Thank you!");
                    }
                    break;
                    //Updating data in Database
                    case 2:
                        System.out.println("Enter the ID of the customer");
                        int cid1 = scanner.nextInt();
                        System.out.println("Enter the name of the customer");
                        String cname1 = scanner.next();
                        System.out.println("Enter the age of the customer");
                        int cage1 = scanner.nextInt();
                        String updateSqlQuery = ("update customers set cname = ?, cage = ? where cid = ?");
                        pstmt = connection.prepareStatement(updateSqlQuery);
                        if(pstmt != null)
                        {
                            pstmt.setString(1,cname1);
                            pstmt.setInt(2,cage1);
                            pstmt.setInt(3,cid1);
                            int noOfRows = pstmt.executeUpdate();
                            System.out.println("No of rows updated are:"+noOfRows);
                            System.out.println("Data has been updated in Database, Thank you!");
                        }
                        break;

                    //Deleting data from DB
                    case 3:
                        System.out.println("Enter the cid of the customer:");
                        int cid3 = scanner.nextInt();
                        String deleteSqlQuery = "delete from customers where cid =?";
                        pstmt = connection.prepareStatement(deleteSqlQuery);
                        if(pstmt != null)
                        {
                            pstmt.setInt(1,cid3);
                            int noOfRows = pstmt.executeUpdate();
                            System.out.println("No of rows deleted are:"+noOfRows);
                            System.out.println("Data has been deleted from Database, Thank you!");
                        }
                        break;

                    //Getting data from DB
                    case 4:
                        String getSqlQuery = "select cid,cname,cage from customers";
                        pstmt = connection.prepareStatement(getSqlQuery);
                        if(pstmt !=null)
                            result = pstmt.executeQuery();
                        while(result.next())
                        {
                           int cid4 = result.getInt(1);
                           String cname4 = result.getString(2);
                           int cage4 = result.getInt(3);
                           System.out.println(cid4+" "+cname4+" "+cage4);
                        }
                        break;
                }
        //Handling the exception
        } catch (SQLException e) {
            System.out.println("Error is :"+e.getMessage());
        }
        //Closing the resources
        finally {
            if(pstmt!=null)
            {
                pstmt.close();
            }
            if(connection !=null)
            {
                connection.close();
            }
            if(scanner !=null)
            {
                scanner.close();
            }
        }
    }
}