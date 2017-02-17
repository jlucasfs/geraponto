package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Collaborator {

	Long pis;
	String name;
	LinkedHashSet<LocalDateTime> timetable;

	public Collaborator(Long pis, String name) {
		this.pis = pis;
		this.name = name;
		this.timetable = new LinkedHashSet<>();
		}

	public Long getPis() {
		return pis;
	}

	public void setPis(Long pis) {
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
	
	public LinkedHashMap<LocalDate, LinkedHashSet<LocalTime>> getTimeTableByDay(){
		LinkedHashMap<LocalDate, LinkedHashSet<LocalTime>> listByDate = new LinkedHashMap<>();
		for (LocalDateTime localDateTime : getTimetable()) {
			listByDate.put(localDateTime.toLocalDate(), retornaHorarios(localDateTime,getTimetable()));
		}
		return listByDate;
	}

	private LinkedHashSet<LocalTime> retornaHorarios(LocalDateTime localDateTime, HashSet<LocalDateTime> timetable2) {
		LinkedHashSet<LocalTime> localTime = new LinkedHashSet<LocalTime>();
		for (LocalDateTime lt : timetable2) {
			if(lt.toLocalDate().isEqual(localDateTime.toLocalDate())){
				localTime.add(lt.toLocalTime());
			}
		}
		return localTime;
	}
	
	
}
