package org.leanpoker.data;

/**
 * Created by tbalogh on 11/09/15.
 */
public class Team {
	private final String              mName;
	private final ProgrammingLanguage mProgrammingLanguage;
	private final int                 mMemberCount;

	public Team(final String name, final ProgrammingLanguage programmingLanguage,
	            final int memberCount) {
		mName = name;
		mProgrammingLanguage = programmingLanguage;
		mMemberCount = memberCount;
	}

	public String getName() {
		return mName;
	}

	public ProgrammingLanguage getProgrammingLanguage() {
		return mProgrammingLanguage;
	}

	public int getMemberCount() {
		return mMemberCount;
	}
}
