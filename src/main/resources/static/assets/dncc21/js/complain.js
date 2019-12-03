$(document).ready(function () {

    complain();

    $(document).on('change', '.btn-file :file', function () {
        var input = $(this),
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [label]);
    });

    $('.btn-file :file').on('fileselect', function (event, label) {

        var input = $(this).parents('.input-group').find(':text'),
            log = label;

        if (input.length) {
            input.val(log);
        } else {
            if (log) alert(log);
        }

    });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                var img = new Image();
                img.src = e.target.result;
                img.onload = function () {
                    alert(this.width + " " + this.height);
                };
                $('#pic1Upload').attr('src', e.target.result);

            }
            reader.readAsDataURL(input.files[0]);
        }
    }


    $("#pic1").change(function () {
        readURL(this, '#pic1Upload');
    });

    $("#pic2").change(function () {
        readURL(this, "#pic2Upload");
    });

    $("#pic3").change(function () {
        readURL(this, "#pic3Upload");
    });

    function readURL(input, selector) {
        var FileSize = input.files[0].size / 1024; // in KB
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


});
/*==================================================================================*/
/*$(document).ready(function() {

    $("#complaiEnSubmit").click(function(event) {
        event.preventDefault();
        saveComplainData();

    });

});*/
/*==================================================================================*/

$.validator.setDefaults({
    submitHandler: function () {
        saveComplainData();
    }
});

$(document).ready(function () {

    $.validator.addMethod("IsValidEmail", function (value, element) {
        var emailRegEx = new RegExp(/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/);

        return this.optional(element) || emailRegEx.test(value);
    }, "Please provide a valid email.");

    $.validator.addMethod("IsValidMobile", function (value, element) {
        var mobileRegEx = new RegExp(/(^(\+88|0088)?(01){1}[3-9]{1}(\d){8})$/);

        return this.optional(element) || mobileRegEx.test(value);
    }, "Please provide a valid mobile No.");

    $("#complainEnForm").validate({
        rules: {
            name: "required",
            mobile: {
                required: true,
                IsValidMobile:true
            },
            email: {
                required: true,
                IsValidEmail: true
            },
            subject: "required",
            description: "required",
            pic1: "required"
        },
        messages: {
            name: "Full name is required!",
            subject: "Subject is required!",
            description: "Description is required!",
            pic1: "Picture is required!"
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


function saveComplainData() {
	document.getElementById("trackingNo").innerHTML = '';
    var form = $('#complainEnForm')[0];
    var data = new FormData(form);
    data.append('lang', getCookie('lang'));

    var url = document.getElementById("complainEnForm").action;

    $("#complaiEnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: url,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function (data, textStatus, jqXHR) {
            var result = JSON.parse(data);

            var msg = "<button type='button' class='close' data-dismiss='alert'>&times;</button>"
                + "<strong>Success!</strong> Thanks for submitting complain. Your complain tracking No. is. <strong>"
                + result.trackingNo + "</strong>";

            document.getElementById("trackingNo").innerHTML = msg;

            $("#complaiEnSubmit").prop("disabled", false);
            $('#complainEnForm')[0].reset();
            $(".img-upload").attr("src", '');
        },
        error: function (jqXHR, textStatus, errorThrown) {

            $("#trackingNo").html(jqXHR.responseText);
            console.log("ERROR : ", jqXHR.responseText);
            $("#complaiEnSubmit").prop("disabled", false);

        }
    });

}

	
	
	