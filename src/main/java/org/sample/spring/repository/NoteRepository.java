package org.sample.spring.repository;

import org.sample.spring.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> {

	Iterable<Note> findByContentIgnoringCase(String content);
}
