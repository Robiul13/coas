
    $.validator.setDefaults({
        submitHandler: function () {
            saveNeonateData();
        }
    });

    $(document).ready(function () {
        neonate();
        $.validator.addMethod("IsValidMobile", function (value, element) {
            var mobileRegEx = new RegExp(/(^(\+88|0088)?(01){1}[3-9]{1}(\d){8})$/);

            return this.optional(element) || mobileRegEx.test(value);
        }, "Please provide a valid mobile No.");

        $("#neonateApplicationForm").validate({
            rules: {
                dob: {
                	required:true
				},
                father: {
                	required:true
				},
                mother:"required",
                presentAddr: "required",
                mobile: {
                    required: true,
                    IsValidMobile:true
                },
                permanentAddr: "required",
                hospital: "required"
            },
            messages: {
                dob: "Date of Birth is required!",
                father: "Father name is required!",
                mother: "Mother name is required!",
                presentAddr: "Present address is required!",
                permanentAddr: "Permanent address is required!",
                mobile: "Contact number is required!",
                hospital: "Hospital name is required!"
            },
            errorElement: "p",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");
                $(error).insertAfter(element);

                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents(".form-group").addClass("has-feedback");

                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!element.next("span")[0]) {
                    $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
                }
            },
            success: function (label, element) {
                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!$(element).next("span")[0]) {
                    $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
                }
            },
            highlight: function (element, errorClass, validClass) {
            	document.getElementById("trackingNo").innerHTML = '';
                $(element).parents(".form-group").addClass("has-error").removeClass("has-success");
                $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".form-group").addClass("has-success").removeClass("has-error");
                $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
            }
        });
    });

    /*$(document).ready(function() {
        neonate();

        $("#neonateSubmitButton").click(function(event) {
            event.preventDefault();
            saveNeonateData();
        });

    });*/

    function saveNeonateData() {
    	document.getElementById("trackingNo").innerHTML = '';
	    
	    var url=document.getElementById("neonateApplicationForm").action;
	    
	    $("#neonateSubmitButton").prop("disabled", true);

	    var param={
			    lang: getCookie('lang'),
			    father: $('#father').val(),
			    mother: $('#mother').val(),
			    gender: $('#gender').val(),
			    hospital: $('#hospital').val(),
			    mobile: $('#mobile').val(),
			    presentAddr: $('#presentAddr').val(),
			    permanentAddr: $('#permanentAddr').val(),
			    dob: $('#dob').val()
			  };
	    
		var paramStr = JSON.stringify(param);
		
		 
		  $.ajax({ 
			  url:url,
			  data: paramStr,
			  contentType: "application/json", 
			  success: function (data) { 
				 
				  var  result = JSON.parse(data); 		
		        	 
		        	 var msg="<button type='button' class='close' data-dismiss='alert'>&times;</button>"
		        	 +"<strong>Success!</strong> Thanks for submitting Neonate Data. Your Neonate tracking No. is. <strong>"
		        	 +result.trackingNo+"</strong>";        	    
		        	 
		           document.getElementById("trackingNo").innerHTML = msg;
		           
		            $("#neonateSubmitButton").prop("disabled", false);
		            $('#neonateApplicationForm')[0].reset();
		           
		            
				  },
		     error: function (data) {
		    	 
		    	 console.log("Server ERROR : ");
		            $("#neonateSubmitButton").prop("disabled", false);
		            
		    	 }, 
		     type: 'POST'		   
		  });

	}

	
