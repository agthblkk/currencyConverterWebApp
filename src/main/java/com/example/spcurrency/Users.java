package com.example.spcurrency;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> history = new ArrayList<>();

    private String name;
    private String surname;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Users(String name, String surname, String username, String password, UserRole role) {
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.username = username;
        this.role = role;
    }
    public void addHistory(History history1) {
        if ( ! history.contains(history1)) {
            history.add(history1);
            history1.setUser(this);
        }
    }

}
