package manager.repository;

import manager.model.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Users, Long> {

    Users findByUsername(String username);
}