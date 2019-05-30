package com.project.backend.Character_Recognition.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentListModel {
    private List<StudentModel> students = new ArrayList<>();

    public List<StudentModel> getStudents() {
        return students;
    }

    public void addStudents(StudentModel students) {
        this.students.add(students);
    }
}
