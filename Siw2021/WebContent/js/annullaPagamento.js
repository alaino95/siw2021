function annulla() {
	var result = confirm("Sei sicuro di voler annullare il pagamento?");
	if (result === true) {
		location.href = 'index.jsp';
	} else {
		alert('Operazione annullata');
	}
}
