/**
 * 
 */
package output;

import java.util.List;

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
	private static final String SHEET_TITLE = "Folha de Ponto";
	private static final String COLUMN_PIS = "PIS";
	private static final String COLUMN_NAME = "Nome";
	private static final String COLUMN_DATE = "Data";
	private static final String COLUMN_HOUR = "Hora ";
	private static final String[] COLUMN_LIST = {COLUMN_PIS, COLUMN_NAME, COLUMN_DATE,
			COLUMN_HOUR + "1", COLUMN_HOUR + "2", COLUMN_HOUR + "3", COLUMN_HOUR + "4"};
		
	
	private HSSFWorkbook workbook;
	private Integer maxColumn;
	
	
	public FileWriter(HSSFWorkbook workbook) {
		super();
		this.workbook = new HSSFWorkbook();
	}
	
	public HSSFSheet processFile(List<Collaborator> collabList) {

		HSSFSheet sheet = getWorkbook().createSheet(SHEET_TITLE);
		createHeader(sheet);
		for (Collaborator collaborator : collabList) {
			
			createLign(sheet, collaborator);
		}
		
		return sheet;
	}
	
	public void createHeader(HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(INITIAL_ROW);
		
		for (String column : COLUMN_LIST) {
			Cell cell = row.createCell(INITIAL_CELL);
			cell.setCellValue(column);
		}
		
	}
	
	public void createLign(HSSFSheet sheet, Collaborator collaborator) {
		HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1); 
		
		
	}

	/**
	 * @return the workbook
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook the workbook to set
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
	 * @param maxColumn the maxColumn to set
	 */
	private void setMaxColumn(Integer maxColumn) {
		this.maxColumn = maxColumn;
	}
}
