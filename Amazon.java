package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		Actions builder = new Actions(driver);
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro ");
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@class='a-declarative']//i)[1]")).click();
	    String text = driver.findElement(By.xpath("(//table[@id='histogramTable']//tr//span)[4]/a")).getText();
	    System.out.println("the percentage of ratings for 5star is "+text);
	    driver.findElement(By.xpath("(//div[@class='a-section a-spacing-none']//a)[2]")).click();
	    Set<String> windowHandles = driver.getWindowHandles();
	    List<String> lwin=new ArrayList<String>(windowHandles);
	    driver.switchTo().window(lwin.get(1));
	    WebElement findElement = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
	    File a = findElement.getScreenshotAs(OutputType.FILE);
	    File b=new File("./snaps/mobile.png");
	    FileUtils.copyFile(a, b);
	    String price = driver.findElement(By.id("priceblock_dealprice")).getText();
	    driver.findElement(By.id("add-to-cart-button")).click();
	    Thread.sleep(2000);
	    String price2 = driver.findElement(By.xpath("(//div[@class='a-column a-span11 a-text-left a-spacing-top-large']//span)[3]")).getText();
	    if (price.equals(price2)) {
	    	System.out.println("the subtotal is verified");
			
		}else {
			System.out.println("the subtotal is different from the price");
		}
	    
	}

}
