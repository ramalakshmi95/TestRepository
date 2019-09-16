
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FaceBook 
{
	public static void main(String[] args) throws InterruptedException 
	{	
		String username="jayashrishankaran04@gmail.com";
		String password="RamsVasantha01*";
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		System.out.println("chrome notifications disabled");
		System.setProperty("webdriver.chrome.driver", "./drivers/Chromedriver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//	driver.manage().window().maximize();
		driver.get("https://www.facebook.com");
		System.out.println("Launching facebook");
		WebDriverWait wait=new WebDriverWait(driver,15);
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		System.out.println("Login completed");
		System.out.println("searching TestLeaf page");
		driver.findElement(By.name("q")).sendKeys("TestLeaf");
		System.out.println("click search button");
		WebElement searchbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='facebar_search_button']")));
		searchbutton.click();
		List<WebElement> findElements = driver.findElements(By.xpath("(//a[@role='tab'])"));
		System.out.println(findElements.size());
		findElements.get(6).click();
		List<WebElement> findElements1 = driver.findElements(By.xpath("(//a[@role='tab'])[7]"));
		System.out.println(findElements1.size());
		findElements1.get(0).click();
		System.out.println("checking for places tab");
		WebElement PlacesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@role='tab']//div[text()='Places']")));
		//System.out.println(PlacesTab.getText());
		PlacesTab.click();
			System.out.println("searching test leaf page");
			WebDriverWait wait1=new WebDriverWait(driver,15);
			WebElement TestLeafLink=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("TestLeaf")));
			boolean displayed = TestLeafLink.isDisplayed();
			if(displayed)
			{
				System.out.println("searching test leaf page like button");
				WebDriverWait wait2=new WebDriverWait(driver,15);
				WebElement TestLeafLikeButton = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='TestLeaf']/preceding::button[contains(@data-testid,'like')]")));
				String LikeButtonText = TestLeafLikeButton.getText();
				if(LikeButtonText.equals("Like"))
				{
					TestLeafLikeButton.click();
					if(driver.findElement(By.xpath("(//a[contains(text(),'Close')])[2]")).isDisplayed())
					{
						driver.findElement(By.xpath("(//a[contains(text(),'Close')])[2]")).click();
						System.out.println("PAge not liked due to error");
					}
					else
					{
						System.out.println("TestLeaf Page liked");
					}
				}
			else
			{
				System.out.println("TestLeaf Page is already liked");
			}
			WebDriverWait wait3=new WebDriverWait(driver,15);
			while(true)
			{
				try
				{
					wait3.until(ExpectedConditions.visibilityOf(TestLeafLink)).click();
					break;
				}
				catch(Exception e)
				{System.out.println("retrying to click link");
					continue;
				}
			}
			String PageTitle = driver.getTitle();
			boolean PageTitleVerify = PageTitle.contains("TestLeaf");
			if(PageTitleVerify)
			{
				System.out.println("TestLeaf Page loaded successfully");
				WebElement LikeCount = driver.findElement(By.xpath("//div[contains(text(),'people like this')]"));
				String LikeCountText = LikeCount.getText();
				String count = LikeCountText.replaceAll("\\D", "");
				int likes=Integer.parseInt(count);			
				System.out.println(LikeCountText);
				System.out.println("Number of likes:"+likes);
			}
			else
			{
				System.out.println("TestLeaf Page has not loaded");
			}
		}
	}
}
