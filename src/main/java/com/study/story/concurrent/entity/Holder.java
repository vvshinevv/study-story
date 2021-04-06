package com.study.story.concurrent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "holder")
@NoArgsConstructor
public class Holder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long holderId;

    private String name;

    @OneToMany(mappedBy = "holder")
    private List<Account> accounts;
}
