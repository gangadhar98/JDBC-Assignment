import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AssignmentTwo {

    public static void main(String[] args) throws SQLException {
         //resources used
        Scanner scanner = new Scanner(System.in);
        java.sql.Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        //Database details
        String url = "jdbc:mysql://localhost:3306/customer";
        String username = "root";
        String password = "qwerty";

        //Menu
        System.out.println("Welcome to Employee data base management system");
        System.out.println("1. Enter the data into database");
        System.out.println("2. Retrieve the data from database");
        int choice = scanner.nextInt();

        try {
            connection =DriverManager.getConnection(url,username,password);
            if(connection != null)
            {
                    switch (choice)
                    {
                        case 1:
                            System.out.println("Enter the name of the Employee");
                            String name = scanner.next();
                            System.out.println("Enter the address of Employee");
                            String address = scanner.next();
                            System.out.println("Enter the Gender of the Employee");
                            String gender = scanner.next();
                            System.out.println("Enter the DOB of the Employee (dd-MM-yyyy)");
                            String sDob = scanner.next();
                            //parsing
                            SimpleDateFormat sdf = new SimpleDateFormat(sDob);
                            Date uDate = sdf.parse(sDob);
                            long time = uDate.getTime();
                            java.sql.Date sDate = new java.sql.Date(time);

                            System.out.println("Enter the DOJ of the Employee (MM-dd-yyyy)");
                            String sDoj = scanner.next();
                            //parsing
                            SimpleDateFormat sdf1 = new SimpleDateFormat(sDoj);
                            Date uDate1 = sdf1.parse(sDoj);
                            long time1 = uDate1.getTime();
                            java.sql.Date sDate1 = new java.sql.Date(time1);

                            System.out.println("Enter the DOM of the Employee (yyyy-MM-dd)");
                            String sDom = scanner.next();
                            java.sql.Date sDate2 = java.sql.Date.valueOf(sDom);
                            String insertQuery = "insert into employee values (?,?,?,?,?,?)";
                            pstmt = connection.prepareStatement(insertQuery);
                            if (pstmt != null) {
                                pstmt.setString(1, name);
                                pstmt.setString(2, address);
                                pstmt.setString(3, gender);
                                pstmt.setDate(4, sDate);
                                pstmt.setDate(5, sDate1);
                                pstmt.setDate(6, sDate2);
                                int noOfRows = pstmt.executeUpdate();
                                System.out.println("The no of  rows  updated are:" + noOfRows);
                            }
                            break;
                        case 2:
                            String getSqlQuery = "Select name,address,gender,DOB,DOJ,DOM from employee";
                            pstmt = connection.prepareStatement(getSqlQuery);

                            if(pstmt !=null)
                            {
                                result = pstmt.executeQuery();
                                if(result != null)
                                {
                                    if(result.next())
                                    {
                                        String getName = result.getString(1);
                                        String getAdd = result.getString(2);
                                        String getGender = result.getString(3);
                                        java.sql.Date userDOB = result.getDate(4);
                                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                                        String date = sdf2.format(userDOB);

                                        java.sql.Date userDOJ = result.getDate(5);
                                        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
                                        String dateDOJ = sdf3.format(userDOJ);

                                        java.sql.Date userDOM = result.getDate(6);
                                        System.out.println(getName+" "+getAdd+" "+getGender+" "+date+" "+dateDOJ+" "+userDOM);
                                    }
                                    else
                                    {
                                        System.out.println("Error");
                                    }

                                }
                            }
                            break;
                    }
            }
        } catch (SQLException | ParseException e)
        {
            System.out.println("Error message is"+e.getMessage());
        }finally
        {
            if(pstmt != null)
            {
                pstmt.close();
            }
            if(connection != null)
            {
                connection.close();
            }if(scanner != null)
            {
                scanner.close();
            }
        }
    }
}
