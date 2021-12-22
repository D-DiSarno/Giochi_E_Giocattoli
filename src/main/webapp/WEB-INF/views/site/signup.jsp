<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 29/06/21
  Time: 14:42
  To change this template use File | Settings | File Templates.
  Registrazione

    <script src="../js/signupForm.js"></script>
    onsubmit="return verifica(this);"
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it" dir="lt">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="signup"/>
    </jsp:include>

<style>
    .btn{
        margin-bottom: 6px;
    }
</style>
<script>
    function validateForm() {

        var pass=document.getElementById("password").value;
        var conferma=document.getElementById("Confpassword").value;
        var nome=document.getElementById("Nome").value;
        var cognome=document.getElementById("Cognome").value;
        var Citta=document.getElementById("Citta").value;
        var Via=document.getElementById("Via").value;
        var Mail=document.getElementById("Mail").value;

        if(pass != conferma){
            alert("Password diverso dalla conferma");
            return false;
        }else if(nome ==""){
            alert("Il nome non può essere vuoto");
            return false;
        }else if(cognome ==""){
            alert("Il cognome non può essere vuoto");
            return false;}
        else if(Citta ==""){
            alert("La città non può essere vuota");
            return false;}
        else if(Via ==""){
            alert("La via non può essere vuota");
            return false;}
        else if(Mail ==""){
            alert("L'e-mail non può essere vuota");
            return false;}
        else if(pass ==""){
            alert("La password non può essere vuota");
            return false;}
        else if(conferma ==""){
            alert("La conferma non può essere vuota");
            return false;}
        else{ return true;}

    }
</script>
</head>
<body>
<%@include file="/WEB-INF/views/partials/crm/header.jsp"%>

<div class="grid-x app">
    <div style="margin: auto">
        <%--Se esiste l'alert lo include --%>
            <c:if test="${not empty alert}">
                <%@include file="../site/alert.jsp"%>
            </c:if>
    <form   action="../accounts/signupCliente" method="post" class="form" name="signup" onsubmit=" return validateForm(this)" >

        <input type="text" placeholder="Nome" id="Nome" name="Nome" ></input>
        <input type="text" placeholder="Cognome" id="Cognome" name="Cognome" ></input>
        <input type="text" placeholder="Via" id="Via" name="Via"></input>
        <input type="text" placeholder="codice postale" id="Codice_postale" name="Codice_postale" ></input>
        <input type="text" placeholder="Città" id="Citta" name="Citta"  ></input>
        <input type="text" placeholder="e-mail" id="Mail" name="Mail" ></input>
        <input type="password" placeholder="password" id="password" name="password" ></input>
        <input type="password" placeholder="conferma" id="Confpassword" name="Confpassword"  >
        <input type="hidden" value="false" id="ruolo" name="ruolo"/>
        <button type="submit" class="btn primary" >Registrati! </button>
    </form>
    <p>Procedendo, confermi di avere letto ed accettato i nostri <a href="../pages/regolamento">Termini e Condizioni</a> e la nostra <a href="../pages/politiche">politica per la Privacy</a>.</p>
    </div>
</div>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
