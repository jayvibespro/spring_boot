package com.jaycode.demo.features.department;

import com.jaycode.demo.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    private ResponseModel<List<Department>> getAllDepartments(){
        List<Department> departments = departmentService.getAllDepartments();

        return new ResponseModel<>(200, true, "Departments fetched successfully", departments);
    }

    @PostMapping
    private ResponseModel<Department> addDepartment(@RequestBody  Department department){
        Department addedDepartment = departmentService.addDepartment(department);

        return  new ResponseModel<>(201, true, "Department created successfully", addedDepartment);
    }
}
