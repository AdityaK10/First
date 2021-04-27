package Requirements;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;

public class functions {

	private WebDriver driver;
	
	//Starting the driver
	public  void driverSetup() throws Exception {
				
	ReadExcel.setExcelFile();
	String browser=ReadExcel.getCellData(0, 1);
				  	  
				
	//Setting up Chrome WebDriver 
	if(browser.equalsIgnoreCase("chrome")) { 
	System.setProperty("webdriver.chrome.driver",ReadExcel.getCellData(4, 1)); 
	driver=new ChromeDriver(); 
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
	}
				  	
	//Setting up Opera WebDriver 
	else if(browser.equalsIgnoreCase("opera")) {
	System.setProperty("webdriver.opera.driver",ReadExcel.getCellData(5, 1)); 
	driver=new OperaDriver();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
	}
				  
	//Setting up Firefox WebDriver
	else if(browser.equalsIgnoreCase("firefox")) {
	System.setProperty("webdriver.gecko.driver",ReadExcel.getCellData(6, 1)); 
	driver=new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
	}
							
	//If entered incorrect browser 
	else {
	System.out.println(ReadExcel.getCellData(7, 1)); 
	System.exit(0); 
	}
				 
	String baseURL=ReadExcel.getCellData(1, 1);
	driver.manage().window().maximize(); //maximizing window
	driver.get(baseURL); 				 //going to "https://demoqa.com/alerts"
	}
			
	//Handling Alert
	public void alertHandling() throws Exception
	{
		WebElement simple = driver.findElement(By.id("alertButton"));
	    simple.click();
	    Alert abc= driver.switchTo().alert();  
	    String text=abc.getText();
	    System.out.println("Alert text is : " +text);
	    Thread.sleep(2000);
	    abc.accept();
	    Thread.sleep(2000);
	}
	
	//Performing slider Drag and Drop
	public void sliderDragAndDrop() throws Exception
	{
		 ReadExcel.setExcelFile();
		 driver.get(ReadExcel.getCellData(2, 1));
	     WebElement slider=driver.findElement(By.xpath("//input[@style='--value:25;'][1]"));
	     Thread.sleep(2000);
	     Actions move=new Actions(driver);
	     move.dragAndDropBy(slider, 90, 0).build().perform();
	     Thread.sleep(2000);
	     System.out.println("Slider handled successfully!");
	}
	
	//Handling multiple windows
	public void handleMultipleWindows() throws InterruptedException
	{
		driver.get(ReadExcel.getCellData(3, 1));
	    driver.findElement(By.id("windowButton")).click();
	    String parentWindowHandle = driver.getWindowHandle();
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    Iterator<String> iterator = allWindowHandles.iterator();
	    while (iterator.hasNext()) {
	    	String ChildWindow = iterator.next();
	    	if (!parentWindowHandle.equalsIgnoreCase(ChildWindow)) {
	                driver.switchTo().window(ChildWindow);
	                Thread.sleep(2000);
	                WebElement text1 = driver.findElement(By.id("sampleHeading"));
	                System.out.println("Heading of child window is : " + text1.getText());
	            	}
	    }
	    driver.quit();
	}
	
}
