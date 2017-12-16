package manager.service.impl;

import manager.exception.AccountWithSameLoginAlreadyExist;
import manager.model.Users;
import manager.repository.AccountRepository;
import manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void register(Users user) throws AccountWithSameLoginAlreadyExist {
        if (accountRepository.findByUsername(user.getUsername()) != null)
            throw new AccountWithSameLoginAlreadyExist(user.getUsername());
        String passHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passHash);
        accountRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users applicationUser = accountRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
