
/**
 * Athelete.java
 * @author Chris Lai	
 * @author Yan Wen
 * @author Rachana Thanneeru
 * @author Jasmine So
 * @author Amit Bal
 * CIS 22C, Group project
 */

import java.text.DecimalFormat;

public class Athlete implements Comparable<Athlete> {
	private String name; // primary key
	private String college; // secondary key
	private String events; // the event that the athlete participating
	private int birthYear;
	private String gender;
	private String birthplace; // combinations of home town City and State of birth
	private String overallPoints;
	private int internationalMedals;
	private int internationalExperience;

	/**
	 * Constructor for the Athlete class
	 * 
	 * @param name     the Athele's first and last name
	 * @param director the Movie's director
	 * @param year     the year the Movie was released
	 * @param gross    the amount grossed over the lifetime of the movie, given in
	 *                 Millions of dollars
	 * 
	 */

	public Athlete(String name, String college, String events, String birthplace, String gender,
			int internationalMedals, int internationalExperience, int birthYear) {
		this.name = name;
		this.college = college;
		this.birthYear = birthYear;
		this.gender = gender;
		this.events = events;
		this.birthplace = birthplace;
		this.internationalMedals = internationalMedals;
		this.internationalExperience = internationalExperience;
		this.overallPoints = "-1";
//		Overall points is initiated with -1 to show unassignment, possibly to add into bst2
	}

	/**
	 * This function will assign points when called
	 */
	void calculate() {

		int age = 2020 - birthYear;

		// Calculating points
		int result = 0;
		if (gender.toLowerCase() == "male") {
			if (age == 25) {
				result += 5;
			} else if (age == 24 || age == 26) {
				result += 3;
			} else if (age == 23 || age == 27) {
				result += 2;
			} else if (age == 22 || age == 28) {
				result += 1;
			} else {

			}
		}
		if (gender.toLowerCase() == "female") {
			if (age == 25) {
				result += 5;
			} else if (age == 24 || age == 26) {
				result += 3;
			} else if (age == 23 || age == 27) {
				result += 1;
			} else {

			}
		}

		result += internationalMedals * 5 + internationalExperience * 2;
		this.overallPoints = "" + result;
	}

	/**
	 * Accesses the name of Athlete
	 * 
	 * @return the Athlete's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accesses the college that the Athlete graduated
	 * 
	 * @return the Athlete's college
	 */
	public String getCollege() {
		return college;
	}

	/**
	 * Access the year the Athlete was born
	 * 
	 * @return the year the Athlete was born
	 */
	public int getBirthYear() {
		return birthYear;
	}

	/**
	 * Access the events that the athlete participated
	 * 
	 * @return the athlete's events
	 */
	public String getEvents() {
		return events;
	}

	/**
	 * Access the birth location of the athlete
	 * 
	 * @return the athlete's birthplace
	 */
	public String getLocation() {
		return birthplace;
	}

	/**
	 * Access the gender of the athlete
	 * 
	 * @return the athlete's gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Access the overall points of the athlete
	 * 
	 * @return the athlete's overall points
	 */
	public String getOverallPoints() {
		return overallPoints;
	}

	/**
	 * Access the number of international medals that the athlete received
	 * 
	 * @return the number of international medals that the athlete received
	 */
	public int getInternationalMedals() {
		return internationalMedals;
	}

	/**
	 * Access the number of years participated as an international athlete
	 * 
	 * @return the number of years participated as an international athlete
	 */
	public int getInternationalExperience() {
		return internationalExperience;
	}

	/**
	 * Sets the name of the Athlete
	 * 
	 * @param give name to the Athlete
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the events of athlete
	 * 
	 * @param the events of athlete
	 */
	public void setEvents(String events) {
		this.events = events;
	}

	/**
	 * Sets the year the the Athlete was born
	 * 
	 * @param the year that the Athlete was born
	 */
	public void setYear(int birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * Sets Location that the Athlete was born
	 * 
	 * @param Location that the Athlete was born
	 */
	public void setLocation(String location) {
		this.birthplace = location;
	}

	/**
	 * Sets the gender of the athlete
	 * 
	 * @param Gender of the athlete
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the overall points of the athlete
	 * 
	 * @param give overall points to the athlete
	 */
	public void setOverallPoints(String overallPoints) {
		this.overallPoints = overallPoints;
	}

	/**
	 * Sets the number of international medals that the athlete received
	 * 
	 * @param give the number of international medals that the athlete received
	 */
	public void setInternationalMedals(int internationalMedals) {
		this.internationalMedals = internationalMedals;
	}

	/**
	 * Sets the number of years participated as an international athlete
	 * 
	 * @param the number of years participated as an international athlete
	 */
	public void setInternationalExperience(int internationalExperience) {
		this.internationalExperience = internationalExperience;
	}

	/**
	 * Creates a String of the Athlete information the following format: Name:
	 * <firstname lastname> College: <college> Events: <events> Birth year:
	 * <birthYear> Note that there should be no <> in the resulting String
	 */
	@Override
	public String toString() {

		String result = "";
		result += getName().toUpperCase() + "\n";
		result += "=====================================\n";
		result += "College    : " + getCollege() + "\n";
		result += "Event      : " + getEvents() + "\n";
		result += "Birth year : " + getBirthYear() + "\n";
		result += "SOB        : " + getLocation() + "\n";
		result += "Gender     : " + getGender() + "\n";
		result += "Years in sport event  : " + getInternationalExperience() + "\n";
		result += "Medals Won            : " + getInternationalMedals() + "\n";
		result += "Overall Points Given  : " + getOverallPoints() + "\n\n";

		return result;

	}

	/**
	 * Determines whether two Athlete objects are equal by comparing name and
	 * College
	 * 
	 * @param otherAthlete the second Athlete object
	 * @return whether the Athletes are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Athlete)) {
			return false;
		} else {
			Athlete temp = (Athlete) o;
			if (o.equals(temp)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * (Primary key comparison) Compares two Athlete objects to determine ordering
	 * by their names Returns 0 if the two items are equal Return -1 if this
	 * Athlete's name comes alphabetically before the other's name. Returns 1 if the
	 * other Athelete's name comes alphabetically before this name of the other
	 * athelete If the two athlete's are the same, will differentiate by college of
	 * athlete (alphabetical comparison)
	 * 
	 * @param the other Athlete object to compare to this
	 * @return 0 (same athlete), -1 (this athlete ordered first) or 1 (the other
	 *         athlete ordered first)
	 */
	@Override
	public int compareTo(Athlete otherAthlete) {

		if (overallPoints == "-1") {

			// Calling method to calculate the points for current athlete and
			// otherAthlete before storing it
			otherAthlete.calculate();
			calculate();


			// Comparing Secondary Key first for bst2
			if (overallPoints.compareTo(otherAthlete.getOverallPoints()) > 0) {
				return 1;
			} else if (overallPoints.compareTo(otherAthlete.getOverallPoints()) < 0) {
				return -1;
			} else {

				// Then comparing Primary key for bst2
				if (name.compareTo(otherAthlete.getName()) > 0) {
					return 1;
				} else if (name.compareTo(otherAthlete.getName()) < 0) {
					return -1;
				}
			}
			return -1;

		} else {

			System.out.println(otherAthlete.getName());
			// comparing Primary key for bst 1
			if (name.compareTo(otherAthlete.getName()) > 0) {
				return 1;
			} else if (name.compareTo(otherAthlete.getName()) < 0) {
				return -1;
			} else {

				// Then comparing secondary key for bst 1
				if (overallPoints.compareTo(otherAthlete.getOverallPoints()) > 0) {
					return 1;
				} else if (overallPoints.compareTo(otherAthlete.getOverallPoints()) < 0) {
					return -1;
				}

			}

		}

		return 0;
	}

	/**
	 * Returns a consistent hash code for each Movie by summing the Unicode values
	 * of each character in the key Key = title + director
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode() {
		String Key = name;
		int sum = 0;
		int charUni = 0;

		for (int i = 0; i < Key.length(); i++) {
			charUni = (int) Key.charAt(i);
			sum += charUni;
		}
		return sum;
	}

}