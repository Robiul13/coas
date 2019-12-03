package com.asl.asl_rms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
	public static int[] IranDayTab = new int[]{0, 0, 31, 62, 93, 124, 155, 186,
			216, 246, 276, 306, 336};
	public static int[] IranYearDay = new int[]{0, 0, 366, 731, 1096, 1461,
			1827, 2192, 2557, 2922, 3288, 3653, 4018, 4383, 4749, 5114, 5479,
			5844, 6210, 6575, 6940, 7305, 7671, 8036, 8401, 8766, 9132, 9497,
			9862, 10227, 10592, 10958, 11323, 11688, 12053, 12419, 12784,
			13149, 13514, 13880, 14245, 14610, 14975, 15341, 15706, 16071,
			16436, 16802, 17167, 17532, 17897, 18263, 18628, 18993, 19358,
			19724, 20089, 20454, 20819, 21185, 21550, 21915, 22280, 22645,
			23011, 23376, 23741, 24106, 24472, 24837, 25202, 25567, 25933,
			26298, 26663, 27028, 27394, 27759, 28124, 28489, 28855, 29220,
			29585, 29950, 30316, 30681, 31046, 31411, 31777, 32142, 32507, '聨',
			'臖', '荃', '蒰', '蘝', '螊', '裸', '詥', '诒', '贿'};
	public static int[] DayTab = new int[]{0, 0, 31, 59, 90, 120, 151, 181,
			212, 243, 273, 304, 334};
	public static int[] DayTabR = new int[]{0, 0, 31, 60, 91, 121, 152, 182,
			213, 244, 274, 305, 335};

	public static int TOU_GetGmDay_IRAN(String IAddr) {
		int Mon = Integer.parseInt(IAddr.substring(2, 4));
		int Year = Integer.parseInt(IAddr.substring(0, 2));
		if (Year < 79) {
			Year += 100;
		}

		int Day = Integer.parseInt(IAddr.substring(4, 6)) - 1 + IranDayTab[Mon]
				+ IranYearDay[Year - 79 + 1];
		return Day;
	}

	public static int TOU_GetGmDay(String GAddr) {
		int Mon = Integer.parseInt(GAddr.substring(2, 4));
		int Year = Integer.parseInt(GAddr.substring(0, 2));
		int Day = Integer.parseInt(GAddr.substring(4, 6)) - 1 + DayTab[Mon]
				+ Year * 365 + (Year >> 2) + 1;
		if ((Year & 3) == 0 && Mon <= 2) {
			--Day;
		}

		return Day;
	}

	public static String TOU_Days_Date_IRAN(int days) {
		String IAddr = "";

		int i;
		for (i = 100; i > 0 && days < IranYearDay[i]; --i) {
			;
		}

		int tmp = 78 + i;
		if (tmp >= 100) {
			tmp -= 100;
		}

		String stemp = "" + tmp;
		stemp = "00".substring(0, 2 - stemp.length()) + stemp;
		IAddr = stemp;
		days -= IranYearDay[i];

		for (i = 12; i > 0 && days < IranDayTab[i]; --i) {
			;
		}

		stemp = "00".substring(0, 2 - ("" + i).length()) + i;
		IAddr = IAddr + stemp;
		days -= IranDayTab[i];
		stemp = "" + (days + 1);
		stemp = "00".substring(0, 2 - stemp.length()) + stemp;
		IAddr = IAddr + stemp;
		return IAddr;
	}

	public static String TOU_Days_Date_G(int days) {
		String GAddr = "";
		String stemp = "";
		int Y1 = days / 1461;
		int Y2 = days % 1461;
		if (Y2 >= 366) {
			stemp = "" + (Y1 * 4 + (Y2 - 1) / 365);
			Y2 = (Y2 - 1) % 365;
		} else {
			stemp = "" + Y1 * 4;
		}

		GAddr = stemp;
		if ((Integer.parseInt(stemp) & 3) != 0) {
			for (Y1 = 12; Y1 > 0 && Y2 < DayTab[Y1]; --Y1) {
				;
			}

			stemp = "" + Y1;
			stemp = "00".substring(0, 2 - stemp.length()) + stemp;
			GAddr = GAddr + stemp;
			stemp = "" + (Y2 + 1 - DayTab[Y1]);
			stemp = "00".substring(0, 2 - stemp.length()) + stemp;
			GAddr = GAddr + stemp;
		} else {
			for (Y1 = 12; Y1 > 0 && Y2 < DayTabR[Y1]; --Y1) {
				;
			}

			stemp = "" + Y1;
			stemp = "00".substring(0, 2 - stemp.length()) + stemp;
			GAddr = GAddr + stemp;
			stemp = "" + (Y2 + 1 - DayTabR[Y1]);
			stemp = "00".substring(0, 2 - stemp.length()) + stemp;
			GAddr = GAddr + stemp;
		}

		return GAddr;
	}

	public static String TOU_GregorianToIRAN(String GAddr) {
		String IAddr = "";
		int Days = TOU_GetGmDay(GAddr.substring(0, 6));
		if (Days >= 79 && Days < '躭') {
			Days -= 79;
			IAddr = TOU_Days_Date_IRAN(Days);
			if (Integer.parseInt(GAddr.substring(6, 8)) == 0) {
				IAddr = IAddr + "07";
			} else {
				IAddr = IAddr + GAddr.substring(6, 8);
			}

			IAddr = IAddr + GAddr.substring(8, 14);
		} else {
			IAddr = "00000001" + GAddr.substring(8, 14);
		}

		return IAddr;
	}

	public static String TOU_IRANToGregorian(String IAddr) {
		String GAddr = "";
		int Days = TOU_GetGmDay_IRAN(IAddr.substring(0, 6)) + 79;
		if (Days < '躭') {
			GAddr = TOU_Days_Date_G(Days);
			if (Integer.parseInt(IAddr.substring(6, 8)) == 0) {
				GAddr = GAddr + "07";
			} else {
				GAddr = GAddr + IAddr.substring(6, 8);
			}

			GAddr = GAddr + IAddr.substring(8, 14);
		} else {
			GAddr = "00000001" + IAddr.substring(8, 14);
		}

		if (GAddr.length() == 13) {
			GAddr = "0" + GAddr;
		}

		return GAddr;
	}

	@Deprecated
	public static String iranToGregorian(String iranDateTime, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;

		try {
			date = sdf.parse(iranDateTime);
			Date e = iranToGregorian(date);
			return sdf.format(e);
		} catch (ParseException arg4) {
			arg4.printStackTrace();
			return "";
		}
	}

	@Deprecated
	public static String gregorianToIran(String gregorianTime, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;

		try {
			date = sdf.parse(gregorianTime);
			Date e = gregorianToIran(date);
			return sdf.format(e);
		} catch (ParseException arg4) {
			arg4.printStackTrace();
			return "";
		}
	}

	@Deprecated
	public static Date iranToGregorian(Date iranDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String iranTime = sdf.format(iranDate);
		iranTime = iranTime.substring(2, 4) + iranTime.substring(5, 7)
				+ iranTime.substring(8, 10) + "01" + iranTime.substring(11, 13)
				+ iranTime.substring(14, 16) + iranTime.substring(17, 19);
		iranTime = TOU_IRANToGregorian(iranTime);
		String greTime = "20" + iranTime.substring(0, 2) + "-"
				+ iranTime.substring(2, 4) + "-" + iranTime.substring(4, 6)
				+ " " + iranTime.substring(8, 10) + ":"
				+ iranTime.substring(10, 12) + ":" + iranTime.substring(12, 14);
		Date greDate = null;

		try {
			greDate = sdf.parse(greTime);
		} catch (ParseException arg5) {
			arg5.printStackTrace();
		}

		return greDate;
	}

	@Deprecated
	public static Date gregorianToIran(Date gregorianDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(gregorianDate);
		time = time.substring(2, 4) + time.substring(5, 7)
				+ time.substring(8, 10) + "01" + time.substring(11, 13)
				+ time.substring(14, 16) + time.substring(17, 19);
		time = TOU_GregorianToIRAN(time);
		if (Integer.parseInt(time.substring(0, 2)) >= 89) {
			time = "13" + time;
		} else {
			time = "14" + time;
		}

		time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
				+ time.substring(6, 8) + " " + time.substring(10, 12) + ":"
				+ time.substring(12, 14) + ":" + time.substring(14, 16);
		Date iranDate = null;

		try {
			iranDate = sdf.parse(time);
		} catch (ParseException arg4) {
			arg4.printStackTrace();
		}

		return iranDate;
	}

	public static String iranToGregorian(String iranTime) {
		boolean withoutTime = false;
		if (iranTime.length() == 10) {
			withoutTime = true;
			iranTime = iranTime + " 00:00:00";
		}

		if (iranTime.substring(0, 10).equals("0000-00-00")) {
			return "0000-00-00 00:00:00";
		} else {
			iranTime = iranTime.substring(2, 4) + iranTime.substring(5, 7)
					+ iranTime.substring(8, 10) + "01"
					+ iranTime.substring(11, 13) + iranTime.substring(14, 16)
					+ iranTime.substring(17, 19);
			iranTime = TOU_IRANToGregorian(iranTime);
			String time = "20" + iranTime.substring(0, 2) + "-"
					+ iranTime.substring(2, 4) + "-" + iranTime.substring(4, 6)
					+ " " + iranTime.substring(8, 10) + ":"
					+ iranTime.substring(10, 12) + ":"
					+ iranTime.substring(12, 14);
			if (withoutTime) {
				time = time.substring(0, 10);
			}

			return time;
		}
	}

	public static String gregorianToIran(String time) {
		boolean withoutTime = false;
		if (time.length() == 10) {
			withoutTime = true;
			time = time + " 00:00:00";
		}

		time = time.substring(2, 4) + time.substring(5, 7)
				+ time.substring(8, 10) + "01" + time.substring(11, 13)
				+ time.substring(14, 16) + time.substring(17, 19);
		time = TOU_GregorianToIRAN(time);
		if (Integer.parseInt(time.substring(0, 2)) >= 89) {
			time = "13" + time;
		} else {
			time = "14" + time;
		}

		time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
				+ time.substring(6, 8) + " " + time.substring(10, 12) + ":"
				+ time.substring(12, 14) + ":" + time.substring(14, 16);
		if (withoutTime) {
			time = time.substring(0, 10);
		}

		return time;
	}

	public static void main(String[] args) {
		String str2 = iranToGregorian("1387-02-31 00:00:01");
		System.out.println(str2);
		String str3 = gregorianToIran("2012-05-20");
		System.out.println(str3);
	}
}