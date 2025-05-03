package s25.cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

/**
 * Best Practice (based on professors experience):
 * Creating a dedicated class whose main function
 * is to allow sharing things/objects between controllers.
 * Such objects should only be instantiated once.
 *
 * This class utilizes Class Design Pattern: Singleton
 * This class now utilizes Polymorphism: April 28, 2025
 */
public class CommonObjects {
    private static CommonObjects commonObjects = new CommonObjects();

    // JavaFX can work directly with ObservableList instead of ArrayList
    private ObservableList<Comparable> CSVList;

    /**
     * Private constructor required for singleton design pattern.
     */
    private CommonObjects() {}

    /**
     * Provide access to commonObject.
     * @return the one and only instance of CommonObjects.
     */
    public static CommonObjects getInstance() {
        return commonObjects;
    }

    /**
     * Reads a CSV file and returns a ObservableList of objects determined by the file.
     * @param file the CSV file to read from.
     * @return an ObservableList object
     */
    public ObservableList getCSVList(File file) {
        try {
            Scanner sc = new Scanner(file);
            CSVList = FXCollections.observableArrayList();
            String line = "";
            String[] elements;

            sc.nextLine();
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                elements = line.split(",");
                if (file.getName().equals("semester_office_hours.csv")) {
                    TermWeekday tw = new TermWeekday(elements[0], Integer.parseInt(elements[1]), elements[2]);
                    CSVList.add(tw);
                } else if (file.getName().equals("courses.csv")) {
                    Course c = new Course(elements[0], elements[1], Integer.parseInt(elements[2]));
                    CSVList.add(c);
                } else if (file.getName().equals("time_slots.csv")) {
                    TimeSlot ts = new TimeSlot(elements[0],elements[1]);
                    CSVList.add(ts);
                } else if (file.getName().equals("schedule.csv")) {
                    Schedule s = new Schedule(elements[0],elements[1], elements[2],
                            elements[3], elements[4], elements[5]);
                    CSVList.add(s);
                } else {
                    throw new Exception("Invalid file provided");
                }
            }
            Collections.sort(CSVList);
            return CSVList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
