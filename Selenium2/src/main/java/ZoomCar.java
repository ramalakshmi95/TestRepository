import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZoomCar 
{
	public static ChromeDriver LaunchBrowser()
	{
		//Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Browser Launched");
		return driver;
	}
	
	public static void LaunchUrl(ChromeDriver driver, String URL,String Title)
	{
		//launch LEAFTAPS url
		driver.get(URL);
		driver.manage().window().maximize();
		if(checkTitle(driver, Title))
		System.out.println(URL+" is opened");
	}
	
	public static boolean checkTitle(ChromeDriver driver,String Title)
	{
		return driver.getTitle().contains(Title);
	}
	public static void main(String[] args) throws InterruptedException 
	{
		String URL="https://www.zoomcar.com/chennai";
		ChromeDriver driver=LaunchBrowser();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		LaunchUrl(driver,URL,"Zoomcar");
		System.out.println(driver.getTitle());
		
		WebElement SearchButton = driver.findElement(By.className("search"));
		SearchButton.click();
		WebElement PopularPickupPoints = driver.findElement(By.xpath("//div[text()='Popular Pick-up points']/following-sibling::div"));
		PopularPickupPoints.click();
		WebElement NextButton = driver.findElement(By.xpath("//button[text()='Next']"));
		NextButton.click();
		
		Date date=new Date();
		DateFormat dateformat=new SimpleDateFormat("dd");
		String todaydate= dateformat.format(date);
		int tomorrowdate=Integer.parseInt(todaydate)+1;
		
		WebElement nextDay = driver.findElement(By.xpath("//div[contains(text(),"+tomorrowdate+")]"));
		nextDay.click();
		WebElement NextButton1 = driver.findElement(By.xpath("//button[text()='Next']"));
		NextButton1.click();
		/*WebElement StartDate = driver.findElement(By.xpath("//div[@class='day picked ']"));
		String text = StartDate.getText();
		System.out.println("text"+text);
		*///int StartDate1= Integer.parseInt(text);
		//System.out.println("startdate"+StartDate1);
		/*boolean checkStartDate=text.contains(todaydate);
		System.out.println(checkStartDate);
		if(checkStartDate)
		{
			WebElement DoneButton = driver.findElement(By.xpath("//button[text()='Done']"));
			DoneButton.click();
		}
		else
		{
			System.out.println("Done not clicked");
		}*/
		Thread.sleep(2000);
		WebElement DoneButton = driver.findElement(By.xpath("//button[text()='Done']"));
		DoneButton.click();
		List<WebElement> CarList = driver.findElementsByXPath("//div[@class='car-listing']");
		System.out.println(CarList.size());
		List<WebElement> CarPriceList =driver.findElements(By.xpath("//div[@class='price']"));
		driver.findElements(By.xpath("//div[@class='price']"));
		List<String> priceList=new ArrayList<String>();
		int HighestPrice=0;
		int count=0;
		int max=1;
		for(WebElement ele:CarPriceList)
		{
			++count;
			String Price=ele.getText();
			String Price1 = Price.replaceAll("\\D","");
			int price2=Integer.parseInt(Price1);
			if(HighestPrice<price2)
			{
				HighestPrice=price2;
				max=count;
			}
		}
		System.out.println(HighestPrice);
		System.out.println(max);
		List<WebElement> findElements1 = driver.findElements(By.xpath("//div/h3"));
		String carNamemax = findElements1.get(max-1).getText();
		System.out.println(carNamemax);
		driver.findElement(By.xpath("//button[text()='BOOK NOW']")).click();
		Thread.sleep(5000);
		
		
		/*Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Price: High to Low')]")).click();
		WebElement HighestPricedCar = driver.findElement(By.xpath("(//div[@class='car-listing'])[1]"));
		String Price = HighestPricedCar.findElement(By.xpath("//div[@class='price']")).getText();
		String carName = HighestPricedCar.findElement(By.xpath("//div[@class='details']/h3")).getText();
		System.out.println(Price);
		System.out.println(carName);
		driver.findElement(By.xpath("//button[text()='BOOK NOW']")).click();
		Thread.sleep(5000);*/
		//driver.close();
		
		
	}
}