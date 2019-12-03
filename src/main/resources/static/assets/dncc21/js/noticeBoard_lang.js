var pageName = "noticeBoard";

function noticeBoard() {

	var lang = getCookie('lang');
	var labels;

	if (lang == 'en') {
		labels = labels_en;
	} else {
		labels = labels_bn;
	}

	document.getElementById("main_header").innerHTML = labels.main_header;
	document.getElementById("nb_sl").innerHTML = labels.nb_sl;
	document.getElementById("nb_title").innerHTML = labels.nb_title;
	document.getElementById("nb_publish").innerHTML = labels.nb_publish;
	document.getElementById("nb_download").innerHTML = labels.nb_download;
}

var labels_en = {
	main_header : 'Notice Board',
	nb_sl : 'SL',
	nb_title : 'Notice Title',
	nb_publish : 'Publish Date',
	nb_download : 'Download'
};


var labels_bn = {
		main_header : 'নোটিস বোর্ড',
		nb_sl : 'ক্রমিক',
		nb_title : 'শিরোনাম',
		nb_publish : 'প্রকাশের তারিখ',
		nb_download : 'ডাউনলোড'
			};

