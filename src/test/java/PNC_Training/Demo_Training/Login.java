package PNC_Training.Demo_Training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPageObject;
import utility.WebDriverFactory;

public class Login {

	private WebDriver driver;

	@Test (dataProvider="usernameandpassword", description="Test Data", priority=2)
	public void login1() {
		driver.findElement(By.linkText("sign-in")).click();
		driver.findElement(By.name("userName")).sendKeys("Sambit");
		driver.findElement(By.name("password")).sendKeys("asd");
		driver.findElement(By.name("login")).click();
		Assert.assertEquals("Find a Flight", driver.getTitle());
	}
	
	
	@Test(priority = 1)
	public void login(String username, String password) {
				/*driver.findElement(By.name("userName")).clear();
				driver.findElement(By.name("userName")).sendKeys("invalidUN");
				driver.findElement(By.name("password")).clear();
				driver.findElement(By.name("password")).sendKeys("invalidPN");
				driver.findElement(By.name("login")).click();*/

		driver.findElement(By.linkText("REGISTER")).click();

		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		//Thread.sleep(6000);
		//driver.findElement(By.name("firstName")).sendKeys("Swadha");
		LoginPageObject.firstName.sendKeys("Swadha");
		driver.findElement(By.name("lastName")).sendKeys("Gupta");
		driver.findElement(By.name("phone")).sendKeys("9891003916");

		//LoginPageObject.uname.sendKeys("swadha07");

		driver.findElement(By.id("userName")).sendKeys("swadha07");
		driver.findElement(By.cssSelector("input[name='address1']")).sendKeys("Fursungi");
		driver.findElement(By.cssSelector("input[name='city']")).sendKeys("Pune");

		//***Drop Down
		Select country = new Select(driver.findElement(By.name("country")));
		country.selectByVisibleText("INDIA");

		driver.findElement(By.id("email")).sendKeys("swadha07");
		driver.findElement(By.name("password")).sendKeys("swadha07");
		driver.findElement(By.name("confirmPassword")).sendKeys("swadha07");

		driver.findElement(By.name("register")).click();
/*
		WebElement textValue = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/a/font/b"));
		String text = textValue.getText();
		Assert.assertEquals("Swadha", text.startsWith("Swadha"));*/
	}
	
	@DataProvider(name="usernameandpassword")
	public String[][] credentials(){
		return new String[][] {
			new String[] {"inavalidUN","invalidPW"},
			new String[] {"swadha07","swadha07"}
		};
	}
	
	@BeforeTest
	public void beforeTest() {
		/*System.setProperty("webdriver.chrome.driver","C:\\Users\\pdc4-training.pdc4\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();*/
		driver = WebDriverFactory.createDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://newtours.demoaut.com/");
		PageFactory.initElements(driver,LoginPageObject.class);
	}



	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
