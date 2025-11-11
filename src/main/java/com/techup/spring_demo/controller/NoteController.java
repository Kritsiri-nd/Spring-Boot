package com.techup.spring_demo.controller;

import com.techup.spring_demo.dto.NoteRequest;
import com.techup.spring_demo.dto.NoteResponse;
import com.techup.spring_demo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // ✅ GET /api/notes - ดึงโน้ตทั้งหมด
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes() {
        List<NoteResponse> notes = noteService.getAll();
        return ResponseEntity.ok(notes);
    }

    // ✅ POST /api/notes - สร้างโน้ตใหม่
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest req) {
        NoteResponse created = noteService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ✅ DELETE /api/notes/{id} - ลบโน้ตตาม ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ PUT /api/notes/{id} - อัปเดตโน้ต
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest req) {

        NoteResponse updated = noteService.update(id, req);
        return ResponseEntity.ok(updated);
    }
}
