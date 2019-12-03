function saveHeirData() {
	document.getElementById("trackingNo").innerHTML = '';
	var form = $('#heirApplicationForm')[0];
	var data = new FormData(form);
	data.append('lang', getCookie('lang'));

	var url = document.getElementById("heirApplicationForm").action;

	$("#heirSubmitButton").prop("disabled", true);

	$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : url,
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 1000000,
				success : function(data, textStatus, jqXHR) {

					var result = JSON.parse(data);
					var msg = "<button type='button' class='close' data-dismiss='alert'>&times;</button>"
							+ "<strong>Success!</strong> Thanks for submitting Heir Certificate Application. Your Application tracking No. is. <strong>"
							+ result.trackingNo + "</strong>";

					document.getElementById("trackingNo").innerHTML = msg;

					$("#heirSubmitButton").prop("disabled", false);
					$('#heirApplicationForm')[0].reset();
					 $(".img-upload").attr("src", '');
				},
				error : function(jqXHR, textStatus, errorThrown) {

					$("#trackingNo").html(jqXHR.responseText);
					console.log("ERROR : ", jqXHR.responseText);
					$("#heirSubmitButton").prop("disabled", false);

				}
			});

}

$(document)
.ready(
		function() {

			heirCertificate();

			$(".add-row")
					.click(
							function() {
								var lang = getCookie('lang');
								var markup = "";
								if (lang == 'bn') {
									markup = "<tr><td><input type='checkbox' name='record'></td><td><input type='text' class='form-control' name='heirName'	placeholder='"
											+ heirCertificate_form_bn.placeholder_hName
											+ "'></td><td><input type='text' class='form-control' name='heirRelation'	placeholder='"
											+ heirCertificate_form_bn.placeholder_hRelation
											+ "'></td><td><input type='text' class='form-control' name='heirAge'	placeholder='"
											+ heirCertificate_form_bn.placeholder_hAge
											+ "'></td><td><input type='text' class='form-control' name='heirRemark'	placeholder='"
											+ heirCertificate_form_bn.placeholder_hRemark
											+ "'></td></tr>";

								} else {
									markup = "<tr><td><input type='checkbox' name='record'></td><td><input type='text' class='form-control' name='heirName'	placeholder='Enter heir name'></td><td><input type='text' class='form-control' name='heirRelation'	placeholder='Enter heir relation'></td><td><input type='text' class='form-control' name='heirAge'	placeholder='Enter age'></td><td><input type='text' class='form-control' name='heirRemark'	placeholder='Enter heir remark'></td></tr>";
								}

								$("table tbody").append(markup);
							});

			// Find and remove selected table rows
			$(".delete-row").click(
					function() {
						$("table tbody").find('input[name="record"]')
								.each(function() {
									if ($(this).is(":checked")) {
										$(this).parents("tr").remove();
									}
								});
					});
			
			
			$(document).on('change', '.btn-file :file', function() {
				var input = $(this),
					label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				input.trigger('fileselect', [label]);
				});

				$('.btn-file :file').on('fileselect', function(event, label) {
				    
				    var input = $(this).parents('.input-group').find(':text'),
				        log = label;
				    
				    if( input.length ) {
				        input.val(log);
				    } else {
				        if( log ) alert(log);
				    }
			    
				});
			

			$("#img1").change(function(){
			    readURL(this, '#img1Upload');
			}); 
			
			$("#img2").change(function(){
				 readURL(this, "#img2Upload");
			}); 
			
			$("#img3").change(function(){
				 readURL(this, "#img3Upload");
			}); 
			
			function readURL(input, selector) {
				 var FileSize = input.files[0].size / 1024 ; // in KB
			        if (FileSize > 1024) {
			            alert('File size exceeds 1024K');					            
			            $(input).val(''); // for clearing with Jquery
			        } 
			    if (input.files && input.files[0]) {
			        var reader = new FileReader();			        
			        reader.onload = function (e) {
			            $(selector).attr('src', e.target.result);
			        }			        
			        reader.readAsDataURL(input.files[0]);
			    }
			}
			
			//  
			
			 $("#heirSubmitButton").click(function(event) {
			      /*  event.preventDefault();
			        saveHeirData();*/
			    });			

		});



$.validator.setDefaults({
    submitHandler: function () {
        saveHeirData();
    }
});

$(document).ready(function () {
    $.validator.addMethod("IsValidMobile", function (value, element) {
        var mobileRegEx = new RegExp(/(^(\+88|0088)?(01){1}[3-9]{1}(\d){8})$/);

        return this.optional(element) || mobileRegEx.test(value);
    }, "Please provide a valid mobile No.");

    $("#heirApplicationForm").validate({
        rules: {
            dName: "required",
            dRelation: "required",
            dFatherName: "required",
            dPresentAddr: "required",
            dod: "required",
            dMotherName: "required",
            applicantMobile:{
                required:true,
                IsValidMobile:true
            },
            dPermanentAddr: "required",
            applicantName: "required",
            applicantAddr: "required",
            applicantNidNo: "required",
            heirName: "required",
            heirRelation: "required",
            heirAge: "required",
            img1: "required",
            img2: "required",
            img3: "required"

        },
        messages: {
            dName: "Full name is required!",
            dRelation: "Relation info is required!",
            dFatherName: "Father name is required!",
            dMotherName: "Mother name is required!",
            dPresentAddr: "Present Address is required!",
            dPermanentAddr: "Permanent Address is required!",           
            dod: "Death of date is required!",
            applicantName: "Full name is required!",
            afhName: "Full name is required!",
            applicantAddr: "Address is required!",
            applicantNidNo: "NID No is required!",
            heirName: "Heir Name is required!",
            heirRelation: "Heir Relation is required!",
            heirAge: "Age is required!",
            img1: "Image is required!",
            img2: "Image is required!",
            img3: "Image is required!"
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
