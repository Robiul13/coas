
function saveOtherCertificateData() {
	document.getElementById("trackingNo").innerHTML = '';
	var form = $('#othersApplicationForm')[0];
	var data = new FormData(form);
	data.append('lang', getCookie('lang'));
	var url = document.getElementById("othersApplicationForm").action;

	$("#othersSubmitButton").prop("disabled", true);

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
							+ "<strong>Success!</strong> Thanks for submitting Application for Certificate . Your Application tracking No. is. <strong>"
							+ result.trackingNo + "</strong>";

					document.getElementById("trackingNo").innerHTML = msg;

					$("#othersSubmitButton").prop("disabled", false);
					$('#othersApplicationForm')[0].reset();
					 $(".img-upload").attr("src", '');
				},
				error : function(jqXHR, textStatus, errorThrown) {

					$("#trackingNo").html(jqXHR.responseText);
					console.log("ERROR : ", jqXHR.responseText);
					$("#othersSubmitButton").prop("disabled", false);

				}
			});

}

$(document)
.ready(
		function() {

			othersCertificate();
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

			 $("#othersSubmitButton").click(function(event) {
			        /*event.preventDefault();
			        saveOtherCertificateData();*/
			    });


		});

$("input[name='fhType']").change(
		function() {
			if ($(this).val() === 'F') {
				if(getCookie('lang')=='bn'){
					$("#fhName").attr("placeholder"," পিতার নাম লিখুন");
				}else{
					$("#fhName").attr("placeholder","Enter Father Name");
				}				
			}
			if ($(this).val() === 'H') {
				if(getCookie('lang')=='bn'){
					$("#fhName").attr("placeholder"," স্বামীর নাম লিখুন");
				}else{
					$("#fhName").attr("placeholder", "Enter Husband Name");
				}
			}
		});

$("input[name='afhType']").change(
		function() {
			if ($(this).val() === 'F') {
				if(getCookie('lang')=='bn'){
					$("#applicantFHname").attr("placeholder"," আবেদনকারীর পিতার নাম লিখুন");
				}else{
					$("#applicantFHname").attr("placeholder","Enter Applicant Father Name");
				}				
			}
			if ($(this).val() === 'H') {
				if(getCookie('lang')=='bn'){
					$("#applicantFHname").attr("placeholder"," আবেদনকারীর স্বামীর নাম লিখুন");
				}else{
					$("#applicantFHname").attr("placeholder", "Enter Applicant Husband Name");
				}
			}
		});


$.validator.setDefaults({
    submitHandler: function () {
        saveOtherCertificateData();
    }
});

$(document).ready(function () {
    $.validator.addMethod("IsValidMobile", function (value, element) {
        var mobileRegEx = new RegExp(/(^(\+88|0088)?(01){1}[3-9]{1}(\d){8})$/);

        return this.optional(element) || mobileRegEx.test(value);
    }, "Please provide a valid mobile No.");

    $("#othersApplicationForm").validate({
        rules: {
            pName: "required",
            fhName: "required",
            motherName: "required",
            presentAddr: "required",
            parmanentAddr: "required",
            applicantName: "required",
            applicantMobile:{
                required:true,
                IsValidMobile:true
			},
            afhName: "required",
            applicantAddr: "required",
            img1: "required",
            img2: "required"

        },
        messages: {
            pName: "Full name is required!",
            fhName: "Father/Husband name is required!",
            motherName: "Mother name is required!",
            presentAddr: "Present Address is required!",
            parmanentAddr: "Parmanent Address is required!",
            applicantName: "Full name is required!",
            applicantMobile: "Applicant Mobile is required!",
            afhName: "Father/Husband name is required!",
            applicantAddr: "Address is required!",
            img1: "Image is required!",
            img2: "Image is required!"
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

