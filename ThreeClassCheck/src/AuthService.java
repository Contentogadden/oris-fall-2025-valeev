import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, String> users = new HashMap<>();

    public String register(String email, String password) {
        if (!email.contains("@") || !email.contains(".")) {
            return "Email не правильный";
        }

        if (password.length() < 6) {
            return "Пароль слишком короткий";
        }

        String hashedPass = password + "123";
        users.put(email, hashedPass);

        return "Регистрация успешна!";
    }

    public String login(String email, String password) {
        String savedPass = users.get(email);

        if (savedPass == null) {
            return "Пользователь не найден";
        }

        String hashedPass = password + "123";

        if (savedPass.equals(hashedPass)) {
            return "Вход выполнен!";
        } else {
            return "Неправильный пароль";
        }
    }
}