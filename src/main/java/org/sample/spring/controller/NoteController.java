package org.sample.spring.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.sample.spring.model.Note;
import org.sample.spring.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value="NoteController", description="Operations pertaining to notes database entries")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

	@RequestMapping(value = "/notes/dummy",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Dummy",
			notes = "Dummy", responseContainer = "Note")
	public String getDummyAPI() {
		return "I am running";
	}

	@RequestMapping(value = "/notes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API to BULK GET Notes data",
			notes = "API to BULK GET Notes data",
			response = Note.class, responseContainer = "Note")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @RequestMapping(value = "/notes/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API to GET Notes data",
			notes = "API to GET Notes data",
			response = Note.class, responseContainer = "Note")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(note);
    }

	@RequestMapping(value = "/notes",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API to post Notes data",
			notes = "API to post Notes data",
			response = Note.class, responseContainer = "Note")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

	@RequestMapping(value = "/notes/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API to PUT Notes data",
			notes = "API to PUT Notes data",
			response = Note.class, responseContainer = "Note")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
										   @Valid @RequestBody Note noteDetails) {
        Note note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }

	@RequestMapping(value = "/notes/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "API to DELETE Notes data",
			notes = "API to DELETE Notes data",
			response = Note.class, responseContainer = "Note")
    public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}
