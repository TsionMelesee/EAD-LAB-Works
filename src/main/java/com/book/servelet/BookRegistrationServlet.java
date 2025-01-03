package com.book.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.book.db.DBConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class BookRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DBConnectionManager dbManager = new DBConnectionManager();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            dbManager.openConnection();
            Connection connection = dbManager.getConnection();

            String sql = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setDouble(3, price);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                out.println("Book registered successfully");
            } else {
                out.println("Something went wrong");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>An error occurred: " + e.getMessage() + "</h2>");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                dbManager.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
