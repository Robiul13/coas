var pageName = "othersCertificate";

function othersCertificate() {

	var lang = getCookie('lang');
	var labels;

	if (lang == 'en') {
		labels = othersCertificate_form_en;

	} else {
		labels = othersCertificate_form_bn;
	}

	document.getElementById("main_header").innerHTML = labels.main_header;
	document.getElementById("label_certificateType").innerHTML = labels.label_certificateType;
	document.getElementById("label_certificateType_unmarried").innerHTML = labels.label_certificateType_unmarried;
	document.getElementById("label_certificateType_remarriage").innerHTML = labels.label_certificateType_remarriage;
	document.getElementById("label_certificateType_monthlyIncome").innerHTML = labels.label_certificateType_monthlyIncome;
	document.getElementById("label_certificateType_poverty").innerHTML = labels.label_certificateType_poverty;
	document.getElementById("label_certificateType_family").innerHTML = labels.label_certificateType_family;
	document.getElementById("label_pName").innerHTML = labels.label_pName;
	document.getElementById("pName").placeholder = labels.placeholder_pName;
	document.getElementById("label_father_name").innerHTML = labels.label_father_name;
	document.getElementById("label_info_husband_name").innerHTML = labels.label_info_husband_name;
	document.getElementById("fhName").placeholder = labels.placeholder_fhName;
	document.getElementById("label_presentAddr").innerHTML = labels.label_presentAddr;
	document.getElementById("presentAddr").placeholder = labels.placeholder_presentAddr;
	document.getElementById("label_motherName").innerHTML = labels.label_motherName;
	document.getElementById("motherName").placeholder = labels.placeholder_motherName;
	document.getElementById("label_parmanentAddr").innerHTML = labels.label_parmanentAddr;
	document.getElementById("parmanentAddr").placeholder = labels.placeholder_parmanentAddr;
    document.getElementById("label_applicantName").innerHTML = labels.label_applicantName;
    document.getElementById("label_applicant_father_name").innerHTML = labels.label_applicant_father_name;
    document.getElementById("applicantName").placeholder = labels.placeholder_applicantName;
    document.getElementById("applicantFHname").placeholder = labels.placeholder_applicantFHname;
    document.getElementById("label_applicantMobile").innerHTML = labels.label_applicantMobile;
    document.getElementById("applicantMobile").placeholder = labels.placeholder_applicantMobile;
    document.getElementById("label_applicantAddr").innerHTML = labels.label_applicantAddr;
    document.getElementById("applicantAddr").placeholder = labels.placeholder_applicantAddr;
    document.getElementById("label_attachments").innerHTML = labels.label_attachments;
    document.getElementById("label_applicant_info").innerHTML = labels.label_applicant_info;
	document.getElementById("label_applicant_husband_name").innerHTML = labels.label_applicant_husband_name;
	document.getElementById("label_Affidavit").innerHTML = labels.label_Affidavit;
	document.getElementById("label_utilityBill").innerHTML = labels.label_utilityBill;

}

var othersCertificate_form_bn = {
    main_header : 'অন্যান্য সনদ  আবেদন ফর্ম:',
    label_certificateType: 'সনদ এর ধরন নির্বাচন করুন',
    label_certificateType_unmarried:'অবিবাহিত সনদ',
    label_certificateType_remarriage:'পুনর্বিবাহ না হওয়ার  সনদ ',
    label_certificateType_monthlyIncome:'মাসিক / বার্ষিক আয়ের সনদ',
    label_certificateType_poverty:'অসচ্ছলতা সনদ',
    label_certificateType_family:'পারিবারিক সনদ',

    label_pName : 'ব্যক্তির নাম:',
    placeholder_pName : 'ব্যক্তির পুরো নাম লিখুন:',

    label_father_name : 'বাবার নাম:',
    label_info_husband_name:'স্বামী নাম:',
    placeholder_fhName : 'পিতার নাম লিখুন:',

    label_presentAddr : 'বর্তমান ঠিকানা:',
    placeholder_presentAddr : 'বর্তমান ঠিকানা:',

    label_motherName : 'মায়ের নাম:',
    placeholder_motherName : 'ব্যক্তির মায়ের নাম লিখুন:',

    label_parmanentAddr : 'ব্যক্তির স্থায়ী ঠিকানা:',
    placeholder_parmanentAddr : 'স্থায়ী ঠিকানা লিখুন',

	label_applicantName : 'আবেদনকারীর নাম:',
	placeholder_applicantName : 'আবেদনকারীর সম্পূর্ণ নাম লিখুন',

	placeholder_applicantFHname : 'পিতার নাম লিখুন:',
    label_applicant_husband_name:'আবেদনকারীর স্বামী নাম:',
    label_applicant_father_name:'আবেদনকারীর বাবার নাম:',

	label_applicantMobile : 'আবেদনকারীর মোবাইল নাম্বার:',
	placeholder_applicantMobile : 'আবেদনকারীর মোবাইল নাম্বার লিখুন',

	label_applicantAddr : 'আবেদনকারীর ঠিকানা :',
	placeholder_applicantAddr : 'আবেদনকারীর ঠিকানা  লিখুন',

	label_attachments : 'সংযুক্তিসমূহ',
	label_applicant_info : 'আবেদনকারীর  তথ্য',
	label_Affidavit : 'নোটারি পাবলিক থেকে ইস্যুকৃত হলফনামা  <br/> আপলোড করুন :',
	label_utilityBill : 'বিদ্যুৎ / গ্যাস / পানি বিল  / জাতীয় পরিচয়পত্র / পাসপোর্ট এর  সত্যায়িত কপি / স্থায়ী হলে বাড়ির পৌরকর  পরিশোধের কপি :'

};

var othersCertificate_form_en = {
	main_header : 'Others Certificate Application form',
    label_certificateType: 'Select Certificate Type',
    label_certificateType_unmarried:'UNMARRIED CERTIFICATE',
    label_certificateType_remarriage:'NON REMARRIAGE CERTIFICATE',
    label_certificateType_monthlyIncome:'MONTHLY/YEARLY INCOME CERTIFICATE',
    label_certificateType_poverty:'INSOLVENCY CERTIFICATE',
    label_certificateType_family:'FAMILY CERTIFICATE',

	label_pName : 'Name of the person:',
	placeholder_pName : 'Enter full name of the person',

    label_father_name : 'Father Name:',
    label_info_husband_name:'Husband Name:',

    placeholder_fhName : 'Enter Father Name:',
    label_presentAddr : 'Present Address of Death person:',

    placeholder_presentAddr : 'Enter Present Address of Death person',
    label_motherName : 'Mother Name:',

	placeholder_motherName : 'Enter Mother Name of the person',
    label_parmanentAddr : 'Permanent Address of Death person:',

    placeholder_parmanentAddr : 'Enter Permanent Address',
    label_applicantName : 'Applicant Name:',

	placeholder_applicantName : 'Enter applicant name',
	placeholder_applicantFHname : 'Enter applicant Father name',

    label_applicant_father_name : 'Applicant Father Name:',
    label_applicant_husband_name:'Applicant Husband Name:',

	label_applicantMobile : 'Applicant Mobile No:',
	placeholder_applicantMobile :'Enter Applicant Mobile No:',

	label_applicantAddr :'Applicant Address:',
	placeholder_applicantAddr :'Enter Applicant Address:',

	label_attachments : 'Attachments',
	label_applicant_info : 'Applicant Information',

	label_Affidavit : 'Upload Affidavit from notary public',
	label_utilityBill : 'Upload Applicant Utility Bill/ NID/Passport / Holding Tax Picture:'

};
