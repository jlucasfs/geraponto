/**
 * 
 */
package main;

import java.io.IOException;
import java.util.HashSet;

import input.FileReader;
import model.Collaborator;

/**
 * @author thiago.lima
 *
 */
public class Main {

	static HashSet<Collaborator> collaborators;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			collaborators = FileReader.fillTimeTable();
			//imprimindo lista
			for (Collaborator c : collaborators) {

				System.out.println(c.getName());
				c.getTimetable().forEach(tt -> System.out.println(tt.toString()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
