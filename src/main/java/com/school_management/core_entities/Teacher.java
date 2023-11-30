import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private int teacherID;
    private boolean teacherIDGenerated;
    private String name;
    private long phoneNumber;
    private String email;
    private Department department;
    private List<Course> coursesTaught;
    private List<CourseSection> sectionsCurrentlyTeaching;
    
    // Constructor for Teacher
    public Teacher(String name, long phoneNumber, String email, Department department) {
        setTeacherID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        coursesTaught = new ArrayList<>();
        sectionsCurrentlyTeaching = new ArrayList<>();
    }

    // Getter for teacher ID
    public int getTeacherID() {
        return this.teacherID;
    }

    // Setter for teacher ID
    public void setTeacherID() {
        this.teacherID = teacherIDGenerator();
    }

    // Private method to generate teacher ID
    private int teacherIDGenerator() {
        if (!teacherIDGenerated) {
            return 111111; // Temporary ID generation logic
            /*
             * generate the unique ID for teachers
             * (Actual logic for generating unique ID can be added here)
             */
        }
        return getTeacherID();
    }

    // Getters and setters for teacher details

    // Get teacher name
    public String getName() {
        return this.name;
    }

    // Set teacher name
    public void setName(String name) {
        this.name = name;
    }

    // Get teacher phone number
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    // Set teacher phone number
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Get teacher email
    public String getEmail() {
        return this.email;
    }

    // Set teacher email
    public void setEmail(String email) {
        this.email = email;
    }

    // Get department of the teacher
    public Department getDepartment() {
        return this.department;
    }

    // Set department of the teacher
    public void setDepartment(Department department) {
        this.department = department;
    }

    // Get courses taught by the teacher
    public List<Course> getCoursesTaught() {
        return Collections.unmodifiableList(coursesTaught);
        //returns new ArrayList to prevent the modification of original List
    }

    // Set courses taught by the teacher
    public void setCourses(List<Course> coursesTaught) {
        if(coursesTaught != null) {
            this.coursesTaught = new ArrayList<>(coursesTaught);
            //assigns new ArrayList to prevent the modification of original List
        } else {
            throw new IllegalArgumentException("Courses taught cannot be null");
        }
    }

    // Add a course taught by the teacher
    public void addCourse(Course courseTaught) {
        if(courseTaught != null) {
            this.coursesTaught.add(courseTaught);
        } else {
            throw new IllegalArgumentException("Course taught cannot be null");
        }
    }

    // Remove a course taught by the teacher
    public void removeCourse(Course courseToRemove) {
        boolean removed = coursesTaught.remove(courseToRemove);
        if(!removed){
            throw new IllegalArgumentException("Course not found in the List");
        }
    }

    // Get sections currently taught by the teacher
    public List<CourseSection> getsectionsCurrentlyTeaching() {
        return Collections.unmodifiableList(sectionsCurrentlyTeaching);
        //returns new ArrayList to prevent the modification of original List
    }

    // Set sections currently taught by the teacher
    public void setClasses(List<CourseSection> sectionsCurrentlyTeaching) {
        this.sectionsCurrentlyTeaching = sectionsCurrentlyTeaching;
    }

    // Add a section currently taught by the teacher
    public void addSection(CourseSection section) {
        this.sectionsCurrentlyTeaching.add(section);
    } 

    // Remove a section currently taught by the teacher
    public void removeSection(CourseSection section) {
        if (sectionsCurrentlyTeaching.contains(section))
            sectionsCurrentlyTeaching.remove(section);
    }   

    // Overridden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return teacherID == teacher.teacherID && Objects.equals(name, teacher.name) && phoneNumber == teacher.phoneNumber && Objects.equals(email, teacher.email) && Objects.equals(department, teacher.department) && Objects.equals(coursesTaught, teacher.coursesTaught) && Objects.equals(sectionsCurrentlyTeaching, teacher.sectionsCurrentlyTeaching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID, name, phoneNumber, email, department, coursesTaught, sectionsCurrentlyTeaching);
    }

    @Override
    public String toString() {
        return "{" +
            " teacherID='" + getTeacherID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", department='" + getDepartment() + "'" +
            ", coursesTaught='" + getCoursesTaught() + "'" +
            ", sectionsCurrentlyTeaching='" + getsectionsCurrentlyTeaching() + "'" +
            "}";
    }
}
