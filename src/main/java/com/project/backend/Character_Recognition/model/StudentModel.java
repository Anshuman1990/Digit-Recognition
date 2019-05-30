package com.project.backend.Character_Recognition.model;

public class StudentModel {
    private String studentName;
    private String usn;
    private TestSheetModel test_1;
    private TestSheetModel test_2;
    private TestSheetModel test_3;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public TestSheetModel getTest_1() {
        return test_1;
    }

    public void setTest_1(TestSheetModel test_1) {
        this.test_1 = test_1;
    }

    public TestSheetModel getTest_2() {
        return test_2;
    }

    public void setTest_2(TestSheetModel test_2) {
        this.test_2 = test_2;
    }

    public TestSheetModel getTest_3() {
        return test_3;
    }

    public void setTest_3(TestSheetModel test_3) {
        this.test_3 = test_3;
    }
}
