package s25.cs151.application;

import s25.cs151.application.controller.TableViewController;

public class TermWeekday implements Comparable<TermWeekday> {
    private String semester;
    private int semesterVal;
    private int year;
    private String weekdays;

    public TermWeekday(String semester, int year, String weekdays) {
        this.semester = semester;
        this.year = year;
        this.weekdays = weekdays;
    }

    public String getSemester() {
        return this.semester;
    }

    public int getYear() {
        return this.year;
    }

    public String getWeekdays() {
        return this.weekdays;
    }

    public void setSemesterVal() {
        if (this.semester.equals("Spring")) {
            this.semesterVal = 1;
        } else if (this.semester.equals("Summer")) {
            this.semesterVal = 2;
        } else if (this.semester.equals("Fall")) {
            this.semesterVal = 3;
        } else if (this.semester.equals("Winter")) {
            this.semesterVal = 4;
        }
    }

    @Override
    public int compareTo(TermWeekday other) {
        setSemesterVal();
        other.setSemesterVal();
        if (this.year < other.year) {
            return 1;
        } else if (this.year > other.year) {
            return -1;
        } else {
            if (this.semesterVal < other.semesterVal) {
                return 1;
            } else if (this.semesterVal > other.semesterVal) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
