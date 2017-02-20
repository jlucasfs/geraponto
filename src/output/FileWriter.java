/**
 * 
 */
package output;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

import model.Collaborator;

/**
 * @author thiago.lima
 *
 */
public class FileWriter {

	private static final Integer INITIAL_CELL = 0;
	private static final Integer INITIAL_ROW = 0;
	private static final String WORKBOOK_TITLE = "Folha de Ponto ";
	private static final String COLUMN_PIS = "PIS:";
	private static final String COLUMN_NAME = "Nome:";
	private static final String COLUMN_DATE = "Data";
	private static final String COLUMN_HOUR = "Hora ";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String HOUR_FORMAT = "HH:mm";
	private static final String DATE_HOUR_FORMAT = "dd-MM-yy HHmm";
	private static final String BLANK_SPACE = " ";
	private static final String USER_HOME = "user.home";
	private static final String DOCUMENTS_FOLDER = "//Documents//";
	private static final String FILE_EXTENSION = ".xls";

	private HSSFWorkbook workbook;
	private HSSFCellStyle cellStyle;
	private HSSFCellStyle headerStyle;
	private Integer maxColumn;
	private Integer columnNumber;
	private Integer rowNumber;

	public FileWriter() {
		super();
		this.workbook = new HSSFWorkbook();
		this.cellStyle = ExcelExportUtils.createCellStyle(workbook);
		this.headerStyle = ExcelExportUtils.createHeaderCellStyle(workbook);
		this.columnNumber = INITIAL_CELL;
		this.rowNumber = INITIAL_ROW;
	}

	/**
	 * Processa a lista de colaboradores e horários
	 * 
	 * @param collabList
	 */
	public void processFile(HashSet<Collaborator> collabList, ReportType type) {
		
		try {
			HSSFSheet sheet;
			
			switch (type) {
			case RAW:
				setWorkbook(ExcelExportUtils.readDefaultWorkbook());
				sheet = getWorkbook().getSheet("Dados");
				createRawSheet(sheet, collabList);
				break;
				
			case CONSOLIDATED:
				for (Collaborator collaborator : collabList) {
					
					// Preenche o titulo da pasta com o primeiro e o ultimo nome do colaborador
					String[] sheetTitle = collaborator.getName().split(BLANK_SPACE);
					sheet = getWorkbook().createSheet(sheetTitle[0] + BLANK_SPACE + sheetTitle[sheetTitle.length - 1]);
					
					createSheet(sheet, collaborator);
				}
				
				break;
			}
	
			// Cria nome do arquivo e filepath
			String fileName = ExcelExportUtils.generateFileName(DATE_HOUR_FORMAT, WORKBOOK_TITLE, FILE_EXTENSION);
			String filepath = System.getProperty(USER_HOME) + DOCUMENTS_FOLDER + fileName;
	
				ExcelExportUtils.saveFile(getWorkbook(), filepath);

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
		
		// Ajusta largura da coluna ao conteúdo das células
		for (int i=0; i <= sheet.getLastRowNum(); i++) {
			sheet.autoSizeColumn(i);
		}
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
		cell.setCellValue(COLUMN_NAME);
		cell.setCellStyle(getHeaderStyle());
		cell = row.createCell(INITIAL_CELL + 1);
		cell.setCellValue(collaborator.getName());
		cell.setCellStyle(getHeaderStyle());
		
		row = sheet.createRow(sheet.getLastRowNum() + 1);
		cell = row.createCell(INITIAL_CELL);
		cell.setCellValue(COLUMN_PIS);
		cell.setCellStyle(getHeaderStyle());
		cell = row.createCell(INITIAL_CELL + 1);
		cell.setCellValue(collaborator.getPis());
		cell.setCellStyle(getHeaderStyle());
		
		// Merge de celulas do cabeçalho
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 7));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7));
		
		// Insere uma linha vazia para separação
		row = sheet.createRow(sheet.getLastRowNum() + 2);

	}
	
	/**
	 * Cria planilha unica com marcações de todos os colaboradores
	 * @param sheet
	 * @param collabList
	 */
	public void createRawSheet(HSSFSheet sheet, HashSet<Collaborator> collabList) {
		int cellCount = 1;
		int rowCount = 2;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern(HOUR_FORMAT);
		
		HSSFRow row = sheet.getRow(rowCount);
		Cell cell = row.getCell(cellCount);
				
		for (Collaborator collaborator : collabList) {
			LinkedHashMap<LocalDate, LinkedHashSet<LocalTime>> dateMap = collaborator.getTimeTableByDay();
			
			for (HashMap.Entry<LocalDate, LinkedHashSet<LocalTime>> entry : dateMap.entrySet()) {
				row = sheet.getRow(rowCount);
				if (row == null) {
					row = sheet.createRow(rowCount);
				}
				
				cell = row.createCell(cellCount++);
				cell.setCellValue(collaborator.getName());
				cell = row.createCell(cellCount++);
				cell.setCellValue(dateFormatter.format(entry.getKey()));
				
				for (LocalTime time : entry.getValue()) {
					
					cell = row.createCell(cellCount++);
					cell.setCellValue(hourFormatter.format(time));
				}
				cellCount = 1;
				rowCount++;
			}
		}
	}
	
	/**
	 * Inclui PIS e nome do colaborador em uma nova linha (planilha unica)
	 * @param sheet
	 * @param collaborator
	 */
	public void createRawInfo(HSSFSheet sheet, Collaborator collaborator) {
		HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
		HSSFCell cell = row.createCell(INITIAL_CELL);
		cell.setCellValue(collaborator.getPis().toString());
		cell = row.createCell((int) row.getLastCellNum());
		cell.setCellValue(collaborator.getName());
		cell = row.createCell((int) row.getLastCellNum());
		cell.setCellValue(collaborator.getName());
		
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
		cell.setCellStyle(getHeaderStyle());
		cell = row.createCell(getColumnNumber() + 1);
		cell.setCellValue(COLUMN_HOUR + "1");
		cell.setCellStyle(getHeaderStyle());
		cell = row.createCell(getColumnNumber() + 2);
		cell.setCellValue(COLUMN_HOUR + "2");
		cell.setCellStyle(getHeaderStyle());
		cell = row.createCell(getColumnNumber() + 3);
		cell.setCellValue(COLUMN_HOUR + "3");
		cell.setCellStyle(getHeaderStyle());
		cell = row.createCell(getColumnNumber() + 4);
		cell.setCellValue(COLUMN_HOUR + "4");
		cell.setCellStyle(getHeaderStyle());
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
				cell.setCellStyle(getCellStyle());
				
				// Preenche entrada de hora
				cell = row.createCell(getColumnNumber() + 1);
				cell.setCellValue(hora.format(hourFormatter));

			} else {
			
				// Se a data não mudou, inclui hora na proxima coluna
				cell = row.createCell(getColumnNumber() + 1);
				cell.setCellValue(hora.format(hourFormatter));
			}
			cell.setCellStyle(getCellStyle());
			
			setColumnNumber(getColumnNumber() + 1);
			
			// Inclui cabeçalho para marcações além do limite padrao (4)
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
	 * @return the cellStyle
	 */
	public HSSFCellStyle getCellStyle() {
		return cellStyle;
	}

	/**
	 * @param cellStyle the cellStyle to set
	 */
	public void setCellStyle(HSSFCellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}

	/**
	 * @return the headerStyle
	 */
	public HSSFCellStyle getHeaderStyle() {
		return headerStyle;
	}

	/**
	 * @param headerStyle the headerStyle to set
	 */
	public void setHeaderStyle(HSSFCellStyle headerStyle) {
		this.headerStyle = headerStyle;
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
