/**
 * 
 */
package input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.stream.Stream;
import model.Collaborator;

/**
 * @author thiago.lima
 *
 */
public class FileReader {

	private static final String FILE_COLLABORATORS = System.getProperty("user.dir") + "/src/" + "colaboradores.txt";
	private static final String FILE_TIMETABLE = System.getProperty("user.dir") + "/src/" + "20170202.txt";

	public static HashSet<Collaborator> fillColaborators() throws IOException {

		HashSet<Collaborator> colalboratos = new HashSet<>();
		try (Stream<String> stream = getStream(FILE_COLLABORATORS)) {

			stream.forEach(l -> colalboratos.add(new Collaborator(Long.parseLong(l.split(";")[1]), l.split(";")[0])));

		} catch (IOException e) {
			throw new IOException("Arquivo " + FILE_COLLABORATORS + " não encontrado");
		}

		return colalboratos;
	}

	private static Stream<String> getStream(String file) throws IOException {
		return Files.lines(Paths.get(file));
	}

	public static HashSet<Collaborator> fillTimeTable() throws IOException {
		HashSet<Collaborator> colalboratos = fillColaborators();
		try {
			for(Collaborator c : colalboratos){
				
				Stream<String> stream = getStream(FILE_TIMETABLE); //pega a entrada
				stream.filter(line -> line.contains(String.valueOf(c.getPis()))).filter(line -> line.length() == 38) // filtra as linahs pelo tamanho e pis
						.filter(line -> line.substring(9, 10).equals("3")) //filtra as linhas pelo codigo de operação
						.forEach(line -> c.getTimetable().add(LocalDateTime.of(Integer.parseInt(line.substring(14, 18)),//ano
								Integer.parseInt(line.substring(12, 14)),//mes
								Integer.parseInt(line.substring(10, 12)),//dia
								Integer.parseInt(line.substring(18, 20)),//hora
								Integer.parseInt(line.substring(20, 22))))); //minuto

			}

		} catch (IOException e) {
			throw new IOException("Arquivo " + FILE_TIMETABLE + " não encontrado");
		}
		return colalboratos;
	}
	

}
