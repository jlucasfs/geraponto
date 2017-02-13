/**
 * 
 */
package output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import model.Collaborator;

/**
 * @author thiago.lima
 *
 */
public class FileWriter {

	private static final Integer INITIAL_CELL = 0;
	private static final Integer INITIAL_ROW = 0;
	private static final String SHEET_TITLE = "Folha de Ponto ";
	private static final String COLUMN_PIS = "PIS:";
	private static final String COLUMN_NAME = "Nome:";
	private static final String COLUMN_DATE = "Data";
	private static final String COLUMN_HOUR = "Hora ";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String HOUR_FORMAT = "HH:mm";
	private static final String DATE_HOUR_FORMAT = "dd-MM-yy HHmm";
	private static final String FILE_EXTENSION = ".xls";

	private HSSFWorkbook workbook;
	private Integer maxColumn;
	private Integer columnNumber;
	private Integer rowNumber;

	public FileWriter() {
		super();
		this.workbook = new HSSFWorkbook();
		this.columnNumber = INITIAL_CELL;
		this.rowNumber = INITIAL_ROW;
	}

	/**
	 * Processa a lista de colaboradores e horários
	 * 
	 * @param collabList
	 */
	public void processFile(HashSet<Collaborator> collabList) {

		for (Collaborator collaborator : collabList) {
			HSSFSheet sheet = getWorkbook().createSheet(collaborator.getPis().toString());
			createSheet(sheet, collaborator);
		}

		String filename = generateFileName();
		try {
			saveFile(filename);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Cria uma nova pasta de planilha para cada {@link Collaborator}
	 * 
	 * @param sheet
	 * @param collaborator
	 */
	public void createSheet(HSSFSheet sheet, Collaborator collaborator) {

		createHeader(sheet, collaborator);
		createTimeSheet(sheet, collaborator);

	}

	/**
	 * Cria o cabeçalho com informações pessoais do {@link Collaborator}
	 * 
	 * @param sheet
	 * @param collaborator
	 */
	public void createHeader(HSSFSheet sheet, Collaborator collaborator) {

		HSSFRow row = sheet.createRow(INITIAL_ROW);
		Cell cell = row.createCell(INITIAL_CELL);
		cell.setCellValue(COLUMN_PIS);
		cell = row.createCell(INITIAL_CELL + 1);
		cell.setCellValue(collaborator.getPis());

		row = sheet.createRow(sheet.getLastRowNum() + 1);
		cell = row.createCell(INITIAL_CELL);
		cell.setCellValue(COLUMN_NAME);
		cell = row.createCell(INITIAL_CELL + 1);
		cell.setCellValue(collaborator.getName());

		// Insere uma linha vazia para separação
		row = sheet.createRow(sheet.getLastRowNum() + 2);

	}

	/**
	 * Cria uma linha para cada dia trabalhado, listando as marcações de ponto por hora
	 * @param sheet
	 * @param collaborator
	 */
	public void createTimeSheet(HSSFSheet sheet, Collaborator collaborator) {
		setColumnNumber(INITIAL_CELL);
		setMaxColumn(INITIAL_CELL);
		Integer dateComparator = 1;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern(HOUR_FORMAT);
		
		
		// Armazena a linha do cabeçalho
		setRowNumber(sheet.getLastRowNum());
		
		// Cabeçalho
		HSSFRow row = sheet.createRow(sheet.getLastRowNum());
		Cell cell = row.createCell(getColumnNumber());
		cell.setCellValue(COLUMN_DATE);
		cell = row.createCell(getColumnNumber() + 1);
		cell.setCellValue(COLUMN_HOUR + "1");
		cell = row.createCell(getColumnNumber() + 2);
		cell.setCellValue(COLUMN_HOUR + "2");
		cell = row.createCell(getColumnNumber() + 3);
		cell.setCellValue(COLUMN_HOUR + "3");
		cell = row.createCell(getColumnNumber() + 4);
		cell.setCellValue(COLUMN_HOUR + "4");
		setMaxColumn(getColumnNumber()  + 4);
		
		// Loop para cada entrada de hora de cada colaborador
		for (LocalDateTime hora : collaborator.getTimetable()) {
			
			if (hora.getDayOfYear() != dateComparator) {
				setColumnNumber(INITIAL_CELL);
				
				// Se a data mudou, cria uma nova linha
				row = sheet.createRow(sheet.getLastRowNum() + 1);
				// Preenche data
				cell = row.createCell(getColumnNumber());
				cell.setCellValue(hora.format(dateFormatter));
				
				// Preenche entrada de hora
				cell = row.createCell(getColumnNumber() + 1);
				cell.setCellValue(hora.format(hourFormatter));

			} else {
			
				// Se a data não mudou, inclui hora na proxima coluna
				cell = row.createCell(getColumnNumber() + 1);
				cell.setCellValue(hora.format(hourFormatter));
			}
			
			setColumnNumber(getColumnNumber() + 1);
			
			// Inclui cabeçalho para marcações além do limite padrão (4)
			if (getColumnNumber() > getMaxColumn()) {
				row = sheet.getRow(getRowNumber());
				cell = row.createCell(getMaxColumn() + 1);
				cell.setCellValue(COLUMN_HOUR + (getMaxColumn() + 1));
				
				// Retorna para a ultima linha e celula para continuar preenchimento
				setMaxColumn(getMaxColumn() + 1);
				row = sheet.getRow(sheet.getLastRowNum());
			}
			
			dateComparator = hora.getDayOfYear();
		}
		
	}

	public String generateFileName() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_HOUR_FORMAT, Locale.getDefault());
		Date today = new Date();
		String fileName = SHEET_TITLE + format.format(today) + FILE_EXTENSION;

		return fileName;
	}

	public void saveFile(String fileName) throws IOException {
		FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "\\Documents\\" + fileName);
		getWorkbook().write(out);
		out.close();
	}

	/**
	 * @return the workbook
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook
	 *            the workbook to set
	 */
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * @return the maxColumn
	 */
	private Integer getMaxColumn() {
		return maxColumn;
	}

	/**
	 * @param maxColumn
	 *            the maxColumn to set
	 */
	private void setMaxColumn(Integer maxColumn) {
		this.maxColumn = maxColumn;
	}

	/**
	 * @return the columnNumber
	 */
	public Integer getColumnNumber() {
		return columnNumber;
	}

	/**
	 * @param columnNumber
	 *            the columnNumber to set
	 */
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}

	/**
	 * @return the rowNumber
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * @param rowNumber
	 *            the rowNumber to set
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public FileWriter(HSSFWorkbook workbook) {
		super();
		this.workbook = new HSSFWorkbook();
	}
}
