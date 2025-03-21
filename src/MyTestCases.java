import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTestCases {
	WebDriver driver = new ChromeDriver();
	String MyWebSite = "https://codenboxautomationlab.com/practice/";
	Random Rand = new Random();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions Action = new Actions(driver);

	@BeforeTest
	public void MySetUp() {
		driver.get(MyWebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, description = "RadioButton", invocationCount = 5)
	public void Radio_Button_Example() throws InterruptedException {
		List<WebElement> AllRadioButtons = driver.findElements(By.className("radioButton"));
		Thread.sleep(1000);
		int Randominput = Rand.nextInt(AllRadioButtons.size());
		AllRadioButtons.get(Randominput).click();
		boolean Expected = true;
		boolean Actual = AllRadioButtons.get(Randominput).isSelected();
		Assert.assertEquals(Actual, Expected);

	}

	@Test(priority = 2, description = "DropDown-Dynamic")
	public void Dynamic_Dropdown_Example() throws InterruptedException {
		String[] CountryCode = { "JO", "QA", "BR", "US" };
		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));
		int RandomInput = Rand.nextInt(CountryCode.length);
		DynamicListInput.sendKeys(CountryCode[RandomInput]);
		Thread.sleep(1000);
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN), Keys.ENTER);
		boolean Expected = true;
		String DataInSideMyInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);
		String UpdatedDataInMyInput = DataInSideMyInput.toLowerCase();
		boolean Actual = UpdatedDataInMyInput.contains(CountryCode[RandomInput].toLowerCase());
		Assert.assertEquals(Actual, Expected);
		System.out.println(DataInSideMyInput);

	}

	@Test(priority = 3, description = "StaticDropDownList")
	public void Static_Dropdown_Example() {
		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));
		Select Sel = new Select(SelectElement);
		Sel.selectByIndex(1);
		// Sel.selectByValue("option2");
		// Sel.selectByVisibleText("API");

	}

	@Test(priority = 4, description = "checkbox")
	public void Checkbox_Example() {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int RandomIndex = Rand.nextInt(checkboxes.size());
		checkboxes.get(RandomIndex).click();
		boolean Expected = true;
		boolean Actual = checkboxes.get(RandomIndex).isSelected();
		Assert.assertEquals(Actual, Expected);
		// to select them all
		// for(int i=0;i<checkboxes.size();i++){
		// checkboxes.get(i).click();}

	}

	@Test(priority = 5, description = "This is to move from window to another one")
	public void Switch_Window_Example() throws InterruptedException {
		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();
		Thread.sleep(2000);
		List<String> windowHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandels.get(1));
		WebElement contactButton = driver.findElement(By.id("menu-item-9680"));
		contactButton.click();
		System.out.println(driver.getTitle());
	}

	@Test(priority = 6, description = "Check moving to another tab")
	public void Switch_Tab_Example() {
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));
		OpenTabButton.click();
		List<String> windowHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandels.get(1));
		System.out.println(driver.getTitle());
	}

	@Test(priority = 7, description = "Alert and confirm")
	public void Switch_To_Alert_Example() throws InterruptedException {
		WebElement NameBox = driver.findElement(By.id("name"));
		NameBox.sendKeys("Noor");
		Thread.sleep(1000);
		// WebElement AlertButton=driver.findElement(By.id("alertbtn"));
		// AlertButton.click();
		// driver.switchTo().alert().accept();
		WebElement ConfirmButton = driver.findElement(By.id("confirmbtn"));
		ConfirmButton.click();
		driver.switchTo().alert().accept();

	}

	@Test(priority = 8, description = "Play with the data of the column")
	public void Web_Table_Example() {
		WebElement TheTable = driver.findElement(By.id("product"));
		List<WebElement> TheDataInSideTheTable = driver.findElements(By.tagName("tr"));
		for (int i = 1; i < TheDataInSideTheTable.size(); i++) {
			int TotalTdInTheRow = TheDataInSideTheTable.get(i).findElements(By.tagName("td")).size();
			System.out.println(
					TheDataInSideTheTable.get(i).findElements(By.tagName("td")).get(TotalTdInTheRow - 1).getText());
		}

	}

	@Test(priority = 9, description = "This is to test hide and show buttons")
	public void Element_Displayed_Example() throws InterruptedException {
		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0,1500)");
		HideButton.click();
		WebElement DisplayedText = driver.findElement(By.id("displayed-text"));
		boolean Actual = DisplayedText.isDisplayed();
		boolean Expected = false;
		Assert.assertEquals(Actual, Expected);
		ShowButton.click();
		Assert.assertEquals(DisplayedText.isDisplayed(), true);
	}

	@Test(priority = 10, description = "Chek the both Buttons disable,enable")
	public void Enabled_Disabled_Example() throws InterruptedException {
		js.executeScript("window.scrollTo(0,1800)");
		WebElement DisabledButton = driver.findElement(By.id("disabled-button"));
		WebElement EnabledButton = driver.findElement(By.id("enabled-button"));
		DisabledButton.click();
		WebElement EnabledInput = driver.findElement(By.id("enabled-example-input"));
		boolean Expected = false;
		boolean Actual = EnabledInput.isEnabled();
		Assert.assertEquals(Actual, Expected);
		Thread.sleep(1000);
		EnabledButton.click();
		boolean Expected2 = true;
		boolean Actual2 = EnabledInput.isEnabled();
		Assert.assertEquals(Actual2, Expected2);
		EnabledInput.sendKeys("Noor");
	}

	@Test(priority = 11, description = "Check the hover to certain element")
	public void Mouse_Hover_Example() {
		js.executeScript("window.scrollTo(0,1800)");
		WebElement MouseHoverElement = driver.findElement(By.id("mousehover"));
		Action.moveToElement(MouseHoverElement).perform();
		// driver.findElement(By.linkText("Top")).click();
		driver.findElement(By.partialLinkText("Relo")).click();
	}

	@Test(priority = 12, description = "Open calendar in anew tab")
	public void Calendar_Example() {
		js.executeScript("window.scrollTo(0,1900)");
		WebElement Calendar = driver.findElement(By.linkText("Booking Calendar"));
		Calendar.click();
		List<String> windowHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandels.get(1));
		System.out.println(driver.findElements(By.className("date_available")).size());
		int TotalAvailableDates = driver.findElements(By.className("date_available")).size();
		driver.findElements(By.className("date_available")).get(0).click();
		driver.findElements(By.className("date_available")).get(TotalAvailableDates - 1).click();
	}

	@Test(priority = 13, description = "Switch to frame inside the main page")
	public void iFrame_Example() {
		WebElement TheFrame = driver.findElement(By.id("courses-iframe"));
		// by index
		driver.switchTo().frame(0);
		// by id
		driver.switchTo().frame("courses-iframe");
		// by webelement
		driver.switchTo().frame(TheFrame);

		String theText = driver.findElement(By.xpath("//*[@id=\"ct_text_editor-be8c5ad\"]/div/div/p")).getText();

		System.out.println(theText);

	}

	@Test(priority = 14, description = "Download the file")
	public void Download_file_to_test() {
		WebElement Thefile = driver.findElement(By.cssSelector(".wp-block-button__link.wp-element-button"));
		Thefile.click();
	}

}
