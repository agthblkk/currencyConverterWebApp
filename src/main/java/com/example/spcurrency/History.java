package com.example.spcurrency;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first;
    private String second;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;
    private Date date;

    public History(String first, String second, Date date) {
        this.first = first;
        this.second = second;
        this.date = date;
    }
}
