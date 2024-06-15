package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String icno = request.getParameter("icno");
        String firstname = request.getParameter("firstname");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CF3107", "root", "password");
            String sql = "INSERT INTO userprofile (username, icno, firstname) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, icno);
            pstmt.setString(3, firstname);

            int result = pstmt.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (result > 0) {
                out.println("<h1>Profile registered successfully!</h1>");
                out.println("<p>Username: " + username + "</p>");
                out.println("<p>IC No: " + icno + "</p>");
                out.println("<p>First Name: " + firstname + "</p>");
            } else {
                out.println("<h1>Profile registration failed!</h1>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

