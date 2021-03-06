package lac.puc.ubi.invbat.concept.misc;

import java.util.Date;

@SuppressWarnings("deprecation")
public class DateHelper {

	public static boolean isItToday(Date date, Date date2) {
		
		if(date.getYear() == date2.getYear() && date.getMonth() == date2.getMonth() && date.getDate() == date2.getDate())
			return true;
		
		return false;
	}

	public static boolean checkTimeFrame(int timeFrameID) {
		boolean ret = false;
		Date current = new Date();

		Date frame00_1 = new Date();
		frame00_1.setHours(0);
		frame00_1.setMinutes(0);
		frame00_1.setSeconds(0);
		
		Date frame00_2 = new Date();
		frame00_2.setHours(7);
		frame00_2.setMinutes(59);
		frame00_2.setSeconds(59);
		
		Date frame01_1 = new Date();
		frame01_1.setHours(8);
		frame01_1.setMinutes(0);
		frame01_1.setSeconds(0);
		
		Date frame01_2 = new Date();
		frame01_2.setHours(15);
		frame01_2.setMinutes(59);
		frame01_2.setSeconds(59);
		
		Date frame02_1 = new Date();
		frame02_1.setHours(16);
		frame02_1.setMinutes(0);
		frame02_1.setSeconds(0);
		
		Date frame02_2 = new Date();
		frame02_2.setHours(23);
		frame02_2.setMinutes(59);
		frame02_2.setSeconds(59);
		
		switch(timeFrameID)
		{
			case 0: //until 07:59h
				if(current.before(frame00_2))
					ret = true;
				break;
			case 1: //until 15:59h
				if(current.before(frame01_2))
					ret = true;
				break;
			case 2: //until 23:59h
				if(current.before(frame02_2))
					ret = true;
				break;
				
			default:
				ret = false;
		}
		
		return ret;
	}

	public static String getTimeLimitFromTimeframeID(int timeFrameID)
	{
		String ret = "00h:00m";

		switch(timeFrameID)
		{
			case 0: //from 00h to 07:59h
				ret = "07h:59m";
				break;
			case 1: //from 08h to 15:59h
				ret = "15h:59m";
				break;
			case 2: //from 16h to 23:59h
				ret = "23h:59m";
				break;
				
			default:
				
		}
		
		return ret;
	}
}
