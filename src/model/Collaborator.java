package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Collaborator {

	long pis;
	String name;
	LinkedHashSet<LocalDateTime> timetable;

	public Collaborator(long pis, String name) {
		this.pis = pis;
		this.name = name;
		this.timetable = new LinkedHashSet<>();
		}

	public long getPis() {
		return pis;
	}

	public void setPis(long pis) {
		this.pis = pis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<LocalDateTime> getTimetable() {
		return timetable;
	}

	public void setTimetable(LinkedHashSet<LocalDateTime> timetable) {
		this.timetable = timetable;
	}
	
	
}
