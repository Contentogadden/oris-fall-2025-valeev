public class RegeexpAuthDataValid {

    public boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }

        if (email.length() < 5) {
            return false;
        }

        if (!email.contains("@")) {
            return false;
        }

        if (!email.contains(".")) {
            return false;
        }

        if (email.startsWith("@")) {
            return false;
        }

        if (email.endsWith(".")) {
            return false;
        }

        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                hasUppercase = true;
            }

            if (c >= '0' && c <= '9') {
                hasDigit = true;
            }
        }

        return hasUppercase && hasDigit;
    }
}