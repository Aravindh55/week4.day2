package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		Actions builder = new Actions(driver);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement findElement = driver.findElement(By.xpath("(//span[@class='catText'])[6]"));
		builder.moveToElement(findElement).perform();
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		String text = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("the total shoe count is " + text);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		Thread.sleep(2000);
		List<WebElement> findElements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> shoeprice = new ArrayList<Integer>();
		List<Integer> shoeprice1 = new ArrayList<Integer>();
		int count = 0;
		for (WebElement webElement : findElements) {
			String text2 = webElement.getAttribute("display-price");
			int prices = Integer.parseInt(text2);
			shoeprice.add(prices);
			shoeprice1.add(prices);
		}
		Collections.sort(shoeprice);
		for (int i = 0; i < shoeprice.size(); i++) {
			if (!shoeprice.get(i).equals(shoeprice1.get(i))) {
				count++;
			}
		}
		if (count > 5) {
			System.out.println("the elements are sorted correctly");

		} else {
			System.out.println("the elements are not sorted correctly");
		}
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(2000);
		WebElement findElement2 = driver.findElement(By.xpath("//div[@class='product-tuple-description ']"));
		Thread.sleep(2000);
		builder.moveToElement(findElement2).perform();
		driver.findElement(By.xpath("//div[@class='center quick-view-bar  btn btn-theme-secondary  ']")).click();
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText().replaceAll("\\D", "");
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText().replaceAll("[a-z,A-Z]", "");
		System.out.println("the cost is " + cost);
		System.out.println("the discount is " + discount);
		WebElement findElement3 = driver.findElement(By.xpath("(//div[@class='comp-quickview']//div)[9]"));
		File a = findElement3.getScreenshotAs(OutputType.FILE);
		File b = new File("./snaps/shoe.png");
		FileUtils.copyFile(a, b);
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		//driver.close();

	}

}
