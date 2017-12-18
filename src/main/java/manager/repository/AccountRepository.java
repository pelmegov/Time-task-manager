package manager.repository;

import manager.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
}