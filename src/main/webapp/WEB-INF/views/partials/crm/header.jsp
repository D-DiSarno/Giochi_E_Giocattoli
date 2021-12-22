<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 17/05/21
  Time: 11:00
  To change this template use File | Settings | File Templates.

<header class="topbar grid-x align-center">
    /*icone*/
        <label class="field command">
            <input type="text " placeholder="Cerca comandi">
        </label>
    <span class="account">
      /*icone*/
        Benvenuto Admin
    </span>
    <span class="shoppng.cart">
    <span class="badge">0</span>//site.css
          </span>
</header>
<img src="/img/logo_negozio.png" alt="errore caricamento logo" width="125 px" class="logo">
<img class="iconHeader" src="/icons/typicons.font-2.1.2/src/png-24px/user.png" >
<%@include file="../../../../icons/typicons.font-2.1.2/src/png-24px/user.png"%>
<%@include file="../../../../icons/typicons.font-2.1.2/src/png-24px/shopping-cart.png" %>
<%@include file="../../../../img/logo_negozio.png"%>
<link rel="stylesheet" href="css/home.css">
<img src="/img/logo_negozio.png" alt="errore caricamento logo" width="125 px" class="logo">
<img src="../../../img/logo_negozio.png" alt="errore caricamento logo" width="125 px" class="logo">

<c:if test="${not empty clientSession}"
${clientSession.nome.charAt(0)}.concat('.')+ clientSession.cognome}
</c>

<img src="../img/logo_negozio.png" width="125 px" class="logo">

${authenticated ? "../accounts/profilo" : "../accounts/secret"}

 <span> ${clienteCarrello.quantita}</span>
--%>

<c:set var="authenticated" value="${not empty clienteSession}" scope="request"/>

<div class="header">
    <div class="logo">
        <a href="../pages/"><img src="../img/logo_negozio.png" width="125 px" class="logo">
        </a>
    </div>
    <nav>

        <ul>
            <li><div class="shopping-cart"> <a href="${authenticated? '../carrelli/show' :  '../accounts/secret'}"  >Carrello

                <c:choose>
                    <c:when test="${not empty clienteCarrello}">
                        <span class="badge">${clienteCarrello.quantita()}</span>
                    </c:when>
                </c:choose>
                <%@include file="../../../../icons/typicons.font-2.1.2/src/svg/shopping-cart.svg" %>
            </a></div></li>

            <li class="dropdown"><a class="drop" href="${authenticated? '../accounts/profilo' :  '../accounts/secret'}" >${authenticated? clienteSession.nome : 'Account' }<%@include file="../../../../icons/typicons.font-2.1.2/src/svg/user.svg"%></a>

                <div class="dropdown-content">
                    <a href="../accounts/secret">Login</a>
                    <a href="../accounts/signup">Registrazione</a>
                </div>

            </li>

        </ul>
    </nav>
</div>



    <div class="topnav" id="myTopnav">

        <a href="../pages/" class="aHome home" >Home</a>
        <a href="../prodotti/searchCategoria" class="home">Categoria</a>
        <a href="../prodotti/searchProduttore" class="home">Marche</a>
        <a href="../pages/contattaci" class="home">Contattaci</a>
        <form action="../prodotti/searchBarra" ><input type="text"  placeholder="Cerca qui!" class="home" name="ricerca"></form>
        <a href="../prodotti/search" class="home">Ricerca prodotto</a>
    </div>

