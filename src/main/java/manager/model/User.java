package manager.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"USERS\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    private String email;
    private String password;
}
