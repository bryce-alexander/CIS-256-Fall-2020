/* Date.java */

import java.util.ArrayList;
import java.util.Arrays;

class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    if (Date.isValidDate(month, day, year)!=true) {
      System.out.println("This is not a valid date.  Please try again.");
      System.exit(0);
    }
    this.month = month;
    this.day = day;
    this.year = year;
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
    if (!s.matches("\\d{1,2}/\\d{1,2}/\\d{1,4}")) {
      System.out.println("This is not a valid date.  Please enter with format MM/DD/YYYY");
      System.exit(0);
    }
    String[] dateArray = s.split("/");
    this.month = Integer.parseInt(dateArray[0]);
    this.day = Integer.parseInt(dateArray[1]);
    this.year = Integer.parseInt(dateArray[2]);

  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    if (year%4==0){
      if (year%400==0){
        return true;
      }
      else if (year%100==0){
        return false;
      }
      else {
        return true;
      }
    }
    return false;                        // replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    ArrayList thirtyDays = new ArrayList(Arrays.asList(4, 6, 9, 11));
    if (month!=2) {
      if (thirtyDays.contains(month)) {
        return 30;
      }
      else {
        return 31;
      }
    }
    if (Date.isLeapYear(year)) {
      return 29;
    }
    return 28;                           // replace this line with your solution
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    if (month<1 || month>12 || year<1 || day<1 || day>31) {
      return false;
    }
    else if (day>daysInMonth(month, year)) {
      return false;
    }
    return true;                        // replace this line with your solution
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    return (this.month + "/" + this.day + "/" + this.year);                     // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
/*    return (this.year * this.month * this.day)<(d.year*d.month*d.day);*/
/*    if ((this.year * this.month * this.day)<(d.year*d.month*d.day)){
      return true;
    }*/
    if (this.year<d.year) {
      return true;
    }
    else if (this.year>d.year) {
      return false;
    }
    else if (this.month<d.month) {
      return true;
    }
    else if (this.month>d.month) {
      return false;
    }
    else if (this.day<d.day) {
      return true;
    }
    return false;                        // replace this line with your solution
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    return (!isBefore(d) && this!=d);
                      // replace this line with your solution
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    int dayCount=day;
    for (int i=month-1; i>0; i--){
      dayCount+=daysInMonth(i,year);
    }
    return dayCount;                           // replace this line with your solution
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    int originTotalDays=this.dayInYear();
    int targetTotalDays=d.dayInYear();
    for (int y=this.year-1; y>0; y--) {
      Date _tempDate = new Date(12,31,y);
      originTotalDays += _tempDate.dayInYear();// replace this line with your solution
    }
    for (int y=d.year-1; y>0; y--) {
      Date _tempDate = new Date(12,31,y);
      targetTotalDays += _tempDate.dayInYear();// replace this line with your solution
    }
    return originTotalDays-targetTotalDays;
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));

    System.out.println("\nisLeapYear test: ");
    System.out.println("1800 (false): " + Date.isLeapYear(1800));
    System.out.println("1900 (false): " + Date.isLeapYear(1900));
    System.out.println("1600 (true): " + Date.isLeapYear(1600));
    System.out.println("2000 (true): " + Date.isLeapYear(2000));
    System.out.println("400 (true): " + Date.isLeapYear(400));

    System.out.println("\ndaysInMonth test: ");
    System.out.println("October of any year (31): " + Date.daysInMonth(10,1988));
    System.out.println("July of any year (31): " + Date.daysInMonth(7,1921));
    System.out.println("April of any year (30): " + Date.daysInMonth(4,1934));
    System.out.println("November of any year (30): " + Date.daysInMonth(11,1900));
    System.out.println("February of leap year 2020 (29): " + Date.daysInMonth(2,2020));
    System.out.println("February of  non leap year 2019 (28): " + Date.daysInMonth(2,2019));

    System.out.println("\nisValidDate test: ");
    System.out.println("4/31/2020 (false): " + Date.isValidDate(4,31,2020));
    System.out.println("5/30/2020 (true): " + Date.isValidDate(5,30,2020));
    System.out.println("2/29/2020 (true): " + Date.isValidDate(2,29,2020));
    System.out.println("2/29/2019 (false): " + Date.isValidDate(2,29,2019));
    System.out.println("0/28/2019 (false): " + Date.isValidDate(0,28,2019));
    System.out.println("4/32/2019 (false): " + Date.isValidDate(4,32,2019));
    System.out.println("13/12/2019 (false): " + Date.isValidDate(13,12,2019));
    System.out.println("3/0/2019 (false): " + Date.isValidDate(3,0,2019));

    System.out.println("\ndayInYear test: ");
    Date t1 = new Date(3,1,2020);
    System.out.println("3/1/2020 (61): " + t1.dayInYear());
    Date t2 = new Date("3/1/2019");
    System.out.println("3/1/2019 (60): " + t2.dayInYear());
    Date t3 = new Date("8/23/2012");
    System.out.println("8/23/2012 (236): " + t3.dayInYear());
  }
}
