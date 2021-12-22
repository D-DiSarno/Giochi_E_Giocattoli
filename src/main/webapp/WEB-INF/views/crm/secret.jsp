<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 17/05/21
  Time: 09:12
  To change this template use File | Settings | File Templates.
<%-- <style>
  .btn{
  padding:9px;
  padding-bottom:35px ;
  border:none;
  cursor: pointer;

  }
  button.primary{
  background-color: darkcyan;
  color:white;
  font-size: xx-large;
  }
  button.primary:hover{
  filter:brightness(110%);
  }
  input,label{
      font-size: xx-large;
      color: darkcyan;

  }


  </style>

<div class="controllo">

        <a href="../accounts/signup"  > oppure Registrati!</a>
    </div>
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="login"/>
    </jsp:include>

  <%--<script>
      function validateForm(){
          var email=document.getElementById("Mail");
          var expMail=/^[a-zA-Z0-9.!$#%&'*+=?_^'{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$#/;
          if(expMail.test(email.value)){
              alert("E-mail non valida");
              return false;
          }
          var pass= (document.getElementById("password")).value;
          if(pass == null || pass==""){
              alert("La password non può essere vuota");
              return false;
          }
          if(pass.length<2){
              alert("La password deve avere almeno due caratteri");
              return false;
          }

      }
     if(!${tornato}){
         document.getElementById("hidden").style.display="inline";
      }
  </script>--%>
<style>
    input{
        padding-left: 50px;
    }
    button.primary{
        color: white;
    }

</style>
</head>
<body>


<%@include file="../partials/crm/header.jsp"%>
<div style="margin: auto">

<form action="../accounts/secret" method="post" class="secret app grid-x justify-center align-center" name="secret" >
    <c:if test="${not empty alert}">
        <%@include file="../site/alert.jsp"%>
    </c:if>

  <fieldset class="grid-y login form">
      <h2 style="color:darkcyan;">Benvenuto!</h2>


      <label for="Mail">
          <input type="email" name="Mail" id="Mail" placeholder="email"  required pattern="^[a-zA-Z0-9.!#$%&'*+=?_^'{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$">
      </label>


      <label for="password">
          <input type="password" name="password" id="password" placeholder="password"  pattern="^\w{2,30}$" required minlength="2" maxlength="20">
      </label>


      <button type="submit" class="btn primary" >Accedi al tuo account </button>
  </fieldset>

</form>
</div>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
