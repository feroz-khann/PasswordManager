package PasswordManagerClasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^A-Za-z0-9]");
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static boolean isValidGmail(String email) {
        if (email == null) {
            return false;
        }
        // Create a matcher for the input email
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        // Check if the email matches the pattern
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false; // Password must be at least 8 characters long
        }
        // Check for at least one uppercase letter
        Matcher hasUppercase = UPPERCASE_PATTERN.matcher(password);
        // Check for at least one lowercase letter
        Matcher hasLowercase = LOWERCASE_PATTERN.matcher(password);
        // Check for at least one digit
        Matcher hasDigit = DIGIT_PATTERN.matcher(password);
        // Check for at least one special character
        Matcher hasSpecial = SPECIAL_CHAR_PATTERN.matcher(password);

        return hasUppercase.find() && hasLowercase.find() && hasDigit.find() && hasSpecial.find();
    }
    public static boolean isValidPhoneNo(String phone){
        Matcher hasDigit = DIGIT_PATTERN.matcher(phone);
        return hasDigit.find();
    }
}
