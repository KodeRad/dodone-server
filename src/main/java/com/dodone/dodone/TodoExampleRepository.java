package com.dodone.dodone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoExampleRepository extends JpaRepository<TodoEntity, Long> {
}
