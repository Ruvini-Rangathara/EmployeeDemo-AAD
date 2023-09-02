package com.example.empdemonew.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employee")
public class EmployeeDto implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String department;
    @Lob
    private byte[] profile;

//    public EmployeeDto(String id, String name, String email, String department) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.department = department;
//    }
}
