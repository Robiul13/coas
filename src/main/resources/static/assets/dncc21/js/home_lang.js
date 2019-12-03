// =============== Start of Citizen Form =======

var pageName = "home";

function home() {
	
	var lang=getCookie('lang');
	lang=lang==""?"bn":lang;
	var labels;
	
	if (lang == 'en') {
		labels = home_page_en;

	} else {
		labels = home_page_bn;

	}
	
	document.getElementById("about_headline1").innerHTML = labels.about_headline1;
	document.getElementById("about_headline2").innerHTML = labels.about_headline2;
	document.getElementById("header_notice_title").innerHTML = labels.header_notice_title;
	/*document.getElementById("notice1").innerHTML = labels.notice1;
	document.getElementById("notice2").innerHTML = labels.notice2;
	document.getElementById("notice3").innerHTML = labels.notice3;
	document.getElementById("notice4").innerHTML = labels.notice4;*/
	load_aboutMGT();
	
	loadNotice();
}




var home_page_bn = {

      about_headline1 : 'মাসুম গনি তাপস',
      about_headline2 : 'কাউন্সিলর, ওয়ার্ড নং ২১, ডিএনসিসি',
      header_notice_title:'নোটিসবোর্ড',
      notice1:'1. নাগরিক সার্টিফিকেটের জন্য আবেদনপত্র',
      notice2:'2. উত্তরাধিকার সার্টিফিকেট জন্য আবেদন ফর্ম',
      notice3:'3. অন্যদের সার্টিফিকেট জন্য আবেদন ফর্ম',
      notice4:'4. অভিযোগ জমা ফর্ম'

};

var home_page_en = {

      about_headline1 : 'Masum Goni Taposh',
      about_headline2 : 'Councilor, Ward No 21, DNCC',
        header_notice_title:'Notice Board',
       notice1:'1. Application form for Citizen Certificate',
       notice2:'2. Application form for Heir Certificate',
       notice3:'3. Application form for Others Certificate',
       notice4:'4. Complain Submit Form'

};
