public class Student {
    private String id;
    private String name;
    private String grade;
    private String department;
    private String gpa;

    public Student(String id, String name, String grade, String department, String gpa) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.gpa = gpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getDepartment() {
        return department;
    }

    public String getGpa() {
        return gpa;
    }

    public String getStudentsData(){
        return " ID : "+id+" Name : "+name+" Grade : "+grade+" Department : "+department+" GPA : "+gpa;
    }
    public String writeAllData(){
        return id+","+name+","+grade+","+department+","+gpa;
    }
}
