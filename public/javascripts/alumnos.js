$(document).ready(function() {
    $("#agregar").hide();
    $("#agregarFamilia").hide();
    $("#buscar").hide();

    $("#buttonAlumno").on("click",showAlumno);
    $("#buttonFamilia").on("click",showFamilia);
    $("#buttonBuscar").on("click",showBuscar);
    $("#cancelAlumno").on("click",hideAgregar);
    $("#cancelBuscar").on("click",hideBuscar);
    if ($("#id").val() &&  $("#id").val() != "") {
        $("#agregar").show()
    }
}
);

var showAlumno = function(){
    $("#agregar").show()
    $("#buscar").hide()
    $("#agregarFamilia").hide()
}

var showFamilia = function(){
    $("#agregarFamilia").show()
    $("#agregar").hide()
    $("#buscar").hide()
}
var showBuscar = function(){
    $("#agregarFamilia").hide()
    $("#agregar").hide()
    $("#buscar").show()
}

var hideBuscar = function() {
    $("#buscar").hide();
}

var hideAgregar= function() {
    $("#agregar").hide();
}
var hideAllDivs = function() {

}

var modificarAlumno = function(identificador) {

}