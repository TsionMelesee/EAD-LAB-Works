package com.book.servelet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/*") 
public class DispatcherServle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Path not provided");
            return;
        }

        switch (path) {
            case "/registerBook":
                request.getRequestDispatcher("/registerBook").forward(request, response);
                break;

            case "/displayBooks":
                request.getRequestDispatcher("/displayBooks").forward(request, response);
                break;

            case "/deleteBook":
                request.getRequestDispatcher("/deleteBook").forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No matching servlet ");
        }
    }
}
