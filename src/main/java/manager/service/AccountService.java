package manager.service;

import manager.exception.AccountWithSameLoginAlreadyExist;
import manager.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AccountService extends UserDetailsService {

    void register(User user) throws AccountWithSameLoginAlreadyExist;
}