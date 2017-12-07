package manager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"USERS\"")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
}
