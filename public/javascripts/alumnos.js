
$.fn.nval = function() {
    return Number(this.val())
};

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

    $("#buttonAgregarDeuda").on("click", agregarDeudaAnterior);
    $("#buttonVerDeudores").on("click", verDeudores);


        $("#buttonAlumno").on("click",showAlumno);
        $("#buttonFamilia").on("click",showFamilia);
        $("#buttonBuscar").on("click",showBuscar);
        $("#cancelAlumno").on("click",hideAgregar);
        $("#cancelBuscar").on("click",hideBuscar);
        if ($("#id").val() &&  $("#id").val() != "") {
            $("#agregar").show()
        }


        $("#obligacionMensual").hide();
        $("#pagosPendientesPorFlia").hide();
        $("#generarInscripciones").hide();
        $("#inscripcionesPorFlia").hide();
        $("#deuda").hide();

        $("#buttonOblig").on("click",showObligacionMensual);
        $("#buttonPagosPend").on("click",showPagosPend);
        $("#buttonInscrip").on("click",showInscrip);
        $("#buttonInscripPend").on("click",showInscripPend);
        $("#buttonDeuda").on("click",showDeuda);




//TODO: DESCUENTO HERMANO AUTOMATICO pero que se pueda modificar
    var descInicial  =  $("#totalSinDescuento").nval() * 0.1;
    $("#descuentoHermano").val(descInicial);
        $("#descuentoEspecial").val(0)
        $("#interes").val(0)
        $("#recibo").val("")

    $("#totalAPagar").val($("#totalSinDescuento").nval()- descInicial);

    $("#descuentoHermano").on('input',function() {

        var totalAPagar = $("#totalSinDescuento").nval() - $("#descuentoEspecial").nval() - $("#descuentoHermano").nval() + $("#interes").nval()
        $("#totalAPagar").val(totalAPagar)
    })
        $("#descuentoEspecial").on('input', function() {

            var totalAPagar = $("#totalSinDescuento").nval() - $("#descuentoEspecial").nval() - $("#descuentoHermano").nval() + $("#interes").nval()
            $("#totalAPagar").val(totalAPagar)
        })
        $("#interes").on('input', function() {

            var totalAPagar = $("#totalSinDescuento").nval() - $("#descuentoEspecial").nval() - $("#descuentoHermano").nval() + $("#interes").nval()
            $("#totalAPagar").val(totalAPagar)
        })
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

var agregarDeudaAnterior = function () {
    $('#formBuscar').attr('action', "/deudaAnterior")
    $("#formBuscar").submit();
}

var verDeudores  = function () {
    $('#formBuscar').attr('action', "/verDeudores")
    $("#formBuscar").submit();
}





var showObligacionMensual = function(){
    $("#obligacionMensual").show();
    $("#pagosPendientesPorFlia").hide();
    $("#generarInscripciones").hide();
    $("#inscripcionesPorFlia").hide();
    $("#deuda").hide();
}

var showPagosPend = function(){
    $("#obligacionMensual").hide();
    $("#pagosPendientesPorFlia").show();
    $("#generarInscripciones").hide();
    $("#inscripcionesPorFlia").hide();
    $("#deuda").hide();
}
var showInscrip = function(){
    $("#obligacionMensual").hide();
    $("#pagosPendientesPorFlia").hide();
    $("#generarInscripciones").show();
    $("#inscripcionesPorFlia").hide();
    $("#deuda").hide();
}


var showInscripPend = function(){
    $("#obligacionMensual").hide();
    $("#pagosPendientesPorFlia").hide();
    $("#generarInscripciones").hide();
    $("#inscripcionesPorFlia").show();
    $("#deuda").hide();
}


var showDeuda= function(){
    $("#obligacionMensual").hide();
    $("#pagosPendientesPorFlia").hide();
    $("#generarInscripciones").hide();
    $("#inscripcionesPorFlia").hide();
    $("#deuda").show();
}
