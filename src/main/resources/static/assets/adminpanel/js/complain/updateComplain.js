$("#status").change(function() {
	$("#updBtn").prop("disabled", false);
	
	/*var selected = $(this).val();
	var current = $("#statusDb").val();

	var flag = confirm("Do you want to change status of this application?");
	if (flag == true) {
		$("#updBtn").prop("disabled", false);

	} else {
		$("#updBtn").prop("disabled", true);
		$(this).val(current);
		return false;
	}
*/
});

$("#updBtn").click(function(event) {
	event.preventDefault();
	updComplain();
});

function updComplain() {
	var selected = $("#status").val();
	var current = $("#statusDb").val();
	
	if(selected==current){
		$("#updBtn").prop("disabled", true);
		alert("Please change the status!");
		return false;
	}
	
	$('#status_temp').val($("#status").val());
	$('#remark_temp').val($("#remark").val());
	$('#id_temp').val($("#complainId").val());

	$("#updateCompalinForm").submit(); // Submit the form

}