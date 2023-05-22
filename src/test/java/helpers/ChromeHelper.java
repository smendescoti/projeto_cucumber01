package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeHelper {

	public static WebDriver create(String url) {

		System.setProperty("webdriver.chrome.driver", "C:\\workspace_testes\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();

		return driver;
	}
}
