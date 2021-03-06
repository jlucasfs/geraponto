/**
 * 
 */
package input;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

import model.Collaborator;

/**
 * @author thiago.lima
 *
 */
public class FileReader {

	private static final String FILE_COLLABORATORS = System.getProperty("user.dir") + "/colaboradores.txt";
	private static final Integer TOLERANCE_MINUTES = 5;

	public static HashSet<Collaborator> fillColaborators() throws IOException {
		HashSet<Collaborator> colalboratos = new HashSet<>();
		try (Stream<String> stream = getStream(FILE_COLLABORATORS)) {

			stream.forEach(l -> colalboratos.add(new Collaborator(Long.parseLong(l.split(";")[1]), l.split(";")[0])));

		} catch (IOException e) {
			throw new IOException("Arquivo " + FILE_COLLABORATORS + " nao encontrado");
		}

		return colalboratos;
	}

	private static void setEnconcingToFile(String file) throws IOException {
		byte[] sourceBytes = Files.readAllBytes(Paths.get(file));
		String data = new String(sourceBytes);
		byte[] destinationBytes = data.getBytes("UTF-8");
		data = new String(destinationBytes);
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		try {
			out.write(data);
		} finally {
			out.close();
		}
//		Files.write(Paths.get(file), destinationBytes);
	}

	private static Stream<String> getStream(String file) throws IOException {
		setEnconcingToFile(file);
		return Files.lines(Paths.get(file));
	}

	public static HashSet<Collaborator> fillTimeTable(String fileTimeTable) throws IOException {
		HashSet<Collaborator> collaborators = fillColaborators();
		try {

			for (Collaborator c : collaborators) {
				// pega a entrada
				Stream<String> stream = getStream(fileTimeTable);
				// filtra as linhas pelo tamanho e pis filtra as linhas pelo
				// codigo de operacao
				stream.filter(line -> line.contains(String.valueOf(c.getPis()))).filter(line -> line.length() == 38)
						.filter(line -> line.substring(9, 10).equals("3"))
						.forEach(line -> c.getTimetable().add(entryDate(c, line)));
			}

		} catch (IOException e) {
			throw new IOException("Arquivo nao encontrado");
		}

		return collaborators;
	}

	private static LocalDateTime entryDate(Collaborator c, String line) {
		LocalDateTime entry = LocalDateTime.of(Integer.parseInt(line.substring(14, 18)), // ano
				Integer.parseInt(line.substring(12, 14)), // mes
				Integer.parseInt(line.substring(10, 12)), // dia
				Integer.parseInt(line.substring(18, 20)), // hora
				Integer.parseInt(line.substring(20, 22)));// minuto
		// procura data no mesmo periodo
		Optional<LocalDateTime> str = c.getTimetable().stream().filter(tt -> tt.getYear() == entry.getYear())
				.filter(tt -> tt.getDayOfYear() == entry.getDayOfYear()).filter(tt -> tt.getHour() == entry.getHour())
				.filter(tt -> Math.abs(entry.until(tt, ChronoUnit.MINUTES)) < TOLERANCE_MINUTES).findAny();
		// se a diferen�a foir maior que 5 minutos vale como entrada nova, se
		// n�o retorna a mesma data, que � ignorada pelo hashset
		if (str.isPresent()) {
			return str.get();
		}
		return entry;
	}

}
