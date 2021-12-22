

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