package manager.service.impl;

import manager.exception.AccountWithSameLoginAlreadyExist;
import manager.model.SpringUser;
import manager.model.User;
import manager.repository.AccountRepository;
import manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void register(User user) throws AccountWithSameLoginAlreadyExist {
        if (accountRepository.findByEmail(user.getEmail()) != null)
            throw new AccountWithSameLoginAlreadyExist(user.getEmail());
        String passHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passHash);
        accountRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = accountRepository.findByEmail(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SpringUser(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }
}
