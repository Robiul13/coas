// =============== Start of Citizen Form =======

var pageName = "citizenCertificate";

function citizenCertificate() {

}

function loadCcForm(obj) {
	var lang = obj.value;
	document.getElementById("citizenCertificateDiv").style.display = "";
	var labels;
	if (lang == 'en') {
		labels = certificate_form_en;

	} else {
		labels = certificate_form_bn;

	}
	
	var ctx=document.getElementsByTagName('meta')['ctx'].getAttribute("content");
	
	document.getElementById("ccApplicationLang").value = lang;
	document.getElementById("citizenForm").action =ctx+labels.form_url;
	
	document.getElementById("main_header").innerHTML = labels.main_header;
	document.getElementById("sub_header").innerHTML = labels.sub_header;
	document.getElementById("label_info_full_name").innerHTML = labels.form_info.label_info_full_name;
	document.getElementById("label_info_father_name").innerHTML = labels.form_info.label_info_father_name;
	document.getElementById("label_info_husband_name").innerHTML = labels.form_info.label_info_husband_name;
	document.getElementById("label_info_mother_name").innerHTML = labels.form_info.label_info_mother_name;
	document.getElementById("label_info_pre_addr").innerHTML = labels.form_info.label_info_pre_addr;
	document.getElementById("label_info_per_addr").innerHTML = labels.form_info.label_info_per_addr;
	document.getElementById("label_info_mob_no").innerHTML = labels.form_info.label_info_mob_no;
	document.getElementById("label_info_reference").innerHTML = labels.form_info.label_info_reference;
	document.getElementById("label_info_nid_front").innerHTML = labels.form_info.label_info_nid_front;
	document.getElementById("label_info_nid_back").innerHTML = labels.form_info.label_info_nid_back;
	document.getElementById("label_info_pp_photo").innerHTML = labels.form_info.label_info_pp_photo;
	document.getElementById("label_info_pp_photo").innerHTML = labels.form_info.label_info_pp_photo;
	
	document.getElementById("nid1_browse").innerHTML = labels.form_info.label_info_browse;
	document.getElementById("nid2_browse").innerHTML = labels.form_info.label_info_browse;
	document.getElementById("pp_browse").innerHTML = labels.form_info.label_info_browse;
	
	document.getElementById("name").placeholder=labels.form_info.prompt_info_full_name;
	document.getElementById("fhName").placeholder=labels.form_info.prompt_info_father_name;
	document.getElementById("motherName").placeholder=labels.form_info.prompt_info_mother_name;
	document.getElementById("presentAddr").placeholder=labels.form_info.prompt_info_pre_addr;
	document.getElementById("permanentAddr").placeholder=labels.form_info.prompt_info_per_addr;
	document.getElementById("mobile").placeholder=labels.form_info.prompt_info_mob_no;
	document.getElementById("reference").placeholder=labels.form_info.prompt_info_reference;
	
}

var certificate_form_bn = {
	form_url:'api/v1/services/citizen/bn/saveApplication',
	main_header : 'নাগরিক সনদ  আবেদনপত্র',
	sub_header : 'বাংলা সংস্করণ',
	form_info : {
		label_info_full_name : 'নাম',
		label_info_father_name : 'পিতার নাম',
		label_info_husband_name : 'স্বামীর নাম',
		label_info_mother_name : 'মায়ের নাম',
		label_info_pre_addr : 'বর্তমান ঠিকানা',
		label_info_per_addr : 'স্থায়ী ঠিকানা',
		label_info_mob_no : 'মোবাইল নম্বর',
		label_info_reference : 'ব্যক্তিগত রেফারেন্স',
		label_info_nid_front : 'NID আপলোড করুন (ফ্রন্ট)',
		label_info_nid_back : 'NID আপলোড করুন (পিছনে)',
		label_info_pp_photo : 'আপলোড ছবি',
		label_info_browse : 'ব্রাউজ',
		prompt_info_full_name : 'আপনার পুরো নাম লিখুন',
		prompt_info_father_name : 'আপনার পিতার নাম লিখুন',
		prompt_info_husband_name : 'আপনার স্বামীর  নাম লিখুন',
		prompt_info_mother_name : 'আপনার মায়ের নাম লিখুন',
		prompt_info_pre_addr : 'আপনার বর্তমান ঠিকানা লিখুন',
		prompt_info_per_addr : 'আপনার স্থায়ী ঠিকানা লিখুন',
		prompt_info_mob_no : 'আপনার মোবাইল নাম্বার প্রবেশ করুন',
		prompt_info_reference : 'আপনার রেফারেন্স নাম, ঠিকানা এবং ফোন নম্বর লিখুন',
	}
};

var certificate_form_en = {
		form_url:'api/v1/services/citizen/en/saveApplication',
	main_header : 'Citizen Certificate Application form',
	sub_header : '(English Version)',
	form_info : {
		label_info_full_name : 'Full Name:',
		label_info_father_name : 'Father Name',
		label_info_husband_name : 'Husband Name',
		label_info_mother_name : 'Mother Name:',
		label_info_pre_addr : 'Present Address:',
		label_info_per_addr : 'Permanent Address:',
		label_info_mob_no : 'Mobile Number:',
		label_info_reference : 'Personal Reference:',
		label_info_nid_front : 'Upload NID(Front)',
		label_info_nid_back : 'Upload NID(Back)',
		label_info_pp_photo : 'Upload Picture',
		label_info_browse : 'Browse',
		prompt_info_full_name : 'Enter your full name',
		prompt_info_father_name : 'Enter your Father Name',
		prompt_info_husband_name : 'Enter your Husband Name',
		prompt_info_mother_name : 'Enter your mother name',
		prompt_info_pre_addr : 'Enter your Present Address',
		prompt_info_per_addr : 'Enter your Permanent Address',
		prompt_info_mob_no : 'Enter your Mobile Number',
		prompt_info_reference : 'Enter your Reference Name, Address and Phone Number',
	}
};
