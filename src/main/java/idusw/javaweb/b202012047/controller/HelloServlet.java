package idusw.javaweb.b202012047.controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (request.getParameter("title") != null && request.getParameter("name") != null)
            out.println("<h1>" + message +
                    request.getParameter("title") +
                    request.getParameter("name") +
                    "</h1>");
        else
            out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        System.out.println("한글은?");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(request.getParameter("title") + " : " + request.getParameter("name"));
    }

    public void destroy() {
    }
}