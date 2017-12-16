package manager.controller;


import manager.exception.AccountWithSameLoginAlreadyExist;
import manager.model.Users;
import manager.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    private AccountService accountService;

    public SecurityController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody Users user) {
        try {
            accountService.register(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (AccountWithSameLoginAlreadyExist accountWithSameLoginAlreadyExist) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}