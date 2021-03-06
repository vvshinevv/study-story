package com.study.story.concurrent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Getter
@Setter
@Entity(name = "account")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private String name;

    @ManyToOne
    private Holder holder;

    private Long balance;

    @Version
    private Long version;

    public Account(String name) {
        this.name = name;
        this.balance = 0L;
    }
}
