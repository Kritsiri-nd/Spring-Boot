package com.techup.spring_demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.techup.spring_demo.entity.Note;
import com.techup.spring_demo.repository.NoteRepository;

@Service 
@RequiredArgsConstructor 
public class NoteService { 
    
    private final NoteRepository noteRepository; 

    public List<Note> getAll() { 
        return noteRepository.findAll(); // ดึงข้อมูลโน้ตทั้งหมดจากฐานข้อมูล
    }

    public Note create(Note note) { 
        return noteRepository.save(note); // บันทึกโน้ตใหม่ลงในฐานข้อมูล
    }

    public void delete(Long id) {
        noteRepository.deleteById(id); // ลบโน้ตตาม ID ที่ระบุ
    }

    public Note update(Long id, Note updatedNote) {
        return noteRepository.findById(id)
            .map(note -> {
                note.setTitle(updatedNote.getTitle());
                note.setContent(updatedNote.getContent());
                note.setImageUrl(updatedNote.getImageUrl());
                return noteRepository.save(note); // บันทึกการอัปเดตโน้ตลงในฐานข้อมูล
            })
            .orElseThrow(() -> new RuntimeException("Note not found with id " + id)); // แสดงข้อความเมื่อไม่พบโน้ตตาม ID
    }
}
