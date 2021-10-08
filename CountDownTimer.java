package project1;
import java.io.*;
import java.util.Scanner;

/****************************************************************
 * Class description
 *
 */
public class CountDownTimer {
    /*****
     * the instance variable of the number of hours, minutes, and seconds
     */
    private int hours;
    private int minutes;
    private int seconds;
    private static boolean suspended = false;

    /***********
     * Default constructor that sets the CountDownTimer to zero.
     */
    public CountDownTimer () {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    /***********
     * A constructor that initializes the
     * instance variables with the provided values
     * @param hours user input the number of hours >0
     * @param minutes user input the number of 0<minutes<60
     * @param seconds user input the number of 0<seconds<60
     */
    public CountDownTimer(int hours, int minutes,int seconds) {
        if (hours < 0 || minutes < 0 || minutes >= 60 || seconds < 0 || seconds >= 60 ) {
            throw new IllegalArgumentException();
        }
        else {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }
    }

    /************
     * initializes the instance variables
     * with the provided values. Initialize hours to 0.
     * @param minutes user input the number of 0<minutes<60
     * @param seconds user input the number of 0<seconds<60
     */
    public CountDownTimer(int minutes, int seconds) {
        if (minutes < 0 || minutes >= 60 || seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException();
        }
        else {
            this.hours = 0;
            this.minutes = minutes;
            this.seconds = seconds;
        }
    }

    /*************
     * initializes the instance variable seconds
     * with the provided value. Initialize hours and minutes to 0.
     * @param seconds user input the number of 0<seconds<60
     */
    public CountDownTimer(int seconds) {
        if (seconds < 0 || seconds >= 60) {
            throw new IllegalArgumentException();
        }
        else {
            this.hours = 0;
            this.minutes = 0;
            this.seconds = seconds;
        }
    }

    /*************
     * initializes the instance variables with the other
     * CountDownTimer parameter’s instance variables.
     * @param other a new CountDownTimer object
     */
    public CountDownTimer(CountDownTimer other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        else {
            this.hours = other.hours;
            this.minutes = other.minutes;
            this.seconds = other.seconds;
        }
    }

    /*************
     * constructor that accepts a String as
     * a parameter with the following format: “1:21:30”
     * @param startTime A string in hh:mm:ss format
     *
     */
    public CountDownTimer(String startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException();
        }
        // empty string will assign variable to 0
        else if (startTime == "") {
            this.hours = 0;
            this.minutes = 0;
            this.seconds = 0;
        }
        else {
            // create a list of number after remove ":"
            String[] timeSplit = startTime.split(":");
            for (int i = 0; i < timeSplit.length; i++) {
                if (timeSplit.length > 3){
                    throw new IllegalArgumentException();
                }
                if (timeSplit.length == 1) {
                    seconds = Integer.parseInt(timeSplit[i]);
                    if (seconds < 0 || seconds > 59) {
                        throw new IllegalArgumentException();
                    }
                }
                else if (timeSplit.length == 2) {
                    minutes = Integer.parseInt(timeSplit[i]);
                    i++;
                    seconds = Integer.parseInt(timeSplit[i]);
                    if (minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
                        throw new IllegalArgumentException();
                    }
                }
                else {
                    hours = Integer.parseInt(timeSplit[i]);
                    i++;
                    minutes = Integer.parseInt(timeSplit[i]);
                    i++;
                    seconds = Integer.parseInt(timeSplit[i]);
                    if (hours < 0 || hours > 59 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59)
                        throw new IllegalArgumentException();
                }
            }
        }
    }
    /************
     * A method that returns
     * true if  “this” CountDownTimer object is exactly the same
     * @throws IllegalArgumentException when Object other is null
     * @return the boolean if object is the same as this
     */
    public boolean equals (Object other) {
        if (other == null)
            throw new IllegalArgumentException();
        else if (other instanceof String) {
            throw new IllegalArgumentException();
        }
        else {
            CountDownTimer temp = (CountDownTimer) other;
            return this.hours == (temp.hours) && this.minutes == (temp.minutes)
                    && this.seconds == (temp.seconds);
        }
    }

    /**************
     * A static method that returns true if CountDownTimer object t1
     * is exactly the same as CountDownTimer object t2
     * @param t1 object to compare
     * @param t2 object to compare
     * @throws IllegalArgumentException when t1 or t2 null
     * @return boolean if they are equal
     */
    public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
        if (t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        }
        else {
            return t1.hours == t2.hours && t1.minutes == t2.minutes
                    && t1.seconds == t2.seconds;
        }
    }
    /*************************
     * A method that returns 1 if “this” CountDownTimer object is
     * greater than the other CountDownTimer object;
     * returns -1 if “this” CountDownTimer object is
     * less than the other CountDownTimer;
     * or returns 0 if “this” CountDownTimer
     * object is equal to the other CountDownTimer object.
     * @return 1,-1,0
     */
    public int compareTo(CountDownTimer other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        else {
            // convert all hours and minutes to second, compare the total seconds
            if (this.totalTime() == other.totalTime())
                return 0;
            else if (this.totalTime() > other.totalTime())
                return 1;
            else
                return -1;
        }
    }

    /******************************
     * A method that returns 1 if  CountDownTimer object t1 is greater than CountDownTimer object t2;
     * returns -1 if the CountDownTimer object t1 is less than CountDownTimer object t2;
     * returns 0 if the CountDownTimer object t1 is equal to CountDownTimer object t2.
     * @param t1 object 1
     * @param t2 object 2
     * @return 1,-1,0
     */
    public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
        if (t1 == null || t2 == null) {
            throw new IllegalArgumentException();
        }
        else {
            if (t1.totalTime() > t2.totalTime())
                return 1;
            else if (t1.totalTime() < t2.totalTime())
                return -1;
            else
                return 0;
        }
    }


    /*************
     * A method that decrements “this” CountDownTimer by 1 second.
     */
    public void dec() {
        if (hours  < 0 || minutes < 0 || seconds < 0)
            throw new IllegalArgumentException();
        else if (this.seconds == 0 && this.minutes == 0 && this.hours == 0) {
            throw new IllegalArgumentException();
        }
        else
            if (!CountDownTimer.suspended) {
                // when minutes and seconds = 0, hour changes, minute + second are 59
                if (minutes == 0 && seconds == 0) {
                    hours--;
                    minutes = 59;
                    seconds = 59;
                } else if (seconds == 0) {
                    minutes--;
                    seconds = 59;
                } else {
                    seconds--;
                }
            }
    }

    /*************
     * A method that subtracts the given number
     * of seconds from “this” CountDownTimer object
     * @param seconds seconds that user want to subtract
     */
    public void sub(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException();
        }
        else if (this.seconds == 0 && this.minutes == 0 && this.hours == 0) {
            throw new IllegalArgumentException();
        }
        else {
            for (int i = 0; i < seconds; i++) {
                dec();
            }
        }
    }

    /**************
     * A method that subtracts CountDownTimer
     * other from the “this” CountDownTimer object
     * @param other object that user want to subtract
     */
    public void sub(CountDownTimer other) {
        // exception when null and when there is nothing to subtract
        if (other == null || other.totalTime() > this.totalTime()) {
            throw new IllegalArgumentException();
        }
        else {
            sub(other.totalTime());
        }
    }

    /*************
     * A method that increments the “this” CountDownTimer by 1 second.
     */
    public void inc() {
        if (!CountDownTimer.suspended) {
            seconds++;
            // change the minute when seconds max, seconds go back to 0, minutes plus 1
            if (seconds == 60) {
                minutes++;
                seconds = 0;
            }
            // change the hour when minutes max, minute + second go back to 0
            if (minutes == 60) {
                hours++;
                minutes = 0;
                seconds = 0;
            }
        }
    }

    /*************
     * A method that adds the number of
     * seconds to “this” CountDownTimer object.
     * @param seconds the number of seconds that needs to be added
     */
    public void add(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException();
        }
        else {
            for (int i = 0; i < seconds; i++) {
                inc();
            }
        }
    }

    /*************
     * A method that adds CountDownTimer other to “this” CountDownTimer object.
     * @param other a new CountDownTimer object to add
     */
    public void add(CountDownTimer other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        else {
            add(other.totalTime());
        }
    }

    /*************
     * A method that returns a string that represents the state of a
     * CountDownTimer with the following format:  “1:06:01”
     * @return  A string that in format hh:mm:ss
     */
    public String toString() {
        String finalString = "";
        if (minutes < 10 && seconds < 10)
            finalString = hours + ":0" + minutes + ":0" + seconds;
        else if (minutes < 10)
            finalString = hours + ":0" + minutes + ":" + seconds;
        else if (seconds < 10)
            finalString = hours + ":" + minutes + ":0" + seconds;
        else
            finalString = hours + ":" + minutes + ":" + seconds;
        return finalString;
    }

    /**************************************************************
     *A  method that loads the “this” CountDownTimer object from a file;
     * use the parameter filename for the name of the file.
     * @param fileName the name of the file
     */
    public void load(String fileName) {
        if (fileName == null)
            throw new IllegalArgumentException();
        if (!CountDownTimer.suspended) {
            int hours;
            int minutes;
            int seconds;
            Scanner fileReader = null;
            try {
                fileReader = new Scanner(new File(fileName));
                hours = fileReader.nextInt();
                minutes = fileReader.nextInt();
                seconds = fileReader.nextInt();
                if (hours < 0 || minutes < 0 || minutes > 59 || seconds < 0
                        || seconds > 59)
                    throw new IllegalArgumentException();
                else
                    this.hours = hours;
                    this.minutes = minutes;
                    this.seconds = seconds;
                System.out.println(this.hours);
                System.out.println(this.minutes);
                System.out.println(this.seconds);
            } catch (Exception error) {
                throw new RuntimeException();
                // System.out.println("Oops!  Something went wrong.");
            }
        }

        // problem reading the file

    }
    /****************
     *A method that saves the “this” CountDownTimer to a file;
     * use the parameter filename for the name of the file.
     * @param fileName name of the file
     */
    public void save(String fileName) {
        if (fileName == null)
            throw new IllegalArgumentException();
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        out.println(this.hours);
        out.println(this.minutes);
        out.println(this.seconds);
        out.close();
    }

    /***************************
     * These method that turns ‘off’ and ‘on’ any mutation method in CountDownTimer. If suspend is true,
     * then all mutation method stop changing the state of the machine.
     * If suspend is false, then all operation act normally
     *
     */
    public static void setSuspended(boolean suspended) {
        CountDownTimer.suspended = suspended;
    }

    /************
     * Methods that return the boolean value of suspended
     * @return suspended
     */
    public static boolean isSuspended() {
        return suspended;
    }

    /******************
     * A method that returns the totalTime
     * in seconds
     * @return total seconds
     */
    public int totalTime() {
        // convert hour and minutes to seconds, then plus the seconds
         return hours*3600+minutes*60+seconds;
    }

    public int getHours() {return hours;}

    public void setHours(int hours) {
        if (hours < 0)
            throw new IllegalArgumentException();
        else
            this.hours = hours;
    }

    public int getMinutes() {return minutes;}
    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException();
        else
            this.minutes = minutes;
    }
    public int getSeconds() {return seconds;}
    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59)
            throw new IllegalArgumentException();
        else
            this.seconds = seconds;
    }
}
// go to each method to add if susended work
