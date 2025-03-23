package s25.cs151.application;

public class OfficeHour {
    private String semester;
    private String year;
    private String days;

    public OfficeHour(String semester, String year, String days) {
        this.semester = semester;
        this.year = year;
        this.days = days;
    }

    // Getters and setters for each property
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}