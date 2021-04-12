package com.study.story.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "member")
public class Member {

    @Id
    private Long id;

    @Column(name = "username")
    private String username;
}
