<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 05/07/21
  Time: 17:12
  To change this template use File | Settings | File Templates.


min="0"

action="../prodotti/search"
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Search"/>

    </jsp:include>

<style>
     form.search{
         background-color: white;
         margin-top: 20px;
         border: 2px solid darkcyan;
         border-radius: 20px;
     }
     label.field.prezzi {
         display: grid;
     }
     div.prodotto{
         min-height: 580px;
         margin: 5px;
     }
     div.prodotto button.btn {
         margin-bottom: 5px;
     }
     @media (min-width: 470px){
         form.search.Tab {
             display: grid;
             margin: auto;
             margin-top: 20px;
         }
     }
     @media (min-width: 780px){
         form.search.Tab {
             display: block;
         }
         label.field {
             display: flow-root;
             padding: 2px;
         }
     }
     .row h1 {
         margin: auto;
         padding: 10px;
         background: azure;
         border: 2px solid;
         display: flex;
         margin-top: 20px;
         margin-left: 240px;
     }

</style>
    <script src="../js/ajax_search.js"></script>
</head>
<body>
<%@include file="../partials/crm/header.jsp"%>
<main class="app">
    <section class="grid-x" style="margin: auto">
        <form method="get"  action="../prodotti/search" class="search Tab" onsubmit="return validateForm(this)" name="form">
            <span>Nome Prodotto:</span>
            <label class="field" for="nome">
                <input type="text" id="nome" placeholder="Nome" value="" name="nome"  minlength="2" maxlength="100">
            </label>
            <span>Categoria:</span>
            <label class="field" for="idCategoria">
                <select name="idCategoria" id="idCategoria">
                    <option value="">Nessuna</option>
              <c:forEach items="${categorie}" var="categoria">
                  <option value="${categoria.idCategoria}" name="${categoria.tipologia}" >${categoria.tipologia}</option>
              </c:forEach>
                </select>
            </label>
            <label class="field prezzi">
            <span>Prezzo:</span>
                <input id="minPrezzo" type="number" placeholder="Da" name="minPrezzo"  >
                <input id="maxPrezzo" type="number" placeholder="A" name="maxPrezzo" >
            </label>

            <label class="field" for="eta_minima">
                <span>Eta'</span>
            <input id="eta_minima" type="number" placeholder="Età minima" name="eta_minima"  >
            </label>
            <label class="field" for="idProduttore">
                <span>Marca:</span>
                <select name="idProduttore" id="idProduttore">
                    <option value="">Nessuno</option>
                    <c:forEach items="${produttori}" var="produttore">
                        <option value="${produttore.idProduttore}" name="${produttore.nome}" >${produttore.nome}</option>
                    </c:forEach>
                </select>
            </label>
            <button class="btn primary" type="submit">Cerca</button>
        </form>
           <div class="">
             <div class="row">
                 <c:if test="${empty prodotti}">
                     <h1>Nessun prodotto trovato</h1>
                 </c:if>
                <c:forEach items="${prodotti}" var="prodotto">
                    <form action="../carrelli/add?id=${prodotto.idProdotto}" method="post">
                    <div class="contenitore">
                        <div class="prodotto">
                            <a href="../prodotti/details?id=${prodotto.idProdotto}">
                                <img src="../images/img/${prodotto.img}" alt="errore" width="300" height="300">
                                <div class="desc">${prodotto.descrizione}<br><b>${prodotto.prezzo}$</b></div></a>
                                 <input type="hidden" value="1" name="Quantita" id="Quantita">

                                <button class="btn primary" type="submit">Aggiungi al carrello</button>
                        </div>
                    </div>
                    </form>
                </c:forEach>


             </div>
          </div>


    </section>

</main>
<%@include file="../partials/crm/footer.jsp"%>
</body>
</html>
