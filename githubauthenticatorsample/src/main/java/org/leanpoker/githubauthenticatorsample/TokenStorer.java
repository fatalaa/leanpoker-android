package org.leanpoker.githubauthenticatorsample;

/**
 * Created by tmolnar on 14/09/15.
 */
public class TokenStorer {
    private static String accessToken;
    private static String accessCode;

    public static void setAccessToken(final String accessToken) {
        TokenStorer.accessToken = accessToken;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessCode(final String accessCode) {
        TokenStorer.accessCode = accessCode;
    }

    public static String getAccessCode() {
        return accessCode;
    }
}
