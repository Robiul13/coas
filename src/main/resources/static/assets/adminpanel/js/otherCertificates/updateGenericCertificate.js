$("#status").change(function() {
	$("#updBtn").prop("disabled", false);

});

$("#updBtn").click(function(event) {
	event.preventDefault();
	updateGenericForm();
});

function updateGenericForm() {

	var selected = $("#status").val();
	var current = $("#statusDb").val();

	if (selected == current) {
		$("#updBtn").prop("disabled", true);
		alert("Please change the status!");
		return false;
	}

	$('#status_temp').val($("#status").val());
	$('#remark_temp').val($("#remark").val());
	$('#id_temp').val($("#citizenId").val());

	// Submit the form
	$("#updateGenericForm").submit();

}
