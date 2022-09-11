import java.time.LocalDateTime;
import java.util.Date;

public class Account {
    private LocalDateTime drop;
    private final String login;
    private final String pass;
    private final String url;
    private final String steamIDApp;
    private final String CSIDApp;

    public Account(LocalDateTime drop, String login, String pass, String url) {
        this.drop = drop;
        this.login = login;
        this.pass = pass;
        this.url = url;
        this.steamIDApp = null;
        this.CSIDApp = null;
    }

    public LocalDateTime getDrop() {
        return drop;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return getDrop() + " " + getLogin() + " " + getPass() + " " + getUrl();
    }
}
