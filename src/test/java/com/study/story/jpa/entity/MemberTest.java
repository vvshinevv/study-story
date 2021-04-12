package com.study.story.jpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void simpleTest() {
        Member member = new Member();
        member.setId(1L);
        member.setUsername("honghee");

        em.persist(member);
        em.flush(); // 영속성 캐시는 날라가지 않음!!


        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember -> " + findMember);
    }

}
