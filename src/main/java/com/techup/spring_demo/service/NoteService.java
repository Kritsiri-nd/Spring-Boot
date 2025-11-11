package com.techup.spring_demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.techup.spring_demo.dto.NoteRequest;
import com.techup.spring_demo.dto.NoteResponse;
import com.techup.spring_demo.entity.Note;
import com.techup.spring_demo.repository.NoteRepository;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    // ดึงโน้ตทั้งหมดและแปลงเป็น DTO
    public List<NoteResponse> getAll() {
        return noteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // สร้างโน้ตใหม่
    public NoteResponse create(NoteRequest req) {
        // 1. แปลงจาก DTO -> Entity
        Note note = new Note();
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        note.setImageUrl(req.getImageUrl());

        // 2. บันทึกลง DB
        Note saved = noteRepository.save(note);

        // 3. แปลงกลับเป็น Response DTO
        return toResponse(saved);
    }

    // ลบโน้ตตาม ID
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    // อัปเดตโน้ต
    public NoteResponse update(Long id, NoteRequest req) {
        return noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(req.getTitle());
                    note.setContent(req.getContent());
                    note.setImageUrl(req.getImageUrl()); // ✅ ตอนนี้ไม่ null แล้ว
                    Note saved = noteRepository.save(note);
                    return toResponse(saved);
                })
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }

    // ฟังก์ชันแปลง Entity -> DTO
    private NoteResponse toResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .imageUrl(note.getImageUrl())
                .build();
    }
}
