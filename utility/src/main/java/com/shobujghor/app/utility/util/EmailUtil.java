package com.shobujghor.app.utility.util;

public class EmailUtil {
    public static final String EMAIL_VERIFICATION_SUBJECT = "Email verification";
    public static final String EMAIL_VERIFICATION_BODY = "Verify your e-mail address using followin link: %s";

    public static String getEmailVerificationBody(String emailVerificationLink) {
        return String.format(EMAIL_VERIFICATION_BODY, emailVerificationLink);
    }

}
