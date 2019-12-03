	$(document).ready(
					function() {
						var wi = $(window).width();
						if (wi < 980) {

							$('#jmenu .show-menu').click(
									function() {
										// $('.mzr-responsive').show();
										$(".mzr-responsive").slideToggle(400,
												"linear", function() {

												});
									});

							$("#jmenu a.submenu").click(
									function() {

										$('#jmenu a.submenu').siblings()
												.addClass('sibling-toggle');
										$(this).parent().find(".mzr-content")
												.removeClass('sibling-toggle')
												.addClass('slide-visible')
												.slideToggle(400, "linear",
														function() {
														});
										return false;
									});
						}

					});	

$(document).ready(function() {
		
		var wi = $(window).width();
		if (wi < 769) {
			$('#printable_area td').removeAttr('style');
			$('#printable_area td p').css("width", "100%");
		}
		
		
		var lang=getCookie('lang');
		if(lang=="" || lang==null || lang=='undefined'){
			setLanguageCookie('bn');
		}
		lang=getCookie('lang');
		
		if(lang=='bn'){
			$('#langBtn').html('English');
		}
		
		if(lang=='en'){
			$('#langBtn').html('বাংলা');
		}
		
		setMenues();
	});

var menues_bn={
		menu_about:'আমাদের সম্পর্কে',
		sm_about:{
			mm_about_sm_amgt:'মাসুম গনি তাপস সম্পর্কে',
			mm_about_sm_ps:'প্রোগ্রাম সময়সূচী',
			mm_about_sm_notice:'নোটিশ / ঘোষণা'
				},
		menu_info:'তথ্য',
		sm_info:{
			mm_info_sm_geo:'ভৌগলিক মানচিত্র',
			mm_info_sm_road:'রাস্তার দৈর্ঘ্য',
			mm_info_sm_garbage:'গার্বেজ সিস্টেম এবং ডাম্পিং স্টেশন',
			mm_info_sm_lamp:'সড়ক বাতি',
			mm_info_sm_sewerage:'ভূগর্ভস্থ নরদমা ব্যবস্থা',
			mm_info_sm_utility:'ইউটিলিটি (পানি, গ্যাস, বিদ্যুৎ)',
			mm_info_sm_holding:'হোল্ডিংস তথ্য',
			mm_info_sm_voters:'ভোটার এলাকা',
			mm_info_sm_security:'নিরাপত্তা ব্যবস্থা'
				},
		menu_services:'সেবা',
		sm_services:{
			mm_services_sm_citizen:'নাগরিক  সনদ ',
			mm_services_sm_heir:'ওয়ারিস   সনদ ',
			mm_services_sm_character:'চারিত্রিক  সনদ ',
			mm_services_sm_birth:'জন্ম  সনদ ',
			mm_services_sm_death:'মৃত্যু  সনদ ',
			mm_services_sm_others:'অন্যান্য সনদ',
			mm_services_sm_security:'স্থানীয় নিরাপত্তা রক্ষী',
			mm_services_sm_cctv:'নজরদারি এবং নিরাপত্তা ব্যবস্থা (সিসিটিভি ক্যামেরা)',
			mm_services_sm_internet:'বিনামূল্যের ওয়াইফাই  সুবিধা',
			mm_services_sm_adv:'ডিজিটাল স্ক্রিন দ্বারা বিজ্ঞাপন ',
			mm_services_sm_birthInput:'নবজাত শিশুর তথ্য প্রদান'
				},

		/* menu_social:'সমাজ কল্যাণ ও কার্যক্রম', */
		menu_social:'সামাজিক  কার্যক্রম',
		sm_social:{
			mm_social_sm_social:'সামাজিক কর্মকান্ড'
		},				
		menu_complain:'অভিযোগ ',
		menu_suggestion:'পরামর্শ',
		menu_workPlan:'কর্ম পরিকল্পনা',
		sm_workPlan:{
			mm_workPlan_sm_ongoing:'চলমান কাজ',
			mm_workPlan_sm_done:'কাজ সম্পন্ন',
			mm_workPlan_sm_inhand:'ভবিষ্যতের কাজ'
		},
		menu_contact:'যোগাযোগ'
};
  
var menues_en={
		menu_about:'About',
		sm_about:{
			mm_about_sm_amgt:'About MG Taposh',
			mm_about_sm_ps:'Program Schedule',
			mm_about_sm_notice:'Notice / Announcement'
				},
		menu_info:'Info',
		sm_info:{
			mm_info_sm_geo:'Geographical Map',
			mm_info_sm_road:'Area and Lengths of Roads',
			mm_info_sm_garbage:'Garbage System and Dumping Stations',
			mm_info_sm_lamp:'Road Lamp',
			mm_info_sm_sewerage:'Sewerage System',
			mm_info_sm_utility:'Utility (Water, Gas, Electricity)',
			mm_info_sm_holding:'Holdings and Peoples',
			mm_info_sm_voters:'Voters Area',
			mm_info_sm_security:'Security System'
				},
		menu_services:'Services',
		sm_services:{
			mm_services_sm_citizen:'Citizen Certificate',
			mm_services_sm_heir:'Heir Certificate',
			mm_services_sm_character:'Character Certificate ',
			mm_services_sm_birth:'Birth Certificate',
			mm_services_sm_death:'Death Certificate',
			mm_services_sm_others:'Others Certificate',
			mm_services_sm_security:'Local security guard',
			mm_services_sm_cctv:'Surveillance and security system (CCTV camera)',
			mm_services_sm_internet:'Free Internet Wifi Zone',
			mm_services_sm_adv:'Digital Screen for advertise',
			mm_services_sm_birthInput:'Neonate Data Input'
				},				 
		menu_social:'Social Activities',
		sm_social:{
			mm_social_sm_social:'Social activities'
		},
		menu_complain:'Complain',
		menu_suggestion:'Suggestions',
		menu_workPlan:'Work Plan',
		sm_workPlan:{
			mm_workPlan_sm_ongoing:'Work on going',
			mm_workPlan_sm_done:'Work Done',
			mm_workPlan_sm_inhand:'Work in Hand'
		},
		menu_contact:'Contact'
};



function setMenues() {
	var lang=getCookie('lang');
	var menues;
	if(lang=='en'){
		menues=menues_en;
	}else{
		menues=menues_bn;
	}
	$('#mm_about').html(menues.menu_about);		
	$('#mm_about_sm_amgt').html(menues.sm_about.mm_about_sm_amgt);
	$('#mm_about_sm_ps').html(menues.sm_about.mm_about_sm_ps);
	$('#mm_about_sm_notice').html(menues.sm_about.mm_about_sm_notice);
	
	$('#mm_info').html(menues.menu_info);
	$('#mm_info_sm_geo').html(menues.sm_info.mm_info_sm_geo);
	$('#mm_info_sm_road').html(menues.sm_info.mm_info_sm_road);
	$('#mm_info_sm_garbage').html(menues.sm_info.mm_info_sm_garbage);
	$('#mm_info_sm_lamp').html(menues.sm_info.mm_info_sm_lamp);
	$('#mm_info_sm_sewerage').html(menues.sm_info.mm_info_sm_sewerage);
	$('#mm_info_sm_utility').html(menues.sm_info.mm_info_sm_utility);
	$('#mm_info_sm_holding').html(menues.sm_info.mm_info_sm_holding);
	$('#mm_info_sm_voters').html(menues.sm_info.mm_info_sm_voters);
	$('#mm_info_sm_security').html(menues.sm_info.mm_info_sm_security);


	$('#mm_services').html(menues.menu_services);
	$('#mm_services_sm_citizen').html(menues.sm_services.mm_services_sm_citizen);
	$('#mm_services_sm_heir').html(menues.sm_services.mm_services_sm_heir);
	$('#mm_services_sm_character').html(menues.sm_services.mm_services_sm_character);
	$('#mm_services_sm_birth').html(menues.sm_services.mm_services_sm_birth);
	$('#mm_services_sm_death').html(menues.sm_services.mm_services_sm_death);
	$('#mm_services_sm_others').html(menues.sm_services.mm_services_sm_others);	
	$('#mm_services_sm_security').html(menues.sm_services.mm_services_sm_security);
	$('#mm_services_sm_cctv').html(menues.sm_services.mm_services_sm_cctv);
	$('#mm_services_sm_internet').html(menues.sm_services.mm_services_sm_internet);
	$('#mm_services_sm_adv').html(menues.sm_services.mm_services_sm_adv);
	$('#mm_services_sm_birthInput').html(menues.sm_services.mm_services_sm_birthInput);
	
	
	$('#mm_social').html(menues.menu_social);
	// $('#mm_social_sm_social').html(menues.sm_social.mm_social_sm_social);
	
	$('#mm_complain').html(menues.menu_complain);	
	$('#mm_suggestion').html(menues.menu_suggestion);
	$('#mm_workPlan').html(menues.menu_workPlan);
	$('#mm_workPlan_sm_ongoing').html(menues.sm_workPlan.mm_workPlan_sm_ongoing);
	$('#mm_workPlan_sm_done').html(menues.sm_workPlan.mm_workPlan_sm_done);
	$('#mm_workPlan_sm_inhand').html(menues.sm_workPlan.mm_workPlan_sm_inhand);
	
		
	$('#mm_contact').html(menues.menu_contact);
}
	
	function setLanguageCookie(cookieValue) {
		var today = new Date();
		var expire = new Date();
		var cookieName = 'lang';
		var nDays = 5;
		var ctx = $("meta[name='ctx']").attr("content");		 
		expire.setTime(today.getTime() + 3600000 * 24 * nDays);
		document.cookie = cookieName + "=" + escape(cookieValue)
				+ ";expires=" + expire.toGMTString()+";path="+ctx+"dncc21/";		
		
	}

	function setLanguage() {
		
		var lang=getCookie('lang');
		if(lang=="" || lang==null || lang=='undefined'){
			setLanguageCookie('bn');
			lang=getCookie('lang');
		}
		
		if(lang=='bn'){
			setLanguageCookie('en');
			$('#langBtn').html('বাংলা');
		}
		
		if(lang=='en'){
			setLanguageCookie('bn');	
			$('#langBtn').html('English');
		}
		
		setMenues();		
		
		// Call function:
		window[pageName]();
		return false;
	}
	
	function load_aboutMGT(){
		// var ctx = $("meta[name='ctx']").attr("content");
		var ctx=document.getElementsByTagName('meta')['ctx'].getAttribute("content");
		var lang=getCookie('lang');
		lang=lang==''?'bn':lang;
		var param={
			    lang: lang
			  }		
		var paramStr = JSON.stringify(param);	
		
		 
		  $.ajax({ 
			  url: ctx + 'api/v1/about/aboutMGT',
			  data: paramStr,
			  contentType: "application/json", 
			  success: function (data) { 
				  var  result = JSON.parse(data); 
				  $('#aboutMgt_p1').html(result.aboutMGT);
				  },
		     error: function (data) {
		    	 alert("Server Error"); 
		    	 }, 
		     type: 'POST'		   
		  });
	}
	
function loadNotice(){		
		var ctx=document.getElementsByTagName('meta')['ctx'].getAttribute("content");		
		var param={limit:3}		
		  $.ajax({ 
			  url: ctx + 'api/v1/about/noticeList',
			  data: param,
			  contentType: "application/json", 
			  success: function (data) { 
				  $.each(data, function(index, value){
					  var n=index+1;					  
					  var lang=getCookie('lang');						
						if(lang=='bn'){
							 $('#notice'+n).html(n+". "+value.titleBn);
							  $('#notice'+n).attr("href", ctx+value.url.substring(1));
						}else{
							 $('#notice'+n).html(n+". "+value.title);
							  $('#notice'+n).attr("href", ctx+value.url.substring(1));
						}
			        });				
				  },
		     error: function (data) {
		    	 alert("Server Error"); 
		    	 }, 
		     type: 'GET'		   
		  });
	}

	function loadNoticePost(){
		
		var ctx=document.getElementsByTagName('meta')['ctx'].getAttribute("content");		
		var param={id:9}		
		var paramStr = JSON.stringify(param);
		  $.ajax({ 
			  url: ctx + 'api/v1/about/noticeList',
			  data: paramStr,
			  contentType: "application/json", 
			  success: function (data) { 
				  var  result = JSON.parse(data);
				  $.each(result.noticeList, function(index, value){
					  var n=index+1;
					  $('#notice'+n).html(n+". "+value.title);
					  $('#notice'+n).attr("href", ctx+value.url.substring(1));
					  if(n == 3) {
					        return false; 
					    }
			        });
				
				  },
		     error: function (data) {
		    	 alert("Server Error"); 
		    	 }, 
		     type: 'POST'		   
		  });
	}
	
	function getCookie(cname) {
		  var name = cname + "=";
		  var decodedCookie = decodeURIComponent(document.cookie);
		  var ca = decodedCookie.split(';');
		  for(var i = 0; i <ca.length; i++) {
		    var c = ca[i];
		    while (c.charAt(0) == ' ') {
		      c = c.substring(1);
		    }
		    if (c.indexOf(name) == 0) {
		      return c.substring(name.length, c.length);
		    }
		  }
		  return "";
		}

	$(function() {

		// Slideshow 4
		$("#front-image-slider").responsiveSlides({
			auto : true,
			pager : false,
			nav : true,
			speed : 2000,
			maxwidth : 960,
			namespace : "callbacks"
		});
		$("#right-content a").click(function() {
			var url = $(this).attr('href');
			if (isExternal(url) && url != 'javascript:;') {
				openInNewTab(url);
				return false;
			}
		});
	});
	function openInNewTab(url) {
		var win = window.open(url, '_blank');
		win.focus();
	}
	function isExternal(url) {
		var match = url
				.match(/^([^:\/?#]+:)?(?:\/\/([^\/?#]*))?([^?#]+)?(\?[^#]*)?(#.*)?/);
		if (typeof match[1] === "string" && match[1].length > 0
				&& match[1].toLowerCase() !== location.protocol)
			return true;
		if (typeof match[2] === "string" && match[2].length > 0
				&& match[2].replace(new RegExp(":(" + {
					"http:" : 80,
					"https:" : 443
				}[location.protocol] + ")?$"), "") !== location.host)
			return true;
		return false;
	}












<!-- ============ start accessibility megamenu ============ -->


	$(document).ready(function($) {

		$("#dawgdrops").accessibleMegaMenu({
			/*
			 * prefix for generated unique id attributes, which are required to
			 * indicate aria-owns, aria-controls and aria-labelledby
			 */
			uuidPrefix : "accessible-megamenu",

			/* css class used to define the megamenu styling */
			menuClass : "meganizr",

			/* css class for a top-level navigation item in the megamenu */
			topNavItemClass : "mzr-drop",

			/* css class for a megamenu panel */
			panelClass : "mzr-content",

			/* css class for a group of items within a megamenu panel */
			panelGroupClass : "mzr-links",

			/* css class for the hover state */
			hoverClass : "hover",

			/* css class for the focus state */
			focusClass : "focus-content",

			/* css class for the open state */
			openClass : "open hover-content"
		});

	});

