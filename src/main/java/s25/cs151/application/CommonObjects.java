package s25.cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import s25.cs151.application.controller.CoursesTableViewController;
import s25.cs151.application.controller.TimeSlotsTableViewController;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Best Practice (based on professors experience):
 * Creating a dedicated class whose main function
 * is to allow sharing things/objects between controllers.
 * Such objects should only be instantiated once.
 *
 * This class utilizes Class Design Pattern: Singleton
 */
public class CommonObjects {
    private static CommonObjects commonObjects = new CommonObjects();

    // JavaFX can work directly with ObservableList instead of ArrayList
    // We are creating a list of TermWeekday Objects using courses.csv
    private ObservableList<TermWeekday> termWeekdayCSVList;

    private ObservableList<Course> courseCSVList;

    private ObservableList<TimeSlot> timeSlotCSVList;


    private CommonObjects() {}

    public static CommonObjects getInstance() {
        return commonObjects;
    }

    public ObservableList<TermWeekday> getTermWeekdayCSVList() {
        try {
            File file = new File("semester_office_hours.csv");
            Scanner sc = new Scanner(file);
            termWeekdayCSVList = FXCollections.observableArrayList();
            String line = "";
            String[] elements;

            sc.nextLine();
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(",");
                TermWeekday tw = new TermWeekday(elements[0], Integer.parseInt(elements[1]), elements[2]);
                termWeekdayCSVList.add(tw);
            }
            Collections.sort(termWeekdayCSVList);
            return termWeekdayCSVList;
        } catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
        return null;
    }

    public ObservableList<Course> getCourseCSVList() {
        try {
            File file = new File("courses.csv");
            Scanner sc = new Scanner(file);
            courseCSVList = FXCollections.observableArrayList();
            String line = "";
            String[] elements;

            sc.nextLine();
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(",");
                Course c = new Course(elements[0], elements[1], Integer.parseInt(elements[2]));
                courseCSVList.add(c);
            }
            Collections.sort(courseCSVList);
            return courseCSVList;
        } catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
        return null;
    }

    public ObservableList<TimeSlot> getTimeSlotCSVList() {
        try {
            File file = new File("time_slots.csv");
            Scanner sc = new Scanner(file);
            timeSlotCSVList = FXCollections.observableArrayList();
            String line = "";
            String[] elements;

            sc.nextLine();
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(",");
                TimeSlot ts = new TimeSlot(elements[0],elements[1]);
                timeSlotCSVList.add(ts);
            }
            Collections.sort(timeSlotCSVList);
            return timeSlotCSVList;
        } catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
        return null;
    }
}
