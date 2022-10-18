package AkarCorp.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AkarCorp.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\AkarCorp\\Resources\\GlobalData.properties");
		prop.load(fis);
		//check if browser is passed through maven or through system property
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

		//String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Please select a valid browser");
		}
		return driver;

	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws InterruptedException{
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//convert json to string by sending the file to FileUtils
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to Hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
	
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotName=System.getProperty("user.dir") + "\\TestReports\\" + testCaseName + ".png";
		File file = new File(screenshotName);
		FileUtils.copyFile(source, file);
		return screenshotName;

	}
	
	

}
