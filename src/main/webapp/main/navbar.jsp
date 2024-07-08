<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<nav class="main-header navbar navbar-expand navbar-white navbar-light">
  <!-- Left navbar links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
    </li>
    <li class="nav-item d-none d-sm-inline-block">
      <a href="../main/index.jsp" class="nav-link">Home</a>
    </li>
    <li class="nav-item d-none d-sm-inline-block">
      <a href="#" class="nav-link">Contact</a>
    </li>
  </ul>

  <!-- Right navbar links -->
  <ul class="navbar-nav ml-auto">
    <!-- Navbar Search -->
    <li class="nav-item">
      <a class="nav-link" data-widget="navbar-search" href="#" role="button">
        <i class="fas fa-search"></i>
      </a>
      <div class="navbar-search-block">
        <form class="form-inline">
          <div class="input-group input-group-sm">
            <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
            <div class="input-group-append">
              <button class="btn btn-navbar" type="submit">
                <i class="fas fa-search"></i>
              </button>
              <button class="btn btn-navbar" type="button" data-widget="navbar-search">
                <i class="fas fa-times"></i>
              </button>
            </div>
          </div>
        </form>
      </div>
    </li>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
  <c:when test="${dto == null}">
    <!-- Messages Dropdown Menu -->
    <li class="nav-item dropdown">
      <a class="nav-link" href="../members/login-form">
        <i class="fa-solid fa-door-open"></i>                   <!-- 로그인 -->
      </a>
    </li>
    <!-- Notifications Dropdown Menu -->
    <li class="nav-item dropdown">
      <a class="nav-link" href="../members/post-form">
        <i class="fa-solid fa-right-to-bracket"></i>             <!-- 회원 가입  -->
      </a>
    </li>
  </c:when>
  <c:otherwise>
    <li class="nav-item dropdown">
      <a class="nav-link" href="../members/detail?mid=${dto.mid}">
        <i class="fa-solid fa-circle-info"></i>                  <!-- detail -->
      </a>
    </li>
    <li class="nav-item dropdown">
      <a class="nav-link" href="../members/logout">
        <i class="fa-solid fa-power-off"></i>                   <!-- logout -->
      </a>
    </li>
    <c:if test="${dto.email eq 'admin'}">
      <li class="nav-item dropdown">
        <a class="nav-link" href="../members/get-list">
          <i class="fa-solid fa-list"></i>                   <!-- list -->
        </a>
      </li>
    </c:if>
  </c:otherwise>
</c:choose>
    <li class="nav-item">
      <a class="nav-link" data-widget="fullscreen" href="#" role="button">
        <i class="fas fa-expand-arrows-alt"></i>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-widget="control-sidebar" data-controlsidebar-slide="true" href="#" role="button">
        <i class="fas fa-th-large"></i>
      </a>
    </li>
  </ul>
</nav>
