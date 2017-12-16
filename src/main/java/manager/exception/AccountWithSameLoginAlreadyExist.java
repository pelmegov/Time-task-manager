package manager.exception;

public class AccountWithSameLoginAlreadyExist extends BaseServiceException {

    private String login;

    public AccountWithSameLoginAlreadyExist(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
