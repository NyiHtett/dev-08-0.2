package s25.cs151.application;

public class Schedule implements Comparable<Schedule> {
    private String studentName;
    private String scheduleDate;
    private String timeSlot;
    private String courseName;
    private String reason;
    private String comment;

    public Schedule(String studentName, String scheduleDate, String timeSlot,
                    String courseName, String reason, String comment) {
        this.studentName = studentName;
        this.scheduleDate = scheduleDate;
        this.timeSlot = timeSlot;
        this.courseName = courseName;
        this.reason = reason;
        this.comment = comment;
    }

    public String getScheduleDate() {
        return this.scheduleDate;
    }

    public String getTimeSlot() {
        return this.timeSlot;
    }


    @Override
    public int compareTo(Schedule other) {
        String[] splitDate = this.scheduleDate.split("-");
        int dateReformat = Integer.parseInt(String.join("", splitDate));

        String[] splitDateOther = other.scheduleDate.split("-");
        int dateReformatOther = Integer.parseInt(String.join("", splitDateOther));

        if (dateReformat < dateReformatOther) {
            return -1;
        } else if (dateReformat > dateReformatOther) {
            return 1;
        } else {
            // TO DO
            return 0;
        }
    }
}
