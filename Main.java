package program02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * File: Main.java
 * Author: Miguel Menjivar
 * Class: CS 4310 - Operating Systems
 * 
 * Assignment: Program 02
 * Date last modified: 3/9/2019
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
	
	static int peopleArriving; // scheduled number of people arriving
	
	static final int MAX_TIME = 5; // the max amount of time a person can stay in restroom
	static final int MAX_RESTROOM = 3; // max amount of people that can be in restroom at time
	
	Queue<Integer> male = new LinkedList<>(); // male queue when waiting to enter restroom
	Queue<Integer> female = new LinkedList<>(); // female queue when waiting to enter restroom
	
	
	/**
	 * no-argument constructor
	 * set private values to 0
	 */
	public restroom() {
		id = 0;
		gender = 0;
		time = 0;
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
	 * will generate schedule when people arrive and wait time
	 */
	public void arrivalSchedules() {
		peopleArriving = 10; // depends on scheduled arrival time
		generateLine(peopleArriving);
		System.out.println("hello world");
		
	}
	
	/**
	 * will randomly generate random person 
	 * 0 for male && 1 for female
	 * called from arrivalSchedules()
	 * 
	 * @param numPeopleArriving
	 */
	public void generateLine(int numPeopleArriving) {
		int randPerson;
		for(int i = 0; i < numPeopleArriving; i++) {
			randPerson = rand.nextInt(2); // generates 0 or 1
			addQueue(randPerson);
		}
	}
	
	/**
	 * adds random person (male || female) to their respective queues
	 * 
	 * @param randValue value from generateLine()
	 */
	public void addQueue(int randPerson) {
		if(randPerson == 0) {
			female.add(randPerson);
		} else {
			male.add(randPerson);
		}
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
	 * 
	 * @param id
	 * @param gender
	 */
	public void arrive(int id, int gender) {
		System.out.println("arrive section");
	}
	
	/**
	 * 
	 * @param id
	 * @param gener
	 * @param time
	 */
	public void useFacilities(int id, int gener, int time) {
		System.out.println("using the facilities section");
	}
	
	/**
	 * 
	 * @param id
	 * @param gender
	 */
	public void depart(int id, int gender) {
		System.out.println("departing section");
	}
	
	/**
	 * will increment every time a person arrives
	 * 
	 * @return id + 1
	 */
	public int addId() {
		id++;
		return id;
	}
	
	/**
	 * 
	 */
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		
	}

}

public class Main {
	public static void main(String[] args) {
		System.out.println("Program 02\n");
		
		restroom room = new restroom(); // creates restroom object
		
		room.arrivalSchedules(); // scheduling when people arrive example
		
		// prints out lines for male female 
		System.out.println("Male line: " + room.male);
		System.out.println("Female line: " + room.female);
		
	}
}
