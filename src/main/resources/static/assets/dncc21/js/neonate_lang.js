var pageName = "neonate";

function neonate() {

	var lang = getCookie('lang');
	var labels;

	if (lang == 'en') {
		labels = neonate_form_en;
	} else {
		labels = neonate_form_bn;
	}

	document.getElementById("main_header").innerHTML = labels.main_header;
	document.getElementById("label_dob").innerHTML = labels.label_dob;
	document.getElementById("label_father").innerHTML = labels.label_father;
	document.getElementById("label_mother").innerHTML = labels.label_mother; 
	document.getElementById("label_presentAddr").innerHTML = labels.label_presentAddr;
	document.getElementById("label_permanentAddr").innerHTML = labels.label_permanentAddr;
	document.getElementById("main_header").innerHTML = labels.main_header;
	document.getElementById("label_gender").innerHTML = labels.label_gender;
	document.getElementById("label_gender_boy").innerHTML = labels.label_gender_boy;
	document.getElementById("label_gender_girl").innerHTML = labels.label_gender_girl;
	document.getElementById("label_hospital").innerHTML = labels.label_hospital;
	document.getElementById("label_mobile").innerHTML = labels.label_mobile;
	document.getElementById("father").placeholder = labels.placeholder_father;
	document.getElementById("mother").placeholder = labels.placeholder_mother;
	document.getElementById("presentAddr").placeholder = labels.placeholder_presentAddr;
	document.getElementById("permanentAddr").placeholder = labels.placeholder_permanentAddr;
	document.getElementById("hospital").placeholder = labels.placeholder_hospital;
	document.getElementById("mobile").placeholder = labels.placeholder_mobile;
}

var neonate_form_en = {
	main_header : 'Neonate Data Input',	
	label_dob : 'Date of Birth',	
	label_father:'Father Name of Baby:',
	label_mother:'Mother Name of Baby:',
	label_presentAddr:'Present Address of Family:', 
	label_permanentAddr:'Permanent Address of Family:',  
	label_gender:'Gender of Baby:',
	label_gender_boy:'Boy',
	label_gender_girl:'Girl',
	label_hospital:'Hospital Name:',
	label_mobile:'Contact Number of Family:',	
	placeholder_father : 'Enter Father Name of Baby',
    placeholder_mother : 'Enter Mother Name of Baby',
    placeholder_presentAddr : 'Enter Present Address of Family',
    placeholder_permanentAddr : 'Enter Permanent Address of Family',
    placeholder_hospital : 'Enter Hospital Name',
	placeholder_mobile : 'Contact Number of Family'
};


var neonate_form_bn = {		
		main_header : 'নবজাত শিশুর তথ্য প্রদান ফর্ম',		
		label_dob:'শিশুর জন্ম তারিখ:',
		label_father:'শিশুর বাবার নাম:',
		label_mother:'শিশুর মায়ের নাম:',
		label_presentAddr:'বর্তমান ঠিকানা:',
		label_permanentAddr:'স্থায়ী ঠিকানা:',
		label_gender:'শিশুর লিঙ্গ:', 
		label_gender_boy:'ছেলে',
		label_gender_girl:'মেয়ে',
		label_hospital:'হাসপাতালের নাম:',
		label_mobile:'পরিবারের মোবাইল নম্বর:',		
		placeholder_father : 'শিশুর বাবার নাম লিখুন',
	    placeholder_mother : 'শিশুর মায়ের নাম লিখুন',
	    placeholder_presentAddr : 'বর্তমান ঠিকানা লিখুন',
	    placeholder_permanentAddr : 'স্থায়ী ঠিকানা লিখুন',
	    placeholder_hospital : 'হাসপাতালের নাম লিখুন',
		placeholder_mobile : 'পরিবারের মোবাইল নম্বর লিখুন'
			};

