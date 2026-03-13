    package com.example.ngogiaphuong2280602523.model;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SinhVien {
    private Long id;
    
    @NotBlank(message = "TĂªn lĂ  báº¯t buá»™c")
    private String ten;

    @Min(value = 18, message = "Tuá»•i pháº£i lá»›n hÆ¡n hoáº·c báº±ng 18")
    @Max(value = 100, message = "Tuá»•i pháº£i nhá» hÆ¡n hoáº·c báº±ng 100")
    private int tuoi;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Khoa pháº£i lĂ  chá»¯")
    private String khoa;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
}

