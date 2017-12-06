package manager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"USERS\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
}
