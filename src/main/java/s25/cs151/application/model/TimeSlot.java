package s25.cs151.application.model;

public class TimeSlot implements Comparable<TimeSlot> {
    private String fromHour;
    private String toHour;

    public TimeSlot(String fromHour, String toHour) {
        this.fromHour = fromHour;
        this.toHour = toHour;
    }

    public String getFromHour() {
        return this.fromHour;
    }

    public String getToHour() {
        return this.toHour;
    }

    @Override
    public int compareTo(TimeSlot other) {
        String[] split = this.fromHour.split(":");
        int hr = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);

        String[] otherSplit = other.fromHour.split(":");
        int otherHr = Integer.parseInt(otherSplit[0]);
        int otherMin = Integer.parseInt(otherSplit[1]);

        if (hr < otherHr) {
            return -1;
        } else if (hr > otherHr) {
            return 1;
        } else {
            if (min < otherMin) {
                return -1;
            } else if (min > otherMin) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
