// =============== Start of Citizen Form =======

var pageName="complain";
		
function  complain() {
	var lang=getCookie('lang');
	var menues;
	if(lang=='en'){
		menues=certificate_form_en;
	}else{
		menues=certificate_form_bn;
	}
    document.getElementById("lab_mh").innerHTML = menues.lab_mh;
    document.getElementById("lab_sh").innerHTML = menues.lab_sh;
    document.getElementById("lab_fn").innerHTML = menues.form_info.lab_fn;
    document.getElementById("lab_addr").innerHTML = menues.form_info.lab_addr;
    document.getElementById("lab_email").innerHTML = menues.form_info.lab_email;
    document.getElementById("lab_mob").innerHTML = menues.form_info.lab_mob;
    document.getElementById("lab_sub").innerHTML = menues.form_info.lab_sub;
    document.getElementById("lab_desc").innerHTML = menues.form_info.lab_desc;
    document.getElementById("lab_pic1").innerHTML = menues.form_info.lab_pic1;
    document.getElementById("lab_pic2").innerHTML = menues.form_info.lab_pic2;
    document.getElementById("lab_pic3").innerHTML = menues.form_info.lab_pic3;

}


var certificate_form_bn ={
    lab_mh:'নাগরিক অভিযোগ ফর্ম',
    lab_sh:'(বাংলা সংস্করণ)',
    form_info:{
        lab_fn:'পুরো নাম:',
        lab_addr:'ঠিকানা:',
        lab_email:'ইমেইল:',
        lab_mob:'মোবাইল নম্বর:',
        lab_sub:'বিষয়',
        lab_desc:'বিবরণ:',
        lab_pic1:'ছবি আপলোড করুন (1)',
        lab_pic2:'ছবি আপলোড করুন (2)',
        lab_pic3:'ছবি আপলোড করুন (3)',
    }
};


var certificate_form_en ={
    lab_mh:'Complain Submit Form',
    lab_sh:'(English Version)',
    form_info:{
        lab_fn:'Full Name:',
        lab_addr:'Address:',
        lab_email:'Email:',
        lab_mob:'Mobile Number:',
        lab_sub:'Subject:',
        lab_desc:'Description:',
        lab_pic1:'Upload Picture(1)',
        lab_pic2:'Upload Picture(2)',
        lab_pic3:'Upload Picture(3)',
    }
};

