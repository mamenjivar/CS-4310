/**
 * 
 */
package program02;

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
 *
 */
public class Main implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Program 02\n");
		onePerson(0, 1, 1);
	}
	
	/**
	 * 
	 * @param id = the ID of the person
	 * @param gender 0 for men and 1 for women
	 * @param time = the amount of time person will take in restroom
	 */
	static void onePerson(int id, int gender, int time){
		arrive(id, gender);
		useFacilities(id, gender, time);
		depart(id, gender);
		
	}
	
	/**
	 * 
	 * @param id
	 * @param gender
	 */
	static void arrive(int id, int gender) {
		System.out.println("arrive section");
	}
	
	/**
	 * 
	 * @param id
	 * @param gener
	 * @param time
	 */
	static void useFacilities(int id, int gener, int time) {
		System.out.println("using the facilities section");
	}
	
	/**
	 * 
	 * @param id
	 * @param gender
	 */
	static void depart(int id, int gender) {
		System.out.println("departing section");
	}

	/**
	 * 
	 */
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
