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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		Actions builder = new Actions(driver);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement findElement = driver.findElement(By.xpath("(//div[@class='desktop-navLink']/a)[1]"));
		builder.moveToElement(findElement).perform();
		driver.findElement(By.xpath("(//li[@class='desktop-oddColumnContent']//a)[7]")).click();
		String text1 = driver.findElement(By.xpath("//span[@class='title-count']")).getText().replaceAll("\\D", "");
		Integer jacketCount = Integer.parseInt(text1);
		String text2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText().replaceAll("\\D","");
		Integer jackets = Integer.parseInt(text2);
		String text3 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText().replaceAll("\\D","");
		Integer rainJackets = Integer.parseInt(text3);
		Integer sumCategories = jackets + rainJackets;
		if (sumCategories.equals(jacketCount)) {
			System.out.println("the sum of categories count matches the total count of item");
		} else {
			System.out.println("count does not match");
		}
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[70]")).click();
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		List<String> jacketList=new ArrayList<String>();
		for (WebElement webElement : elements) {
			String text = webElement.getText();
			jacketList.add(text);
		}
		int count=0;
        for (int i = 0; i < jacketList.size(); i++) {
        	if (!jacketList.get(i).equals("Duke")) {
        		count++;
        }
       
		}
        if (count==0) {
			System.out.println("all the coats are of brand duke");
		}else {
			System.out.println("the coat brands are different");
	}
        driver.findElement(By.xpath("//div[@class='sort-sortBy']")).click();
        driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();
        String text = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText().replaceAll("\\D","");
        System.out.println("the price of the product is Rs."+text);
        driver.findElement(By.xpath("(//li[@class='product-base']/a)[1]")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> wList=new ArrayList<String>(windowHandles);
        driver.switchTo().window(wList.get(1));
        WebElement findElement2 = driver.findElement(By.xpath("//div[@class='image-grid-container common-clearfix']"));
        File a = findElement2.getScreenshotAs(OutputType.FILE);
        File b=new File("./snaps/jacket.png");
        FileUtils.copyFile(a, b);
        driver.findElement(By.xpath("//span[text()='Wishlist']")).click();
        driver.quit();
        
        
        }

}
