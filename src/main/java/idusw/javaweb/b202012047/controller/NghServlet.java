package idusw.javaweb.b202012047.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "myhServlet", value = "/induk")
public class NghServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + "내가 만든 서블릿" + "</h1>");
//        out.println("</body></html>");
        out.print("\n" +
                "\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\" />\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n" +
                "        <meta name=\"description\" content=\"\" />\n" +
                "        <meta name=\"author\" content=\"\" />\n" +
                "        <title>One Page Wonder - Start Bootstrap Template</title>\n" +
                "        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\n" +
                "        <!-- Font Awesome icons (free version)-->\n" +
                "        <script src=\"https://use.fontawesome.com/releases/v6.3.0/js/all.js\" crossorigin=\"anonymous\"></script>\n" +
                "        <!-- Google fonts-->\n" +
                "        <link href=\"https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900\" rel=\"stylesheet\" />\n" +
                "        <link href=\"https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i\" rel=\"stylesheet\" />\n" +
                "        <!-- Core theme CSS (includes Bootstrap)-->\n" +
                "        <link href=\"css/styles.css\" rel=\"stylesheet\" />\n" +
                "    </head>\n" +
                "    <body id=\"page-top\">\n" +
                "        <!-- Navigation-->\n" +
                "        <nav class=\"navbar navbar-expand-lg navbar-dark navbar-custom fixed-top\">\n" +
                "            <div class=\"container px-5\">\n" +
                "                <a class=\"navbar-brand\" href=\"#page-top\">Start Bootstrap</a>\n" +
                "                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\n" +
                "                <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" +
                "                    <ul class=\"navbar-nav ms-auto\">\n" +
                "                        <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">Sign Up</a></li>\n" +
                "                        <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">Log In</a></li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </nav>\n" +
                "        <!-- Header-->\n" +
                "        <header class=\"masthead text-center text-white\">\n" +
                "            <div class=\"masthead-content\">\n" +
                "                <div class=\"container px-5\">\n" +
                "                    <h1 class=\"masthead-heading mb-0\">안녕</h1>\n" +
                "                    <h2 class=\"masthead-subheading mb-0\">내가 좋아하는 영화</h2>\n" +
                "                    <a class=\"btn btn-primary btn-xl rounded-pill mt-5\" href=\"#scroll\">Learn More</a>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"bg-circle-1 bg-circle\"></div>\n" +
                "            <div class=\"bg-circle-2 bg-circle\"></div>\n" +
                "            <div class=\"bg-circle-3 bg-circle\"></div>\n" +
                "            <div class=\"bg-circle-4 bg-circle\"></div>\n" +
                "        </header>\n" +
                "        <!-- Content section 1-->\n" +
                "        <section id=\"scroll\">\n" +
                "            <div class=\"container px-5\">\n" +
                "                <div class=\"row gx-5 align-items-center\">\n" +
                "                    <div class=\"col-lg-6 order-lg-2\">\n" +
                "                        <div class=\"p-5\"><img class=\"img-fluid rounded-circle\" src=\"assets/img/01.jpg\" alt=\"...\" /></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 order-lg-1\">\n" +
                "                        <div class=\"p-5\">\n" +
                "                            <h2 class=\"display-4\">For those about to rock...</h2>\n" +
                "                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod aliquid, mollitia odio veniam sit iste esse assumenda amet aperiam exercitationem, ea animi blanditiis recusandae! Ratione voluptatum molestiae adipisci, beatae obcaecati.</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </section>\n" +
                "        <!-- Content section 2-->\n" +
                "        <section>\n" +
                "            <div class=\"container px-5\">\n" +
                "                <div class=\"row gx-5 align-items-center\">\n" +
                "                    <div class=\"col-lg-6\">\n" +
                "                        <div class=\"p-5\"><img class=\"img-fluid rounded-circle\" src=\"assets/img/02.jpg\" alt=\"...\" /></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6\">\n" +
                "                        <div class=\"p-5\">\n" +
                "                            <h2 class=\"display-4\">We salute you!</h2>\n" +
                "                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod aliquid, mollitia odio veniam sit iste esse assumenda amet aperiam exercitationem, ea animi blanditiis recusandae! Ratione voluptatum molestiae adipisci, beatae obcaecati.</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </section>\n" +
                "        <!-- Content section 3-->\n" +
                "        <section>\n" +
                "            <div class=\"container px-5\">\n" +
                "                <div class=\"row gx-5 align-items-center\">\n" +
                "                    <div class=\"col-lg-6 order-lg-2\">\n" +
                "                        <div class=\"p-5\"><img class=\"img-fluid rounded-circle\" src=\"assets/img/03.jpg\" alt=\"...\" /></div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 order-lg-1\">\n" +
                "                        <div class=\"p-5\">\n" +
                "                            <h2 class=\"display-4\">Let there be rock!</h2>\n" +
                "                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod aliquid, mollitia odio veniam sit iste esse assumenda amet aperiam exercitationem, ea animi blanditiis recusandae! Ratione voluptatum molestiae adipisci, beatae obcaecati.</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </section>\n" +
                "        <!-- Footer-->\n" +
                "        <footer class=\"py-5 bg-black\">\n" +
                "            <div class=\"container px-5\"><p class=\"m-0 text-center text-white small\">Copyright &copy; Your Website 2023</p></div>\n" +
                "        </footer>\n" +
                "        <!-- Bootstrap core JS-->\n" +
                "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                "        <!-- Core theme JS-->\n" +
                "        <script src=\"js/scripts.js\"></script>\n" +
                "    </body>\n" +
                "</html>\n");
    }
}
