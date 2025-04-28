package s25.cs151.application;

public class Course implements Comparable<Course> {
    private String courseCode;
    private String courseName;
    private int courseNumber;

    public Course(String courseCode, String courseName, int courseNumber) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getCourseNumber() {
        return this.courseNumber;
    }

    @Override
    public int compareTo(Course other) {
        if (this.courseCode.compareTo(other.courseCode) < 0) {
            return 1;
        } else if (this.courseCode.compareTo(other.courseCode) > 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
