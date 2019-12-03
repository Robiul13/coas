var pageName = "heirCertificate";

function heirCertificate() {

	var lang = getCookie('lang');
	var labels;

	if (lang == 'en') {
		labels = heirCertificate_form_en;

	} else {
		labels = heirCertificate_form_bn;
	}

	document.getElementById("main_header").innerHTML = labels.main_header;
	document.getElementById("label_dName").innerHTML = labels.label_dName;
	document.getElementById("dName").placeholder = labels.placeholder_dName;
	document.getElementById("label_dRelation").innerHTML = labels.label_dRelation;
	document.getElementById("dRelation").placeholder = labels.placeholder_dRelation;
	document.getElementById("label_dFatherName").innerHTML = labels.label_dFatherName;
	document.getElementById("dFatherName").placeholder = labels.placeholder_dFatherName;
	document.getElementById("label_dPresentAddr").innerHTML = labels.label_dPresentAddr;
	document.getElementById("dPresentAddr").placeholder = labels.placeholder_dPresentAddr;
	document.getElementById("label_mStatus").innerHTML = labels.label_mStatus;
	document.getElementById("label_mStatus_married").innerHTML = labels.label_mStatus_married;
	document.getElementById("label_mStatus_single").innerHTML = labels.label_mStatus_single;
	document.getElementById("label_dGender").innerHTML = labels.label_dGender;
	document.getElementById("label_dGender_male").innerHTML = labels.label_dGender_male;
	document.getElementById("label_dGender_female").innerHTML = labels.label_dGender_female;	
	document.getElementById("label_dod").innerHTML = labels.label_dod;
	document.getElementById("label_dMotherName").innerHTML = labels.label_dMotherName;
	document.getElementById("dMotherName").placeholder = labels.placeholder_dMotherName;
	document.getElementById("label_dPermanentAddr").innerHTML = labels.label_dPermanentAddr;
	document.getElementById("dPermanentAddr").placeholder = labels.placeholder_dPermanentAddr;
	document.getElementById("label_dHusbandName").innerHTML = labels.label_dHusbandName;
	document.getElementById("dHusbandName").placeholder = labels.placeholder_dHusbandName;
	document.getElementById("label_heirList").innerHTML = labels.label_heirList;
	document.getElementById("label_hName").innerHTML = labels.label_hName;
	document.getElementById("label_hRelation").innerHTML = labels.label_hRelation;
	document.getElementById("label_hAge").innerHTML = labels.label_hAge;
	document.getElementById("label_hRemark").innerHTML = labels.label_hRemark;

	var n1 = document.getElementsByName("heirName").length;
	for (var i = 0; i < n1; i++) {
		document.getElementsByName("heirName")[i].placeholder = labels.placeholder_hName;
	}
	var n2 = document.getElementsByName("heirRelation").length;
	for (var i = 0; i < n2; i++) {
		document.getElementsByName("heirRelation")[i].placeholder = labels.placeholder_hRelation;
	}
	var n3 = document.getElementsByName("heirAge").length;
	for (var i = 0; i < n3; i++) {
		document.getElementsByName("heirAge")[i].placeholder = labels.placeholder_hAge;
	}
	var n4 = document.getElementsByName("heirRemark").length;
	for (var i = 0; i < n4; i++) {
		document.getElementsByName("heirRemark")[i].placeholder = labels.placeholder_hRemark;
	}
	document.getElementById("label_applicantInfo").innerHTML = labels.label_applicantInfo;
	document.getElementById("label_applicantName").innerHTML = labels.label_applicantName;
	document.getElementById("applicantName").placeholder = labels.placeholder_applicantName;
	document.getElementById("label_applicantMobile").innerHTML = labels.label_applicantMobile;
	document.getElementById("applicantMobile").placeholder = labels.placeholder_applicantMobile;
	document.getElementById("label_applicantAddr").innerHTML = labels.label_applicantAddr;
	document.getElementById("applicantAddr").placeholder = labels.placeholder_applicantAddr;
	document.getElementById("label_applicantNidNo").innerHTML = labels.label_applicantNidNo;
	document.getElementById("applicantNidNo").placeholder = labels.placeholder_applicantNidNo;
	document.getElementById("label_attachments").innerHTML = labels.label_attachments;
	document.getElementById("label_Affidavit").innerHTML = labels.label_Affidavit;
	document.getElementById("label_cemeteryReceipt").innerHTML = labels.label_cemeteryReceipt;
	document.getElementById("label_utilityBill").innerHTML = labels.label_utilityBill;

}

var heirCertificate_form_bn = {
	main_header : 'ওয়ারিশ সনদ প্রদানের আবেদনপত্র ',
	label_dName : 'মৃত ব্যক্তির নাম:',
	placeholder_dName : 'মৃত ব্যক্তির সম্পূর্ণ নাম লিখুন',
	label_dRelation : 'মৃত ব্যক্তি সঙ্গে আবেদনকারী সম্পর্ক:',
	placeholder_dRelation : 'মৃত ব্যক্তি সঙ্গে আবেদনকারী সম্পর্ক লিখুন',
	label_dFatherName : 'মৃত ব্যক্তির পিতার নাম:',
	placeholder_dFatherName : 'মৃত ব্যক্তির পিতার নাম লিখুন',
	label_dPresentAddr : 'মৃত ব্যক্তির বর্তমান ঠিকানা:',
	placeholder_dPresentAddr : 'মৃত ব্যক্তির বর্তমান ঠিকানা লিখুন',
	label_mStatus : 'মৃত ব্যক্তির বৈবাহিক অবস্থা:',
	label_mStatus_married : 'বিবাহিত',
	label_mStatus_single : 'অবিবাহিত',
	label_dGender : 'মৃত ব্যক্তির লিঙ্গ:',
	label_dGender_male : 'পুরুষ',
	label_dGender_female : 'মহিলা',
	label_dod : 'মৃত্যুর তারিখ:',
	label_dMotherName : 'মৃত ব্যক্তির মায়ের নাম:',
	placeholder_dMotherName : 'মৃত ব্যক্তির মায়ের নাম লিখুন',
	label_dPermanentAddr : 'মৃত ব্যক্তির স্থায়ী ঠিকানা:',
	placeholder_dPermanentAddr : 'মৃত ব্যক্তির স্থায়ী ঠিকানা লিখুন',
	label_dHusbandName : 'মৃত ব্যক্তির স্বামীর নাম:',
	placeholder_dHusbandName : 'মৃত ব্যক্তির স্বামীর নাম লিখুন',
	label_heirList : 'ওয়ারিশগণের তালিকা ',
	label_hName : 'নাম',
	placeholder_hName : 'ওয়ারিশের সম্পূর্ণ নাম লিখুন',
	label_hRelation : 'সম্পর্ক',
	placeholder_hRelation : 'মৃত ব্যক্তি সঙ্গে  সম্পর্ক ',
	label_hAge : 'বয়স',
	placeholder_hAge : 'ওয়ারিশের বয়স ',
	label_hRemark : 'মন্তব্য',
	placeholder_hRemark : 'মন্তব্য  লিখুন',
	label_applicantInfo : 'আবেদনকারীর তথ্য',
	label_applicantName : 'আবেদনকারীর নাম:',
	placeholder_applicantName : 'আবেদনকারীর সম্পূর্ণ নাম লিখুন',
	label_applicantMobile : 'আবেদনকারীর মোবাইল নাম্বার:',
	placeholder_applicantMobile : 'আবেদনকারীর মোবাইল নাম্বার লিখুন',
	label_applicantAddr : 'আবেদনকারীর ঠিকানা :',
	placeholder_applicantAddr : 'আবেদনকারীর ঠিকানা  লিখুন',
	label_applicantNidNo : 'আবেদনকারীর জাতীয় পরিচয়পত্র নাম্বার:',
	placeholder_applicantNidNo : 'আবেদনকারীর জাতীয় পরিচয়পত্র নাম্বার  লিখুন',
	label_attachments : 'সংযুক্তিসমূহ',
	label_Affidavit : 'নোটারি পাবলিক থেকে ইস্যুকৃত হলফনামা  আপলোড করুন',
	label_cemeteryReceipt : 'মৃত্যুর সনদপত্র  বা কবরস্থানের রশিদ আপলোড করুন',
	label_utilityBill : 'বিদ্যুৎ / গ্যাস / পানি বিল  / জাতীয় পরিচয়পত্র / পাসপোর্ট এর  সত্যায়িত কপি / স্থায়ী হলে বাড়ির পৌরকর  পরিশোধের কপি :'

};

var heirCertificate_form_en = {
	main_header : 'Heir Certificate Application form',
	label_dName : 'Name of the person who died:',
	placeholder_dName : 'Enter full name of the person who died',
	label_dRelation : 'Applicant Relation with death person:',
	placeholder_dRelation : 'Enter Applicant Relation with death person',
	label_dFatherName : 'Father Name of Death person:',
	placeholder_dFatherName : 'Enter Father Name of Death person',
	label_dPresentAddr : 'Present Address of Death person:',
	placeholder_dPresentAddr : 'Enter Present Address of Death person',
	label_mStatus : 'Marital Status of Death person:',
	label_mStatus_married : 'Married',
	label_mStatus_single : 'Single',
	label_dGender : 'Gender of Death person:',
	label_dGender_male : 'Male',
	label_dGender_female : 'Female',
	label_dod : 'Date of death:',
	label_dMotherName : 'Mother Name of Death person:',
	placeholder_dMotherName : 'Enter Mother Name of Death person',
	label_dPermanentAddr : 'Permanent Address of Death person:',
	placeholder_dPermanentAddr : 'Enter Permanent Address of Death person',
	label_dHusbandName : 'Husband Name of Death person:',
	placeholder_dHusbandName : 'Enter Husband Name of Death person',
	label_heirList : 'Heir List',
	label_hName : 'Name',
	placeholder_hName : 'Enter heir name',
	label_hRelation : 'Relation',
	placeholder_hRelation : 'Enter heir relation',
	label_hAge : 'Age',
	placeholder_hAge : 'Enter heir age',
	label_hRemark : 'Remark',
	placeholder_hRemark : 'Enter heir remark',
	label_applicantInfo : 'Applicant Information',
	label_applicantName : 'Applicant Name:',
	placeholder_applicantName : 'Enter applicant name',
	label_applicantMobile : 'Applicant Mobile No:',
	placeholder_applicantMobile : 'Enter Applicant Mobile No:',
	label_applicantAddr : 'Applicant Address:',
	placeholder_applicantAddr : 'Enter Applicant Address:',
	label_applicantNidNo : 'Applicant NID No:',
	placeholder_applicantNidNo : 'Enter Applicant NID No:',
	label_attachments : 'Attachments',
	label_Affidavit : 'Upload Affidavit from notary public',
	label_cemeteryReceipt : 'Upload Death Certificate / Cemetery Receipt',
	label_utilityBill : 'Upload Applicant Utility Bill/ NID/Passport / Holding Tax Picture:'

};
