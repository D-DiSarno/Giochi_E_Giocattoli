<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 10/05/21
  Time: 10:55
  To change this template use File | Settings | File Templates.
   <jsp:param name="style" value="crm,dashboard"/>
   <link rel="stylesheet" href="/css/library.css" type="text/css">
   ../../../css/dashboard.css

    <jsp:param name="stat" value="100"/>


    <jsp:param name="stat" value="${ordiniMensili}"/>
    <jsp:param name="stat" value="${incassoMensile} Euro"/>
--%>


<html>
<head>

    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="GiochiEGiocattoli-Home"/>
        <jsp:param name="style" value="crm,dashboard"/>
    </jsp:include>

<script>
    alert("CIAO ADMIN");
</script>
<style>
  div.valori{min-width: 70px;}
  div.around {
      min-width: 100px;
  }
</style>
</head>
<body>
<%@include file="../partials/crm/header.jsp"%>

<main class="app dashboard">

    <%@include file="../partials/crm/sidebar.jsp"%>
    <section class="content grid-y">

        <div class="body grid-x justify-center">
            <div class="valori">
            <jsp:include page="../partials/statscard.jsp">
                <jsp:param name="title" value="Clienti Registrati"/>
                <jsp:param name="stat" value="${clientiNum}"/>

            </jsp:include>
            </div><div class="valori">
            <jsp:include page="../partials/statscard.jsp">
                <jsp:param name="title" value="Ordini "/>
                <jsp:param name="stat" value="${ordini}"/>

            </jsp:include>
        </div><div class="valori">
            <jsp:include page="../partials/statscard.jsp">
                <jsp:param name="title" value="Incasso "/>
                <jsp:param name="stat" value="${incasso}"/>

            </jsp:include>
            </div>  <div class="valori">
            <jsp:include page="../partials/statscard.jsp">
                <jsp:param name="title" value="Prodotti in magazzino"/>
                <jsp:param name="stat" value="${prodottiRimanenti}"/>
            </jsp:include>
            </div>
        </div>

    </section>

</main>
<%@include file="../partials/crm/footer.jsp"%>

</body>
</html>
