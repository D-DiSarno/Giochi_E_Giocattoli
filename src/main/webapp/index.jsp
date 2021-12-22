
<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 10/05/21
  Time: 10:49
  To change this template use File | Settings | File Templates.

--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>

<html lang="it" dir="ltr">
<head>
    <title></title>
    <meta content="width=device-width, initial-scale=1" name="viewport" />
  <jsp:include page="WEB-INF/views/partials/head.jsp">
    <jsp:param name="title" value="HomePage"/>
  </jsp:include>

</head>

<body>
<%--
<%@include file="WEB-INF/views/partials/crm/header.jsp"%>
<div class="header">
  <div class="logo">
      <a href="index.jsp"><img src="img/logo_negozio.png" alt="errore caricamento logo" width="125 px" class="logo">
      </a>
  </div>
  <nav>

<ul>

  <div class="dropdown"> <li><a href="" class="drop">Account<img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/user.png" ></a>
        <div class="dropdown-content">
         <a href="./accounts/secret">Login</a>
         <a href="./accounts/signup">Registrazione</a>
         </div>

       </li>
    </div>

    <li><a href="">Carrello <img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/shopping-cart.png" ></a></li>

</ul>
  </nav>
</div>

<div class="topnav" id="myTopnav">

    <a href="index.jsp" class="aHome">Home</a>
    <a href="">Categoria</a></li>
    <a href="">Marche</a></li>
    <a href="">Contattaci</a>
    <form action="ricerca"><input type="text"  value="Cerca qui!"></form>

</div>

<div class="row">
    <p role="tab">PIÙ VENDUTI :</p>
  <div class="contenitore">
      <div class="prodotto">
        <a target="_blank" href="">
         <img src="images/img/avengers%20iron%20Man.png" alt="errore" width="300" height="300"></a>
       <div class="desc">Add a description of the image here</div>
      </div>

      <div class="prodotto">
        <a target="_blank" href="">
            <img src="images/img/chicco%20bici%20gialla.jpeg" alt="errore" width="300" height="300"></a>
        <div class="desc">Add a description of the image here</div>
       </div>

    <div class="prodotto">
        <a target="_blank" href="">
            <img src="images/img/giochi%20preziosi%20Cicciobello%20Bua.jpeg" alt="errore" height="300" width="300"></a>
        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="prodotto">
        <a target="_blank" href="">
            <img src="images/img/mondo%20italia.jpeg" alt="errore" width="300" height="300"></a>
        <div class="desc">Add a description of the image here</div>
    </div>

  <div class="prodotto">
    <a target="_blank" href="">
      <img src="images/img/star%20wars%20Destroyer.jpeg" alt="errore" width="300" height="300"></a>
    <div class="desc">Add a description of the image here</div>
  </div>

  <div class="prodotto">
      <a target="_blank" href="">
        <img src="images/img/technic%20ferrari.jpeg" alt="errore" width="300" height="300" > </a>
    <div class="desc">Add a description of the image here</div>
  </div>
  </div>
    <div class="eta">
        <h2>Se preferisci cerca anche in base all'età:</h2>
        <div class="categoriaEta"><a href="">0-2 Anni <img src="icons/typicons.font-2.1.2/src/png-24px/star.png"> </a></div>
        <div class="categoriaEta"><a href="">3-6 Anni <img src="icons/typicons.font-2.1.2/src/png-24px/flow-children.png"></a></div>
        <div class="categoriaEta"><a href="">7-9 Anni <img src="icons/typicons.font-2.1.2/src/png-24px/plane.png"></a></div>
        <div class="categoriaEta"><a href="">10+ Anni <img src="icons/typicons.font-2.1.2/src/png-24px/world.png"></a></div>
    </div>
</div>
<%@include file="WEB-INF/views/partials/crm/footer.jsp"%>

<%--
<div class="footer">
<ul>

    <li><p>089202020<img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/phone.png" ></p></li>
    <li><p>Ci troviamo a: via F.WENNER, 39 - Z. INDUSTRIALE FRATTE<img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/plane.png" ></p></li>
    <li><p>Orari di apertura: 09:00-20:00<img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/time.png" ></p></li>
    <li><a href="">Regolamento<img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/info.png" ></a></li>
    <li><a href="">Politiche<img class="iconHeader" src="icons/typicons.font-2.1.2/src/png-24px/info.png" ></a></li>


  </ul>
</div>
<% response.sendRedirect("./crm/Home-Page"); %>
<% response.sendRedirect("./crm/dashboard"); %>
<% response.sendRedirect("./accounts/secret"); %>
<% response.sendRedirect("./crm/dashboard"); %>
<% response.sendRedirect("./crm/prodotti"); %>
<img src="/GiochiEGiocattoli/img/download.jpg" alt="No text">
<% response.sendRedirect("./accounts/secret"); %>
<% response.sendRedirect("./crm/prodotti"); %>
<% response.sendRedirect("./prodotti/search"); %>
<% response.sendRedirect("./accounts/secret"); %>
<%@include file="WEB-INF/views/ordine/carrello.jsp"%>
--%>
<% response.sendRedirect("./pages/"); %>
</body>
</html>


