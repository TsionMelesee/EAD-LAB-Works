package jdbc_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "root123";
        String databaseName = "StudentsDB";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String createDatabaseSQL = "CREATE DATABASE " + databaseName;
            statement.executeUpdate(createDatabaseSQL);

            System.out.println("Database '" + databaseName + "' created successfully.");
            statement.close();
            connection.close();

            String jdbcURL = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(jdbcURL, username, password);
            statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE teacher1 (" +
                                    "id int auto_increment primary key," +
                                    "first_name varchar(255)," +
                                    "last_name varchar(255)," +
                                    "school varchar(255)," +
                                    "hire_date date," +
                                    "salary decimal(10, 2))";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'teacher1' created successfully.");
            String[] insertStatements = {
                "INSERT INTO teacher1(first_name, last_name, school, hire_date, salary) " +
                "VALUES('Aster', 'Nega', 'Yekatit 12', '2021-01-01', 8000)",
                "INSERT INTO teacher1(first_name, last_name, school, hire_date, salary) " +
                "VALUES('Jemal', 'Edris', 'Bole', '2021-09-11', 20000)",
                "INSERT INTO teacher1(first_name, last_name, school, hire_date, salary) " +
                "VALUES('Haile', 'Anaol', 'Saris', '2022-01-22', 15000)",
                "INSERT INTO teacher1(first_name, last_name, school, hire_date, salary) " +
                "VALUES('Teddy', 'Anbesaw', 'Bole', '2021-01-01', 8000)"
            };
            for (String stmt : insertStatements) {
                statement.executeUpdate(stmt);
            }

            System.out.println("Data inserted successfully.");
            String query = "SELECT * FROM teacher1";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("id");
                String fname = res.getString("first_name");
                java.sql.Date hire_date = res.getDate("hire_date");
                double salary = res.getDouble("salary");
                
                System.out.println("Teacher ID: " + id);
                System.out.println("Teacher Name: " + fname);
                System.out.println("Teacher Hire Date: " + hire_date);
                System.out.println("Teacher Salary: " + salary);
                System.out.println("..............."); 
            }

            res.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
