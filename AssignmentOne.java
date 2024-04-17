import java.sql.*;
import java.util.Scanner;

public class AssignmentOne {
    public static void main(String[] args) throws SQLException {

        //Components used in Project
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        //DB Details
        String url = "jdbc:mysql://localhost:3306/customer";
        String username = "root";
        String password = "qwerty";

        //Menu
        System.out.println("Welcome to the Customer Table");
        System.out.println("Menu:");
        System.out.println("1. Add Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Read the data from customer table");
        System.out.println("Enter the choice");
        int choice = scanner.nextInt();

        try{
            connection = DriverManager.getConnection(url,username,password);
            if(connection != null)
            {
               statement = connection.createStatement();
                {
                    if(statement != null)
                    {
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

                                String insertSqlQuery = String.format("insert into customers values (%d,'%s',%d)",cid,cname,cage);
                                int noOfRows = statement.executeUpdate(insertSqlQuery);
                                System.out.println("The now of rows affected are:" +noOfRows);
                                System.out.println("Data has been added, Thank you!");
                                break;
                            //Updating data in DB
                            case 2:
                                System.out.println("Enter the ID of the customer");
                                int cid1 = scanner.nextInt();
                                System.out.println("Enter the name of the customer");
                                String cname1 = scanner.next();
                                System.out.println("Enter the age of the customer");
                                int cage1 = scanner.nextInt();
                                String updateSqlQuery =String.format("update customers set cname = '%s', cage = %d where cid = %d",cname1,cage1,cid1);
                                int noOfRows1 = statement.executeUpdate(updateSqlQuery);
                                System.out.println("The number of rows updated" +noOfRows1);
                                System.out.println("Data has been updated, Thank you!");
                                break;

                            //Deleting data from DB
                            case 3:
                                System.out.println("Enter the ID of the customer");
                                int cid2 = scanner.nextInt();
                                String deleteSqlQuery = String.format("delete from customers where cid = %d",cid2);
                                int noOfRowsDeleted = statement.executeUpdate(deleteSqlQuery);
                                System.out.println("The no of rows deleted are:" +noOfRowsDeleted);
                                System.out.println("Data has been deleted, Thank you!");
                                break;

                            //Get data from DB
                            case 4:
                                String sqlQuery = "select cid,cname,cage from customers";
                                result = statement.executeQuery(sqlQuery);
                                while(result.next())
                                {
                                    int cid3 = result.getInt(1);
                                    String cname3 = result.getString(2);
                                    int cage3 = result.getInt(3);
                                    System.out.println(cid3+" "+cname3+" "+cage3);
                                }
                        }
                    }
                }
            }
        //Handling exception
        }catch (SQLException se)
        {
            System.out.println("Error is"+se.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("The error is"+e.getMessage());
        }
        //closing the resources
        finally {
            if(result != null)
                result.close();
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
            if(scanner != null )
                scanner.close();
        }

    }
}
