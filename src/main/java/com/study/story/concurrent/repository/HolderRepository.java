package com.study.story.concurrent.repository;

import com.study.story.concurrent.entity.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderRepository extends JpaRepository<Holder, Long> {
}
