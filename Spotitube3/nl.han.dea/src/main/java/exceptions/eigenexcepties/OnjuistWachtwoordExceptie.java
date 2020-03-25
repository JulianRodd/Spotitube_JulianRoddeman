package exceptions.eigenexcepties;

import javax.security.auth.login.FailedLoginException;

public class OnjuistWachtwoordExceptie extends FailedLoginException {
    public OnjuistWachtwoordExceptie() {
        super("Er is een onjuist wachtwoord ingevuld!");
    }
}
