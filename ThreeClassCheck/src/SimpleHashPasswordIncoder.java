public class SimpleHashPasswordIncoder {

    public String encode(String password) {
        if (password == null) {
            return null;
        }

        String result = "";

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int code = (int)c + 1;
            result = result + (char)code;
        }

        result = result + "XYZ";

        return result;
    }

    public boolean matches(String password, String encoded) {
        String newEncoded = encode(password);

        if (newEncoded == null || encoded == null) {
            return false;
        }

        return newEncoded.equals(encoded);
    }
}