public class Main {
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        RegeexpAuthDataValid validator = new RegeexpAuthDataValid();
        SimpleHashPasswordIncoder encoder = new SimpleHashPasswordIncoder();

        String email = "danillkolbasenko@mail.com";
        String password = "Kolbasenko123";

        System.out.println("Email правильный: " + validator.validateEmail(email));
        System.out.println("Пароль неправильный: " + validator.validatePassword(password));

        String encoded = encoder.encode(password);
        System.out.println("Засшифрованный пароль: " + encoded);

        String result = auth.register(email, password);
        System.out.println(result);

        String login = auth.login(email, password);
        System.out.println(login);

        String wrongLogin = auth.login(email, "wrong");
        System.out.println(wrongLogin);
    }
}