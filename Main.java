package program02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * File: Main.java
 * Author: Miguel Menjivar
 * Class: CS 4310 - Operating Systems
 * 
 * Assignment: Program 02
 * Date last modified: 3/15/2019
 * 
 * Purpose:
 * Writing a multi-threaded program
 * Gender-segregated restroom that only allows women in the restroom and no men
 * and allows men into the restroom but no women can enter
 *
 */
class restroom implements Runnable{
	
	static Random rand = new Random(); // randomized gender
	
	private int id; // number which identifies each person upon arrival restroom (assigned sequentially)
	private int gender; // 0 for men || 1 for women
	private int time; // amount of time person will stay in restroom
	
	int depart; // keeps track of the order in which people leave the restroom
	
	static int peopleArriving; // scheduled number of people arriving
	
	static final int MAX_TIME = 5000; // the max amount of time a person can stay in restroom (5s = 5000ms)
	static final int MAX_RESTROOM = 2; // max amount of people that can be in restroom at time (index 0)
	final int GENDER_MALE = 0;
	final int GENDER_FEMALE = 1;
	
	Queue<Integer> maleLine = new LinkedList<>(); // male queue when waiting to enter restroom
	Queue<Integer> femaleLine = new LinkedList<>(); // female queue when waiting to enter restroom
	
	// or make another queue to keep track of people in restroom
	ArrayList<Integer> restroom = new ArrayList<>(); // will keep track of people in the restroom
	
	
	/**
	 * no-argument constructor
	 * set private values to 0
	 */
	
	// WILL CREATE A NEW THREAD BUT RESET VALUES LIKE FOR ID
	public restroom() {
//		id = 0;
		gender = 0;
//		time = 0;
	}
	
	/**
	 * Parameterized constructor
	 * 
	 * @param newId
	 * @param newGender
	 * @param newTime
	 */
	public restroom(int newId, int newGender, int newTime) {
		id = newId;
		gender = newGender;
		time = newTime;
	}
	
	/**
	 * setter for integer id
	 * 
	 * @param newId
	 */
	public void setId(int newId) {
		id = newId;
	}
	
	/**
	 * setter for integer gender
	 * 
	 * @param newGender
	 */
	public void setGender(int newGender) {
		gender = newGender;
	}
	
	/**
	 * setter for integer time
	 * 
	 * @param newTime
	 */
	public void setTime(int newTime) {
		time = newTime;
	}
	
	/**
	 * getter value for integer id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * getter for value integer gender
	 * 
	 * @return
	 * SYNCHRONIZED
	 */
	public int getGender() {
		return gender;
	}
	
	/**
	 * getter for value integer time
	 * 
	 * @return
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * will look over creation of each person(thread) and 
	 * go through the restroom process
	 */
	public void overlord() {
		createPerson();
		
		// shows person in gender line TEST
		System.out.println("Male line: " + maleLine);
		System.out.println("Female line: " + femaleLine);
		///////////////////////
		
		// for testing purposes 1 thread
		addId(); // add number to person
		onePerson(getId(), getGender(), MAX_TIME); // go through restroom process
	}
	
	/**
	 * will create a thread that simulates one person
	 * 
	 * Person (thread) will be assigned a random value 0 or 1
	 * 
	 * then will be added to their designated lines (queue)
	 * SYNCRHRONIZED
	 */
	public void createPerson() {
		int randGender;
		randGender = rand.nextInt(2); // generates 0 or 1
		setGender(randGender);
		// for testing purposes randGender OG
		addToGenderLine(randGender);		
	}
	
	/**
	 * adds random person (male || female) to their respective lines (queues)
	 * 
	 * @param randValue value from generateLine()
	 * SYNCHRONIZED
	 */
	public void addToGenderLine(int randPerson) {
		if(randPerson == GENDER_MALE) {
			maleLine.add(randPerson);
		} else if(randPerson == GENDER_FEMALE) {
			femaleLine.add(randPerson);
		} else {
			System.out.println("Gender not 0 or 1");
		}
	}
	
	/**
	 * removes person from their respective lines to go to restroom
	 * 
	 * @param leaveLine
	 * SYNCRHONIZED
	 */
	public void removeGenderLine(Queue<Integer> leaveLine) {
		leaveLine.remove();
	}
	
	/**
	 * will check if restroom line is empty
	 * true = empty
	 * false = not empty
	 * 
	 * @param restroomLine
	 * @return
	 */
	public boolean isEmpty(ArrayList<Integer> restroomLine) {
		return restroomLine.isEmpty();
	}
	
	/**
	 * when person enters restroom
	 * MUST BE OF SAME GENDER
	 * 
	 * @param gender
	 * SYNCRHONZIED
	 */
	public  void enterRestroom(int gender) {
		restroom.add(gender);
	}
	
	/**
	 * when person exits restroom
	 * 
	 * @param gender
	 * SYNCHRONIZED
	 */
	public  void exitRestroom() {
		restroom.remove(0);
	}
	
	/**
	 * Will return how many people are currently in the restroom
	 * 
	 * @return
	 */
	public synchronized int sizeRestroom() {
		return restroom.size();
	}
	
	/**
	 * 
	 * @param id = the ID of the person
	 * @param gender 0 for men and 1 for women
	 * @param time = the amount of time person will take in restroom
	 */
	public void onePerson(int id, int gender, int time){
		arrive(id, gender);
		useFacilities(id, gender, time);
		depart(id, gender);
		
	}
	
	/**
	 * This procedure must not return until it is okay for person to enter the restroom
	 * (it must guarantee that there will be only women or only men in the restroom and that the
	 * room limit of 3 is not exceeded
	 * 
	 * @param id
	 * @param gender
	 */
	public void arrive(int id, int gender) {
//		if() {
			
//		}
		
		// checks if gender is male or female
		if(gender == GENDER_MALE) {
			removeGenderLine(maleLine);  // removes person from respective line ( male)
			enterRestroom(gender); // adds person to restroom
		} else if (gender == GENDER_FEMALE){
			removeGenderLine(femaleLine); // removes person from respective line (female)
			enterRestroom(gender);
		} else {
			System.out.println("Error: gender not 0 or 1");
		}
		
		// TEST check if person made it into restroom
		System.out.println("People in restroom: " + restroom);
		//////////////////////
		
		
		// prints out message when person has arrived in restroom
		System.out.println("ARRIVED    => Person id: " + id + " ||| gender: " + showGender(gender) + "(" + gender + ")");
	}
	
	/**
	 * This procedure should just delay time in seconds
	 * and print out a debug message
	 * 
	 * @param id
	 * @param gener
	 * @param time
	 */
	public void useFacilities(int id, int gender, int time) {
		// will delay the amount of time person will be in the restroom
//		System.out.println("FACILITIES => Person id: " + id + " SLEEP");
		try {
//			System.out.println( Thread.currentThread());
			Thread.sleep(time);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// prints out message when person is using the facility
		System.out.println("FACILITIES => Person id: " + id + " ||| gender: " + showGender(gender) + "(" + gender +")");
	}
	
	/**
	 * This procedure is called to indicate that the person is
	 * ready to exit. It should also update a shared variable departure index,
	 * which keeps track of the order in which the people leave the restroom IE the first person
	 * to leave the restroom has departure index 1, the 2nd person has departure index 2, and so on.
	 * This procedure should also print out the departure index for that person
	 * 
	 * @param id
	 * @param gender
	 */
	public void depart(int id, int gender) {
		// counts departure index then remove person from restroom BLOCK
		leaveRestroomCount(); // increments when person is ready to leave restroom
		exitRestroom(); //  will remove person from restroom array
		
		// prints out departure message
		System.out.println("DEPART     => Person id: " + id + " ||| gender: " + showGender(gender) + "(" + gender + ")" + " ||| Departure Id: " + showLeaveRestroomCount());
//		System.out.println("restroom depart: " + restroom);
	}
	
	/**
	 * will increment the depart value when person leaves restroom
	 * @return
	 */
	public synchronized int leaveRestroomCount() {
		return depart++;
	}
	
	/**
	 * will show the current number of departed person
	 * @return
	 */
	public synchronized int showLeaveRestroomCount() {
		return depart;
	}
	
	/**
	 * will increment every time a person arrives
	 * 
	 * @return id + 1
	 * SYNCRHONIZED
	 */
	public int addId() {
		return id++;
	}
	
	/**
	 * will just print if whether male or female
	 * 
	 * @param gender
	 * @return
	 */
	public String showGender(int gender) {
		if(gender == 0) {
			return "male";
		} else {
			return "female";
		}
	}
	
	/**
	 * 
	 */
	public void run() {
		// create 1 thread
		overlord();
		
	}
}

/**
 * The main method where program runs
 *
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Program 02\n");
		
		Runnable room = new restroom(); // creates restroom object
		
//		Thread t3 = new Thread(new restroom());
//		Thread t4 = new Thread(new restroom());
//		t3.start();
//		t4.start();
		

		// successfully creates 2 threads and puts them in same wait period
		Thread t1 = new Thread(room);
		t1.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t2 = new Thread(room);
		t2.start();
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Thread t3 = new Thread(room);
//		t3.start();
		
		
//		room.arrivalSchedules(); // scheduling when people arrive example
		
		// prints out lines for male female 
//		System.out.println("Male line: " + room.male);
//		System.out.println("Female line: " + room.female);
		
	}
}
