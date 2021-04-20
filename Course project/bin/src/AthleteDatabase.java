/**
 * AtheleteDatabase.java
 * @author Chris Lai	
 * @author Yan Wen
 * @author Rachana Thanneeru
 * @author Jasmine So
 * @author Amit Bal
 * CIS 22C, Group project
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class AthleteDatabase {
    private final int NUM_ATHLETES = 26;
    Hash<Athlete> ht = new Hash<>(NUM_ATHLETES * 2);
    BST<Athlete> bst1 = new BST<>();
    BST<Athlete> bst2 = new BST<>();
    
    public static void main(String[] args) throws IOException {
    	AthleteDatabase myAthleteDatabase = new AthleteDatabase();

        String name;
        String college;
        String sport;
        String stateOfBirth;
        String gender;
        int birthYear;
        int overallPoints;
        int internationalMedals;
        int internationalExperience;
        
        
        char userInput;
        System.out.println("Welcome to the Team USA Database!\n");
      
        try {
    	File file = new File("inputFile.txt");
            Scanner input = new Scanner(file);
	        do {
	        	name = input.nextLine();
	        	college = input.nextLine();
	        	sport = input.nextLine();
	        	stateOfBirth = input.nextLine();
	        	gender = input.nextLine();
	        	internationalMedals = input.nextInt();
	        	internationalExperience = input.nextInt();
	        	birthYear = input.nextInt();
	        	if(input.hasNextLine()) {
	        		input.nextLine();
	        	}
	      	
	        	Athlete myAthlete = new Athlete(name.trim(), college.trim(), sport.trim(), stateOfBirth.trim(), gender.trim(), internationalMedals, internationalExperience, birthYear);
	        	
	        	//insert into bst2 first before calculating points
	        	myAthleteDatabase.bst2.insert(myAthlete);
	        	
	        	//calculate points, then insert into bst1
	        	myAthlete.calculate();
	        	myAthleteDatabase.bst1.insert(myAthlete);
	        	myAthleteDatabase.ht.insert(myAthlete);
	        	
//	        	System.out.println(myAthlete.toString());
	        	
	        	if(input.hasNext()) {
	        	input.nextLine();
	        	}
	        	
	        } while (input.hasNextLine());
	        
	        input.close();
        
        } catch (IOException e) {
			System.out.println (e.toString());
		}

        Scanner readUserInput = new Scanner(System.in);
        
        do {
        	System.out.println("Please select from one of the following options:\n");
        	System.out.println("A. Add an Athlete");
        	System.out.println("D. Delete an Athlete");
        	System.out.println("S. Search for an Athlete");
        	System.out.println("L. List all Athletes");
        	System.out.println("W. Write data to a file");
        	System.out.println("X. Exit\n");
        	
        	System.out.print("Enter your choice: ");
        	userInput = readUserInput.next().charAt(0);
        	
        	if(userInput != 'A' && userInput != 'D' && userInput != 'S' && userInput != 'L' 
        		&& userInput != 'W' && userInput != 'X') {
        		System.out.println("\nInvalid Input!\n");
        	}
        	
        	// ADDing athlete
        	
        	
        	if(userInput == 'A') {
        		System.out.println("\nAdding an Athlete!\n");
        		readUserInput.nextLine();
				System.out.print("Enter the name: ");
				name = readUserInput.nextLine();
				System.out.print("Enter the college: ");
				college = readUserInput.nextLine();
				System.out.print("Enter the sport: ");
				sport = readUserInput.nextLine();
				System.out.print("Enter the state of birth: ");
				stateOfBirth = readUserInput.nextLine();
				System.out.print("Enter the gender Male/Female: ");
				gender = readUserInput.nextLine();
				System.out.print("Enter the number of international medals the athelete has received: ");
				internationalMedals = readUserInput.nextInt();
				System.out.print("Enter the number of years participated as an international athele: ");
				internationalExperience = readUserInput.nextInt();
				System.out.print("Enter the birth year: ");
				birthYear = readUserInput.nextInt();
				
				Athlete a = new Athlete(name, college, sport, stateOfBirth, gender, internationalMedals, internationalExperience, birthYear);
				myAthleteDatabase.bst2.insert(a);
				
				//Calculate points then insert into bst1
				a.calculate();
				myAthleteDatabase.bst1.insert(a);
				myAthleteDatabase.ht.insert(a);
				
				System.out.println("\n" + name + " was added!\n");
        	}
        	
        	
        	// Deleting athlete
        	
        	
        	if(userInput == 'D') {
        		System.out.println("\nDeleting an athlete!\n");
        		readUserInput.nextLine();
				System.out.print("Enter the name: ");
				name = readUserInput.nextLine();
				System.out.print("Enter the number of points: ");
				college = readUserInput.nextLine();
				
				//searching for athlete first using hash table
				Athlete a = new Athlete(name, college, null, null, null, 0, 0, 0);
				int inBase = myAthleteDatabase.ht.search(a);

				if (inBase != -1) {
					myAthleteDatabase.bst1.remove(a);
					myAthleteDatabase.bst2.remove(a);
					myAthleteDatabase.ht.remove(a);
					
		            System.out.println("\n" + name + " was removed!\n");
		         } else {
		             System.out.println("\nI cannot find " + name + " in the database.\n");
		         }
        	}
        	
        	
        	// Searching for an athlete
        	
        	if(userInput == 'S') {
        		System.out.println("\nSearching for an Athlete!\n");
        		readUserInput.nextLine();
				System.out.print("Enter the name: ");
				name = readUserInput.nextLine();
				System.out.print("Enter the college: ");
				college = readUserInput.nextLine();
				
				Athlete a = new Athlete(name, college, null, null, null, 0, 0, 0);
				int inBase = myAthleteDatabase.ht.search(a);
				if (inBase != -1) {
					System.out.println(name + " is in database!");
		         } else {
		        	 System.out.println(name + " isn't in database!");
		         }
        	}
        	
        	
        	//Listing out the athletes
        	
        	if(userInput == 'L') {
        		char userInputL;
        		System.out.println("\nPlease select from one of the following options:\n");
        		System.out.println("U. Unsorted");
        		System.out.println("P. Primary Key (name)");
        		System.out.println("S. Secondary Key (points)");
        		
        		System.out.println("\nEnter your choice: ");
        		userInputL = readUserInput.next().charAt(0);
        		
        		//Printing options
            	if(userInputL == 'U') {
            		System.out.println("Displaying Athlehtes unsorted:\n");
            		System.out.println(myAthleteDatabase.ht);
            	}
            	if(userInputL == 'P') {
            		System.out.println("Displaying Athlehtes sorted by name:\n");
            		myAthleteDatabase.bst1.inOrderPrint();
            	}
            	if(userInputL == 'S') {
            		System.out.println("Displaying Athletes sorted by points:\n");
            		myAthleteDatabase.bst2.inOrderPrint();
            	}
        	}
        	
        	
        	//Writes out the athlete information into a file
        	
        	if(userInput == 'W') {
        		java.io.File outFile = new java.io.File ("outputFile1.txt");
        		
        		try{ //Writes to outputFile
					PrintWriter out = new PrintWriter("outputFile1.txt");
					
					out.write(myAthleteDatabase.ht.toString()); 
					out.close();
					
				} catch (IOException e){ // Catches errors
					
					System.out.println("Unable to write to file");
					System.exit(-1);
				}
        		
        	}
        	
        	
        	
        } while(userInput != 'X');

        readUserInput.close();
        
       
    }
    
    
}