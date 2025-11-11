package com.techup.spring_demo.dto;

import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Size; 
import lombok.Data;

@Data
public class NoteRequest {
    @NotBlank(message = "Title is required") // ตรวจสอบว่า title ไม่เป็นค่าว่าง
    @Size(max = 255, message = "Title must be less than 255 characters") // จำกัดความยาว title ไม่เกิน 255 ตัวอักษร
    private String title;
    @Size(max = 5000, message = "Content must be less than 5000 characters") // จำกัดความยาว content ไม่เกิน 5000 ตัวอักษร
    private String content;
    @Size(max = 500, message = "Image URL must be less than 500 characters")
    private String imageUrl;
}
