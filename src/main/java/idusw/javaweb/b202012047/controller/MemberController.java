package idusw.javaweb.b202012047.controller;

import idusw.javaweb.b202012047.model.MemberDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

// Controller : 요청의 흐름을 제어하는 역할을 수행
// 데이터 처리 기본 연산 : C.R.U.D (create, read, update, delete)
// HTTP Method (Rest API 관련이 높음) : post, get, (update, delete) - jsp에서는 지원에 문제가 있음
// http method - view vs process
@WebServlet(name = "memberController", urlPatterns = {
        "/members/post-form", "/members/register", "/members/get-list",
        "/members/detail", "/members/update", "/members/delete",
        "/members/login-form", "/members/login", "/members/logout"})
public class MemberController extends HttpServlet {
    // 다형성을 활용할 수 있다.
    // Generics를 사용하고 있음. 컴파일 시점에 유형 문제를 처리할 수 있다.
    List<MemberDTO> memberDTOList = new ArrayList<>();

    // JDBC(Java Database Connectivity) : 자바 기반 데이터베이스 활용을 위한 API
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getConnection();

        int index = request.getRequestURI().lastIndexOf("/");  // 마지막으로 나온 '/'의 위치 인덱스
        String command = request.getRequestURI().substring(index + 1);
        System.out.println(command);
        
        HttpSession session = request.getSession(); 
        // HttpSession 객체를 가져옴, 없는 경우 생성
        // session은 페이지에 구애받지 않고 일정 시간동안 유지됨
        // request는 페이지가 변경되는 경우(forward가 아닌 경우) 다시 생성됨
        
        if (command.equals("register")) {
            // JCF: Collection Framework( ArrayList<MemberDTO> )를 활용하여 메모리 상에서 처리
            MemberDTO member = new MemberDTO();
            member.setFullName(request.getParameter("full-name"));
            member.setEmail(request.getParameter("email"));
            String pw1 = request.getParameter("pw1");
            String pw2 = request.getParameter("pw2");

            if (pw1.equals(pw2)) {
                member.setPw(pw1);
            } else {
                System.out.println("암호 불일치로 작업 중단");
            }

            // 입력하지 않은 정보가 있을 때
            if (member.getFullName().isEmpty() || member.getEmail().isEmpty() || pw1.isEmpty() ) {
                request.getRequestDispatcher("../errors/fail.jsp").forward(request, response);
                return;
            }

            // JDBC
            // DB에 insert를 함
            try {
                stmt = conn.createStatement();
                int cnt = stmt.executeUpdate("insert into t_mb202012047(fullname, email, pw) values('" +
                        member.getFullName() + "', '"
                        + member.getEmail() + "', '"
                        + member.getPw() + "')");
                // cnt가 0이면 질의 실패
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        }
        else if (command.equals("post-form")) {
            request.getRequestDispatcher("../members/register.jsp").forward(request, response);
        }
        else if (command.equals("get-list")) {
            memberDTOList.clear();
            try {
                stmt = conn.createStatement();
    
                // rs : result set, 질의 결과를 다루기 위한 객체, 레코드 또는 레코드들과 처리 메소드로 구성
                rs = stmt.executeQuery("select * from t_mb202012047");
                while(rs.next()) {
                    MemberDTO m = new MemberDTO();
                    m.setMid(rs.getLong(1));
                    m.setFullName(rs.getString("fullname"));  // rs로 가져와진 레코드의 필드값을 m 객체에 set 함
                    m.setEmail(rs.getString(3));
                    m.setPw(rs.getString("pw"));
                    m.setZipcode(rs.getString("zipcode"));
                    memberDTOList.add(m);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("dtoList", memberDTOList);  // Attribute key - value
            request.getRequestDispatcher("../members/list.jsp").forward(request, response);  // ../members/list.jsp와 같음
        }
        else if (command.equals("login-form")) {
            request.getRequestDispatcher("../members/login.jsp").forward(request, response);
        }
        else if (command.equals("login")) {
            try {
                stmt = conn.createStatement();

                // rs : result set, 질의 결과를 다루기 위한 객체, 레코드 또는 레코드들과 처리 메소드로 구성
                String query = "select * from t_mb202012047 where " +
                        "email = '" + request.getParameter("email") +
                        "' and pw = '" + request.getParameter("pw1") + "'";  // pw1은 파라미터 이름
                System.out.println(query);
                rs = stmt.executeQuery(query);

                MemberDTO m = null;
                if(rs.next()) {  // rs.next()는 ResultSet에 원소가 있으면 true, 없으면 false
                    m = new MemberDTO();
                    m.setMid(rs.getLong(1));
                    m.setFullName(rs.getString("fullname"));  // rs로 가져와진 레코드의 필드값을 m 객체에 set 함
                    m.setEmail(rs.getString(3));
                    m.setPw(rs.getString("pw"));  // pw: 테이블의 필드 이름
                    m.setZipcode(rs.getString("zipcode"));
                }
                String url = "../errors/fail.jsp";
                if (m != null) {
                    //request.setAttribute("dto", m);
                    session.setAttribute("dto", m);  // 세션 사용
                    url = "../main/index.jsp";
                }
                request.getRequestDispatcher(url).forward(request, response);  // ../members/list.jsp와 같음
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (command.equals("detail")) {
            long mid = Long.parseLong(request.getParameter("mid"));
            try {
                stmt = conn.createStatement();

                // rs : result set, 질의 결과를 다루기 위한 객체, 레코드 또는 레코드들과 처리 메소드로 구성
                String query = "select * from t_mb202012047 where mid = " + mid;  // pw1은 파라미터 이름
                System.out.println(query);
                rs = stmt.executeQuery(query);

                MemberDTO m = null;
                if(rs.next()) {  // rs.next()는 ResultSet에 원소가 있으면 true, 없으면 false
                    m = new MemberDTO();
                    m.setMid(rs.getLong(1));
                    m.setFullName(rs.getString("fullname"));  // rs로 가져와진 레코드의 필드값을 m 객체에 set 함
                    m.setEmail(rs.getString(3));
                    m.setPw(rs.getString("pw"));  // pw: 테이블의 필드 이름
                    m.setZipcode(rs.getString("zipcode"));
                }
                String url = "../errors/fail.jsp";
                if (m != null) {
                    request.setAttribute("dto", m);
                    url = "../members/detail.jsp";
                }
                request.getRequestDispatcher(url).forward(request, response);  // ../members/list.jsp와 같음
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (command.equals("update")) {
            // JCF: Collection Framework( ArrayList<MemberDTO> )를 활용하여 메모리 상에서 처리
            MemberDTO member = new MemberDTO();
            member.setMid(Long.parseLong(request.getParameter("mid")));
            member.setFullName(request.getParameter("full-name"));
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw1"));
            member.setZipcode(request.getParameter("zipcode"));

            // JDBC
            // DB에 insert를 함
            int cnt = -1;
            try {
                pstmt = conn.prepareStatement("update t_mb202012047 set fullname = ?, pw = ?, zipcode = ? where mid = ?;");
                pstmt.setString(1, member.getFullName());  // placeholder 교체
                pstmt.setString(2, member.getPw());
                pstmt.setString(3, member.getZipcode());
                pstmt.setLong(4, member.getMid());  // Auto Boxing / Unboxing: long -> Long, Long -> long
                cnt = pstmt.executeUpdate();
                // cnt가 0이면 질의 실패
            } catch (SQLException e) {
                System.out.println("질의문 처리 오류");
                throw new RuntimeException(e);
            } finally {
                if (cnt > 0) { // 정상
                    request.getRequestDispatcher("../members/detail?seq=" + member.getMid()).forward(request, response);
                }
                else { // 비정상
                    request.getRequestDispatcher("../errors/fail.jsp").forward(request, response);
                }
            }
        }
        else if (command.equals("delete")) {
            // JCF: Collection Framework( ArrayList<MemberDTO> )를 활용하여 메모리 상에서 처리
            MemberDTO member = new MemberDTO();
            member.setMid(Long.parseLong(request.getParameter("mid")));  // long으로 변환해야 함
            // Type 변화: String -> long -> Long
            // Auto Boxing: long -> Long, Unboxing: Long -> long
            member.setFullName(request.getParameter("full-name"));
            member.setEmail(request.getParameter("email"));
            member.setPw(request.getParameter("pw1"));
            member.setZipcode(request.getParameter("zipcode"));

            // JDBC
            // DB에 insert를 함
            int cnt = -1;
            try {
                pstmt = conn.prepareStatement("delete from t_mb202012047 where mid = ?;");
                pstmt.setLong(1, member.getMid());  // Auto Boxing / Unboxing: long -> Long, Long -> long
                cnt = pstmt.executeUpdate();
                // cnt가 0이면 질의 실패
            } catch (SQLException e) {
                System.out.println("질의문 처리 오류");
                throw new RuntimeException(e);
            } finally {
                if (cnt > 0) { // 정상
                    session.invalidate(); // 탈퇴 하기 전에 로그아웃
                    request.getRequestDispatcher("../main/index.jsp").forward(request, response);
                }
                else { // 비정상
                    request.getRequestDispatcher("../errors/fail.jsp").forward(request, response);
                }
            }
        }
        else if (command.equals("logout")) {
            session.invalidate();
            request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        }
    }

    private void getConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_b202012047?characterEncoding=UTF8&serverTimezone=UTC&useSSL=false";
        String dbUser = "u_b202012047";
        String dbPass = "cometrue";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Driver, Connector (산출물 - DBMS 중재)를 메모리에 적재
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

            /*
            rs = stmt.executeQuery("select * from member");
            System.out.println("Connection Success - " + jdbcUrl);
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.println(rs.getString(2));
            }*/
        } catch(SQLException e) {
            System.out.println("Connection Fail - ");
        } /*finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (pstmt != null) try {pstmt.close(); } catch (Exception e) {}
            if (stmt != null) try {stmt.close(); } catch (Exception e) {}
            if (conn != null) try {conn.close(); } catch (Exception e) {}
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
