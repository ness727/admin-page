package idusw.javaweb.b202012047.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

// application context('/')와 webapp의 레벨이 같음
@WebServlet(name = "cinemaServlet", urlPatterns = "/landing")
public class CinemaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("당신의 영화는 = ");

//        request.getParameterNames().asIterator()
//                .forEachRemaining(name -> System.out.println("name = " + name + ": value = " + request.getParameter(name)));

        request.setAttribute("top", request.getParameter("movie"));
        request.setAttribute("bottom", "내가 좋아하는 영화");
        request.getRequestDispatcher("landing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
