$(document).ready(function(){	
	$.ajax({
		type: "GET",
		url: "elencoCaseEditrici",
		//data: {casaEditrice: valore},
		success: function(data){
			$("#caseeditrici").html(data);
		}
	});
	$.ajax({
		type: "GET",
		url: "elencoGeneri",
		//data: {genere: valore},
		success: function(data){
			$("#generi").html(data);
		}
	});	
});

function caricaRecensioni(id){
	$.ajax({
		type: "POST",
		url: "elencoRecensioni",
		data: {id_ebook: id},
//		dataType: "json",
//		data : JSON.stringify({
//			id_appunto : id
//		}),
		success: function(data){
			$("#recensioni").html(data);
		//	$("#dettagliScuola").text("<p>"+recensioni.testo+"</p>"); 
			
		}
	});
}

function salvaDati(p,crediti,id){
	var totaleP = 0;
	var totaleC = 0;
	var aggiungi = false;
	
	if($("."+id).is(':checked')){
		totaleP=parseInt(p);
		totaleC=parseInt(crediti);
		aggiungi = true;
	}else{
		totaleP=-parseInt(p);
		totaleC=-parseInt(crediti);
		aggiungi = false;
	}
	$.ajax({
		type: "POST",
		url: "carrello",
		data: {id_ebook: id,
				prezzo: p,
				crediti: crediti,
				aggiungi: aggiungi},
		success: function(data){
			$(".paragrafo").remove();	
			$("#totale").html(data);
		}
	});
}