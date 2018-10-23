package netwrix.winlog;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WinLogWiniumTestCase {
    static WiniumDriver driver = null;
    private static DesktopOptions options = null;
    private static String appPath = "C:\\Users\\user\\Desktop\\WindowsLogViewer.exe";
    ProcessBuilder pro = null;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        options = new DesktopOptions();
        options.setApplicationPath(appPath);
        options.setDebugConnectToRunningApp(false);
        options.setLaunchDelay(5);
        driver = new WiniumDriver(new URL("http://localhost:9999"),options);
    }

    @Test
    public void test() throws InterruptedException {
        //Check app is open
        WebElement window = driver.findElement(By.name("win log viewer"));
        assertTrue(window.isDisplayed());

        //get buttons which open list in menu
        List<WebElement> buttonsList = driver.findElements(By.name("Кнопка раскрытия списка"));
        assertEquals(2, buttonsList.size());

        //button 1 is visible
        assertTrue(buttonsList.get(1).isDisplayed());
        buttonsList.get(1).click();

        //list is opened & choose "Application"
        WebElement applicationPoint = driver.findElement(By.name("Application"));
        assertTrue(applicationPoint.isDisplayed());
        applicationPoint.click();

        //button 2 is visible
        assertTrue(buttonsList.get(0).isDisplayed());
        buttonsList.get(0).click();

        //list is opened & choose "Last hour"
        WebElement last12hour = driver.findElement(By.name("Last 12 hours"));
        assertTrue(last12hour.isDisplayed());
        last12hour.click();

        //Press refresh button
        WebElement refreshButton = driver.findElement(By.name("Refresh"));
        assertTrue(refreshButton.isDisplayed());
        refreshButton.click();
        Thread.sleep(3000);

        //Press export button
        WebElement exportButton = driver.findElement(By.name("Export..."));
        assertTrue(exportButton.isDisplayed());
        exportButton.click();

        //check dialog save opened
        WebElement dialogSave = driver.findElement(By.className("#32770"));
        assertTrue(dialogSave.isDisplayed());

        //"Edit"
        WebElement editNameFile = driver.findElement(By.className("Edit"));
        assertTrue(editNameFile.isDisplayed());
        editNameFile.click();
        editNameFile.sendKeys("testXls");

        //Choose xls
        buttonsList = driver.findElements(By.name("Кнопка раскрытия списка"));
        buttonsList.get(1).click();
        WebElement xlsType = driver.findElement(By.name("XLS Document (*.xml)"));
        assertTrue(xlsType.isDisplayed());
        xlsType.click();

        //Press save
        WebElement saveButton = driver.findElement(By.name("Сохранить"));
        assertTrue(saveButton.isDisplayed());
        saveButton.click();
    }
}
