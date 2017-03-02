package input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scrap {

	private static WebDriver driver;
	private static String baseUrl;
	private static String fileName;
	static final String DIR = System.getProperty("user.home") + "\\Downloads";

	public static String getFullFile() {
		System.setProperty("chrome.binary.path", System.getProperty("user.dir") + "\\chromedriver.exe");

		driver = new ChromeDriver();
		try {
			

			efetuaLogin(driver);

			Thread.sleep(4000);

			deletaArquivos();

			driver.findElement(By.cssSelector("span.sprt.menuitem-downld")).click();
			driver.findElement(By.cssSelector("a.linkDownload")).click();
			driver.findElement(By.cssSelector("span.sprt.menuitem-ext")).click();
			Thread.sleep(1000);
			while (textFiles(DIR).size() > 0) {
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			driver.close();
		}
		return fileName;
	}

	private static void efetuaLogin(WebDriver driver) {
		baseUrl = "http://10.19.16.80/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.id("lblPass")).sendKeys("084959");
		driver.findElement(By.id("lblLogin")).sendKeys("Igor");
		driver.findElement(By.id("lblPass")).sendKeys(Keys.ENTER);
	}

	private static void deletaArquivos() {
		File folder = new File(DIR);
		File fList[] = folder.listFiles();
		for (int i = 0; i < fList.length; i++) {
			String pes = fList[i].getName();
			if (pes.endsWith(".crdownload") || pes.endsWith(".tmp")) {
				// and deletes
				fList[i].delete();
			}
		}

	}

	static List<String> textFiles(String directory) {
		List<String> textFiles = new ArrayList<String>();
		File dir = new File(directory);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((".crdownload")) || file.getName().endsWith((".temp"))) {
				fileName = file.getName();
				textFiles.add(fileName);
			}
		}
		return textFiles;
	}
}
