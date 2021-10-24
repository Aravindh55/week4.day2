package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		Actions builder = new Actions(driver);
		driver.get("https://jqueryui.com/selectable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement element = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(element);
		WebElement element1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement findElement = driver.findElement(By.xpath("//li[text()='Item 5']"));
		builder.clickAndHold(element1).moveToElement(findElement).release().perform();

	}

}
