<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 18/07/21
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Dettagli ${prodotto.nome}"/>
        <jsp:param name="styles" value="site,ecomerce"/>
    </jsp:include>
<style>
    .cell img {
        height: 400px;
        width: 350px;
        border: 2px solid salmon;
    }
    dl.cell {
        display: inline-block;
        border: 2px solid coral;
        padding: 30px;
        max-width: 500px;
        background: aliceblue;
    }
    dt {
        color: darkcyan;
        font-size: x-large;
    }
    section{
        display: block;
    }
    button.primary{
        margin-left: 20px;
    }
    label.field {
        margin-top: 15px;
    }
    section.grid-x.align-center {
        margin: auto;
        border: 2px solid darkgoldenrod;
        border-radius: 40px;
    }
    input#Quantita {
        border-radius: 20px;
    }
</style>
</head>
<body>
<%@include file="/WEB-INF/views/partials/crm/header.jsp"%>
<main class="app grid-y">
  <section class="grid-x align-center">
      <figure class="cell">
          <img src="../images/img/${prodotto.img}">
          <figcaption>
              <form method="post" action="../carrelli/add?id=${prodotto.idProdotto}" class="grid-x">
                  <input type="hidden" name="id" value="${prodotto.idProdotto}">
                  <label for="Quantita" class="field">
                  <input type="number" value="1" name="Quantita" id="Quantita" placeholder="Quantità">
                      <button type="submit" class="btn primary">Aggiungi</button>
                  </label>
              </form>
          </figcaption>

      <dl class="cell">
          <dt>Nome</dt>
          <dd>${prodotto.nome}</dd>
          <dt>Prezzo</dt>
          <dd>${prodotto.prezzo}</dd>
          <dt>Età minima</dt>
          <dd>${prodotto.eta_minima}</dd>
          <dt>Ecco una nostra descrizione:</dt>
          <dd>${prodotto.descrizione}</dd>
          <dt>Produttore</dt>
          <dd>${prodotto.produttore.nome}</dd>
          <dt>Categoria</dt>
          <dd>${prodotto.categoria.tipologia}</dd>

      </dl>
      </figure>
  </section>
</main>

<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
