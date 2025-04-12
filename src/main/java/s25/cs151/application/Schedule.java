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

    public String getStudentName() {
        return this.studentName;
    }

    public String getReason() {
        return this.reason;
    }

    public String getComment() {
        return this.comment;
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
            String startTime;
            String startTimeOther;

            if(this.timeSlot.contains("-")){
                startTime = this.timeSlot.split("-")[0].trim();
            } else {
                startTime = this.timeSlot;
            }

            if(other.timeSlot.contains("-")){
                startTimeOther = other.timeSlot.split("-")[0].trim();
            } else {
                startTimeOther = other.timeSlot;
            }

            String[] hourMinute = startTime.split(":");
            int hour = Integer.parseInt(hourMinute[0]);
            int minute = Integer.parseInt(hourMinute[1]);
            int totalTime = hour * 60 + minute;
        
            String[] hourMinuteOther = startTimeOther.split(":");
            int hourOther = Integer.parseInt(hourMinuteOther[0]);
            int minuteOther = Integer.parseInt(hourMinuteOther[1]);
            int totalTimeOther = hourOther * 60 + minuteOther;

            if(totalTime < totalTimeOther){
                return -1;
            } else if (totalTime > totalTimeOther){
                return 1;
            } else {
                return 0;
            }
        }
    }
}
