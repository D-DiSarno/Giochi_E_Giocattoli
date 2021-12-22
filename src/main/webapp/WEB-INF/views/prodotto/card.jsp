<%--
  Created by IntelliJ IDEA.
  User: davidedisarno
  Date: 18/07/21
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<div class="prodotto-card grid-y">
     <img src="/GiochiEGiocattoli/images/img/${prodotto.img}" height="200" alt="Immagine predefinita">
    <div class="body">
        <div class="colonna">
            <p>Nome</p>
            <p>${prodotto.nome}</p>
        </div>
        <div class="colonna">
            <p>Prezzo</p>
            <p>${prodotto.prezzo}</p>
        </div>
        <div class="colonna">
            <p>Descrizione</p>
            <p>${prodotto.descrizione}</p>
        </div>
        <div class="colonna">
            <p>età minima</p>
            <p>${prodotto.eta_minima}</p>
        </div>
    </div>
    <div class="opzioni grid-x">
        <form method="post" action="/GiochiEGiocattoli/carrelli/add" class="cell">
            <input type="hidden" name="id" value="${prodotto.idProdotto}">
            <input type="hidden" name="quantita" value="1">
            <button type="submit" class="btn primary">Aggiungi al Carrello</button>
        </form>
        <a href="/GiochiEGiocattoli/prodotti/details?id=${prodott.idProdotto}" class="btn primary">Dettagli</a>
    </div>
</div>