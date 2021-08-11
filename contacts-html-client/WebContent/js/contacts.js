var rootURL = "http://localhost:8080/contacts";

function findAll(callback) {
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json", 
		success : callback
	});
}

function findByName(name, callback) {

	if (name != null && name != "") {
		$.ajax({
			type : 'GET',
			url : rootURL + '/find/' + name,
			dataType : "json",
			success : callback
		});
	} else {
		findAll(callback);
	}
}

function findById(id, callback) {
	$.ajax({
		type : 'GET',
		url : rootURL + '/' + id,
		dataType : "json",
		success : callback
	});
}

function addContact(contact, callback) {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL,
		dataType : "json",
		data : contact,
		success : callback,
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Erro criando contato: ' + jqXHR.responseText);
		}
	});
}

function updateContact(id, contact, callback) {
	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		url : rootURL + '/' + id,
		data : contact,
		success : callback,
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Erro atualizando contato: ' + textStatus);
		}
	});
}

function deleteContact(id, callback) {
	$.ajax({
		type : 'DELETE',
		url : rootURL + '?contactId=' + id,
		success : callback,
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Erro excluindo contato: ' + textStatus);
		}
	});
}


