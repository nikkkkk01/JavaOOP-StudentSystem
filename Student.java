package myPackage;

public class Student {
    private String name;
    
    private String studentID;
    private String courseCode;

   
    public Student() {
    }

   
    public Student(String name, String studentID, String courseCode) {
        this.name = name;
        this.studentID = studentID;
        this.courseCode = courseCode;
    }

   
    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourse() {
        return courseCode;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCourse(String courseCode) {
        this.courseCode = courseCode;
    }
}
