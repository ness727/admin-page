package idusw.javaweb.b202012047.controller;

import idusw.javaweb.b202012047.model.MemberDTO;
import idusw.javaweb.b202012047.model.ProjectDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Controller : 요청의 흐름을 제어하는 역할을 수행
// 데이터 처리 기본 연산 : C.R.U.D (create, read, update, delete)
// HTTP Method (Rest API 관련이 높음) : post, get, (update, delete) - jsp에서는 지원에 문제가 있음
// http method - view vs process
@WebServlet(name = "projectController", urlPatterns = {
        "/projects/add-form", "/projects/add", "/projects/list", "/projects/detail"})
public class ProjectController extends HttpServlet {
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
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        // HttpSession 객체를 가져옴, 없는 경우 생성
        // session은 페이지에 구애받지 않고 일정 시간동안 유지됨
        // request는 페이지가 변경되는 경우(forward가 아닌 경우) 다시 생성됨
        
        if (command.equals("detail")) {
            // MemberController Detail 코드 참고
            ProjectDTO projectDTO = new ProjectDTO();

            projectDTO.setPid(Long.parseLong(request.getParameter("pid")));
            String sql = "select * from t_prjb202012047 where pid=" + projectDTO.getPid();
            // ...
            request.setAttribute("pdto", projectDTO);
            request.getRequestDispatcher("../projects/detail.jsp").forward(request, response);
        }
        if (command.equals("add")) {
            System.out.println(request.getParameter("project-name"));
            // HTML은 -,         DB는 _,          Java CamelNotation
            // project-name,     project_name,   projectName
            // JCF: Collection Framework( ArrayList<MemberDTO> )를 활용하여 메모리 상에서 처리
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setProjectName(request.getParameter("project-name"));
            projectDTO.setStatus(request.getParameter("status"));
            projectDTO.setProjectLeader(request.getParameter("project-leader"));

            // JDBC
            // DB에 insert를 함
            String sql = "insert into t_prjb202012047(project_name, status, project_leader)"
                            + " values(?, ?, ?);";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, projectDTO.getProjectName());
                pstmt.setString(2, projectDTO.getStatus());
                pstmt.setString(3, projectDTO.getProjectLeader());
                int cnt = pstmt.executeUpdate();
                // cnt가 0이면 질의 실패
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            response.sendRedirect("../projects/list");
        }
        else if (command.equals("add-form")) {
            request.getRequestDispatcher("../projects/add.jsp").forward(request, response);
        }
        else if (command.equals("list")) {
            memberDTOList.clear();
            try {
                stmt = conn.createStatement();

                // rs : result set, 질의 결과를 다루기 위한 객체, 레코드 또는 레코드들과 처리 메소드로 구성
                rs = stmt.executeQuery("select * from t_prjb202012047");
                while(rs.next()) {
                    ProjectDTO m = new ProjectDTO();

                    m.setPid(rs.getLong(1));
                    m.setProjectName(rs.getString("project_name"));  // project_name: 테이블 필드 이름, 인덱스 2
                    m.setStatus(rs.getString("status"));
                    m.setProjectLeader(rs.getString("project_leader"));

                    projectDTOList.add(m);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (!projectDTOList.isEmpty()) {
                    request.setAttribute("pDtoList", projectDTOList);  // Attribute key - value
                    request.getRequestDispatcher("../projects/list.jsp").forward(request, response);  // ../members/list.jsp와 같음
                }
                else {
                    request.getRequestDispatcher("../errors/fail.jsp").forward(request, response);  // ../members/list.jsp와 같음
                }
            }
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
