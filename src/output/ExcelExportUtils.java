/**
 * 
 */
package output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

import org.apache.commons.lang.SystemUtils;
import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Utilitários para exportação de Excel
 * @author thiago.lima
 *
 */
public class ExcelExportUtils {
	private static final String USER_DIR = "user.dir";
	private static final String EXCEL_DEFAULT = "/Ponto_GW_ANS.xls";

	/**
	 * Gera um nome de arquivo a partir da data e hora corrente
	 * @return
	 */
	public static String generateFileName(String dateFormat, String fileTitle, String fileExtension) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
		Date today = new Date();
		String fileName = fileTitle + format.format(today) + fileExtension;

		return fileName;
	}
	
	/**
	 * Cria estilo para células de cabeçalho
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle createHeaderCellStyle(HSSFWorkbook workbook) {
		
		HSSFCellStyle style = createCellStyle(workbook);		
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		
		return style;
	}
	
	/**
	 * Cria estilo para células normais
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderLeft(HSSFBorderFormatting.BORDER_NONE);
		style.setBorderRight(HSSFBorderFormatting.BORDER_NONE);
		style.setBorderTop(HSSFBorderFormatting.BORDER_NONE);
		style.setBorderBottom(HSSFBorderFormatting.BORDER_NONE);
		
		return style;
	}
	
	/**
	 * Salva a planilha na pasta indicada
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveFile(HSSFWorkbook workbook, String filePath) throws IOException {
		FileOutputStream out = new FileOutputStream(filePath);
		workbook.write(out);
		out.close();
	}

	public static HSSFWorkbook readDefaultWorkbook() throws IOException {
		String filename = System.getProperty(USER_DIR) + EXCEL_DEFAULT;
		FileInputStream inputStream = new FileInputStream(new File(filename));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
				
		return workbook;
	}
}
