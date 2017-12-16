package manager.service;

import manager.exception.AccountWithSameLoginAlreadyExist;
import manager.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AccountService extends UserDetailsService {

    void register(Users user) throws AccountWithSameLoginAlreadyExist;
}