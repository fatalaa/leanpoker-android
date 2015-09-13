package org.leanpoker.domain.mock;

import org.leanpoker.data.Address;
import org.leanpoker.data.Date;
import org.leanpoker.data.Event;
import org.leanpoker.data.Facilitator;
import org.leanpoker.data.ProgrammingLanguage;
import org.leanpoker.data.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbalogh on 06/09/15.
 */
public final class EventListProviderMock {
	private EventListProviderMock() {
		// Util class
	}

	public static List<Event> getMockEvents(final int eventCount) {
		final List<Event> events = new ArrayList<>();
		for (int i = 0; i < eventCount; i++) {
			final Event event = new Event(getMockId(i), getMockName(i), getMockDate(i), getMockFacilitator(i),
			                              getMockAddress(i), getMockTeams(i));
			events.add(event);
		}
		return events;
	}

	private static List<Team> getMockTeams(final int count) {
		final List<Team> teams = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			teams.add(getMockTeam(i));
		}
		return teams;
	}

	private static Team getMockTeam(final int i) {
		return new Team(getMockTeamName(i), getMockProgrammingLanguage(i), getMockMemberCount(i));
	}

	private static String getMockTeamName(final int i) {
		return "Team" + String.valueOf(i);
	}

	private static ProgrammingLanguage getMockProgrammingLanguage(final int i) {
		final int mod = i % 3;
		switch (mod) {
			case 0:
				return ProgrammingLanguage.C_PLUS_PLUS;
			case 1:
				return ProgrammingLanguage.C_SHARP;
			case 2:
				return ProgrammingLanguage.PYTHON;
			default:
				return ProgrammingLanguage.PYTHON;
		}
	}

	private static int getMockMemberCount(final int i) {
		return i;
	}

	private static long getMockId(final int i) {
		return i;
	}

	private static String getMockName(final int i) {
		return "Geeks & Greeks";
	}

	private static Date getMockDate(final int i) {
		return new Date(getMockDay(), getMockMonth(), getMockYear());
	}

	private static String getMockDay() {
		return "27";
	}

	private static String getMockMonth() {
		return "August";
	}

	private static String getMockYear() {
		return "2015";
	}

	private static Facilitator getMockFacilitator(final int i) {
		return new Facilitator("DevIll");
	}

	private static Address getMockAddress(final int i) {
		return new Address("Budapest");
	}
}
