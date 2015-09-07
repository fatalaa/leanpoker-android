package org.leanpoker.data;

/**
 * Created by tbalogh on 06/09/15.
 */
public class Date {
	private final String mDay;
	private final String mMonth;
	private final String mYear;

	public Date(final String day, final String month, final String year) {
		mDay = day;
		mMonth = month;
		mYear = year;
	}

	public String getDay() {
		return mDay;
	}

	public String getMonth() {
		return mMonth;
	}

	public String getYear() {
		return mYear;
	}
}
