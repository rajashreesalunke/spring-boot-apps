package com.telusko.student_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentRepo repo;
    @RequestMapping("/getStudents")
    public List<Students> getStudents(){
        //return List.of(
//                new Students(1,"Rohan",22),
//                new Students(2,"Rajashree",22),
//                new Students(3,"Anisha",21)
        return repo.findAll();
        //);
    }
    @RequestMapping("/addStudent")
    public void addStudent(){
    Students s=new Students();
    s.setName("Raj");
    s.setAge(20);
    repo.save(s);
    }


}
