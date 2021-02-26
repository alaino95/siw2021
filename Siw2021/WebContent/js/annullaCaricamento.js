function annulla() {
	var domanda = confirm("Sei sicuro di voler annullare?");
	if (domanda === true) {
		location.href = 'index.jsp';
	} else {
		alert('Operazione annullata');
	}
}
