package com.book.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.book.db.DBConnectionManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerBook")
public class BookRegistrationServletIoc extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DBConnectionManager dbManager;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dbManager = context.getBean("dbConnectionManager", DBConnectionManager.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price;

        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException e) {
            response.setContentType("text/html");
            response.getWriter().println("<h2>Error: Invalid price format.</h2>");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            dbManager.openConnection();
            Connection conn = dbManager.getConnection();

            String query = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setDouble(3, price);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                out.println("Book registered successfully!");
            } else {
                out.println("Failed to register the book.");
            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred while registering the book.");
        } finally {
            try {
                dbManager.closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
