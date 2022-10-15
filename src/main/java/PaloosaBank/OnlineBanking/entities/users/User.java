package PaloosaBank.OnlineBanking.entities.users;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }
}