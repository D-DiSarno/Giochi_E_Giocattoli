function showCategoria(categoria) {
    if (categoria == "") {
        document.getElementById("result").innerHTML = "Scegli per cercare";
        return;
    }
    const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            if (this.status == 200 && this.readyState == 4) {
                document.getElementById("result").innerText = "Ricerca Valida";
            } else {
                alert("ERRORE " + this.status);
            }


    }
    xhttp.open("GET", "../prodotti/searchCategoria", true);
    xhttp.send();
}

    function showProduttore(produttore) {
        if (produttore == "") {
            document.getElementById("result").innerHTML = "Scegli per cercare";
            return;
        }
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            if (this.status == 200 && this.readyState == 4) {
                document.getElementById("result").innerText = "Ricerca Valida";
            } else {
                alert("ERRORE " + this.status);
            }
        }
        xhttp.open("GET", "../prodotti/searchProduttore", true);
        xhttp.send();
    }


    function validateForm() {

        let eta = parseInt(document.getElementById("eta_minima").value);
        let prezzoMin = parseInt(document.getElementById("minPrezzo").value);
        let prezzoMax = parseInt(document.getElementById("maxPrezzo").value);

        if (eta < 0) {
            alert("L'età minima non può essere minore di 0("+eta+")");
            document.getElementById("eta_minima").focus();
            document.getElementById("eta_minima").style.backgroundColor = "red";
            document.getElementById("eta_minima").style.color = "white";
            document.getElementById("eta_minima").value = 0;
            return false;
        } else if (prezzoMax < 0) {
            alert("Il prezzo massimo non può essere minore di 0 ("+prezzoMax+"<0)");
            document.getElementById("maxPrezzo").focus();
            document.getElementById("maxPrezzo").style.backgroundColor = "red";
            document.getElementById("maxPrezzo").style.color = "white";
            document.getElementById("maxPrezzo").value = 0;
            return false;
        } else if (prezzoMin < 0) {
            alert("Il prezzo minimo non può essere minore di 0");
            document.getElementById("minPrezzo").focus();
            document.getElementById("minPrezzo").style.backgroundColor = "red";
            document.getElementById("minPrezzo").style.color = "white";
            document.getElementById("minPrezzo").value = 0;
            return false;
        } else if (prezzoMin>prezzoMax) {
            alert("Il prezzo minimo("+prezzoMin+") non può superare il prezzo massimo("+prezzoMax+")");
            document.getElementById("minPrezzo").focus();
            document.getElementById("maxPrezzo").focus();
            document.getElementById("minPrezzo").style.backgroundColor = "red";
            document.getElementById("minPrezzo").style.color = "white";
            document.getElementById("minPrezzo").value = 0;
            document.getElementById("maxPrezzo").style.backgroundColor = "red";
            document.getElementById("maxPrezzo").style.color = "white";
            document.getElementById("maxPrezzo").value = 0;
            return false;}
        else {
            return true;
        }


}
