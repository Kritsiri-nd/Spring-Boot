package com.techup.spring_demo.controller;

import com.techup.spring_demo.entity.Note; // นำเข้า Note entity เพื่อใช้ในการรับส่งข้อมูลโน้ต
import com.techup.spring_demo.service.NoteService; // นำเข้า NoteService เพื่อใช้ในการจัดการโน้ต
import lombok.RequiredArgsConstructor; // ใช้สำหรับการสร้างคอนสตรัคเตอร์อัตโนมัติ
import org.springframework.web.bind.annotation.*; // ใช้สำหรับการสร้าง RESTful API
import java.util.List; // เก็บข้อมูลหลายรายการ

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    // GET/api/notes 
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAll(); // เรียกใช้เมธอดจาก NoteService เพื่อดึงข้อมูลโน้ตทั้งหมด
    }

    // POST/api/notes
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.create(note); // เรียกใช้เมธอดจาก NoteService เพื่อสร้างโน้ตใหม่
    }

    // DELETE/api/notes/{id}
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.delete(id); // เรียกใช้เมธอดจาก NoteService เพื่อลบโน้ตตาม ID ที่ระบุ
    }

    // PUT/api/notes/{id}
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return noteService.update(id, updatedNote); // เรียกใช้เมธอดจาก NoteService เพื่ออัปเดตโน้ตตาม ID ที่ระบุ
    }
}
