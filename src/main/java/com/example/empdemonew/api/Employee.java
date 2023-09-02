package com.example.empdemonew.api;


import com.example.empdemonew.dto.EmployeeDto;
import com.example.empdemonew.model.EmployeeModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("api/v1/emp")
public class Employee {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String saveEmployeeData(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam("profile") MultipartFile profileFile
    ) throws IOException {
//        System.out.println("Id: " + id);
//        System.out.println("Name: " + name);
//        System.out.println("Email: " + email);
//        System.out.println("Department: " + department);

        byte[] profileBytes = profileFile.getBytes();
        String profileStr = Base64.getEncoder().encodeToString(profileBytes);
//        System.out.println("Profile: " + profileStr);

        boolean isSave = EmployeeModel.saveEmployeeToDatabase(new EmployeeDto(id, name, email, department, profileFile.getBytes()));

        if(isSave){
            return "Saved";
        }
        return "Not Saved";

    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String updateEmployeeData(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam("profile") MultipartFile profileFile
    ) throws IOException {
//        System.out.println("Id: " + id);
//        System.out.println("Name: " + name);
//        System.out.println("Email: " + email);
//        System.out.println("Department: " + department);

        byte[] profileBytes = profileFile.getBytes();
        String profileStr = Base64.getEncoder().encodeToString(profileBytes);
//        System.out.println("Profile: " + profileStr);

        boolean isUpdate = EmployeeModel.updateEmployeeDatabase(new EmployeeDto(id, name, email, department, profileFile.getBytes()));

        if(isUpdate){
            return "Update";
        }
        return "Not Updated";
    }

    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String deleteEmployeeData(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam("profile") MultipartFile profileFile
    ) throws IOException {
//        System.out.println("Id: " + id);
//        System.out.println("Name: " + name);
//        System.out.println("Email: " + email);
//        System.out.println("Department: " + department);

        byte[] profileBytes = profileFile.getBytes();
        String profileStr = Base64.getEncoder().encodeToString(profileBytes);
//        System.out.println("Profile: " + profileStr);

        boolean isDeleted = EmployeeModel.deleteEmployeeDatabase(new EmployeeDto(id, name, email, department, profileFile.getBytes()));

        if(isDeleted){
            return "Delete";
        }
        return "Not Deleted";
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public EmployeeDto searchEmployeeData(@RequestParam String id) throws IOException {
//        System.out.println("Id: " + id);
//
//        EmployeeDto employeeDto = EmployeeModel.searchEmployee(id);
//        System.out.println(employeeDto.toString());
//        if(employeeDto==null){
//            System.out.println("Employee Not Found!");
//        }
//        return employeeDto;
//    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> searchEmployeeData(@RequestParam String id) {
        System.out.println("Id: " + id);

        EmployeeDto employeeDto = EmployeeModel.searchEmployee(id);

        if (employeeDto == null) {
            System.out.println("Employee Not Found!");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Employee Found: " + employeeDto.toString());
            return ResponseEntity.ok(employeeDto);
        }
    }

}
