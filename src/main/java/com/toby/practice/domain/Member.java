package com.toby.practice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Builder
    public Member(String name, String email, String password){
        this.name = name;
        this.email= email;
        this.password = password;
    }

    public void update(String pw, String email){
        if(pw != null)  this.password = pw;
        if(email != null)   this.email = email;
    }
}
