package TestCases;

import Enums.FlightCategory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import PageModels.*;

import java.util.Date;

public class FlightsSearchTestCases {
    ChromeDriver _driver = null;
    Fligths flightsSearchPage = null;
    @BeforeTest
    void setup()
    {
        try
        {
            System.setProperty("webdriver.chrome.driver", "E:\\Work Study\\Applications\\Java\\WegoTrialAssignment\\drivers\\chromedriver.exe");
            _driver = new ChromeDriver();
            _driver.manage().window().maximize();
            flightsSearchPage = new Fligths(_driver);
            flightsSearchPage.NavigateTo();
            flightsSearchPage.AllowtoSubscribe();
        }
        catch (Exception exp)
        {
            Assert.fail(exp.getMessage());
        }
    }
    @Test(priority = 0)
    void SearchTest1() throws InterruptedException //-- Search OneWay flight from Popular City(option) to Search City
    {
        flightsSearchPage.SetFlightType("oneWay");
        flightsSearchPage.SetFrom("Karachi");
        flightsSearchPage.setTo("Islamabad");
        flightsSearchPage.setDapertDate(new Date(2022, 8,29));
        Thread.sleep(5000);
        flightsSearchPage.DisableCompare();
        flightsSearchPage.SearchFlight();
        Thread.sleep(8000);
        // Now verify Result populated i.e. search yeld some flights.
        flightsSearchPage.VerifyFlights();
    }
    @Test(priority = 1)
    void SearchTest2() throws Exception //-- Search Round trip flight from Popular City(option) to Search City
    {
        flightsSearchPage.NavigateTo();
        Thread.sleep(10000);
        flightsSearchPage.SetFlightType("return");
        flightsSearchPage.SetFrom("Karachi");
        flightsSearchPage.setTo("Islamabad");
        flightsSearchPage.setDapertDate(new Date(2022, 8,29));
        flightsSearchPage.setReturnDate(new Date(2022, 9,05));
        Thread.sleep(5000);
       flightsSearchPage.DisableCompare();
        flightsSearchPage.SearchFlight();
        Thread.sleep(8000);
        // Now verify Result populated i.e. search yeld some flights.
        flightsSearchPage.VerifyFlights();
    }
    @Test(priority = 3)
    void SearchTest3() throws Exception //-- Search One Way flight from Popular City(option) to Search City with FirstClass category flights
    {
        flightsSearchPage.NavigateTo();
        Thread.sleep(10000);
        flightsSearchPage.SetFlightType("oneWay");
        flightsSearchPage.SetFlightCategory(FlightCategory.BusinessClass);
        flightsSearchPage.SetFrom("Islamabad");
        flightsSearchPage.setTo("Istanbul");
        flightsSearchPage.setDapertDate(new Date(2022, 8,29));
        Thread.sleep(5000);
        flightsSearchPage.DisableCompare();
        flightsSearchPage.SearchFlight();
        Thread.sleep(8000);
        // Now verify Result populated i.e. search yeld some flights.
        flightsSearchPage.VerifyFlights();
    }
    @Test(priority = 4)
    void SearchTest4() throws Exception //-- Search Direct flights only from One location to destination.
    {
        //Navigate to search fligh page to perform search...
        flightsSearchPage.NavigateTo();
        //Waiting for page to load completely to perform actions...
        Thread.sleep(10000);
        flightsSearchPage.SetFlightType("oneWay");
        flightsSearchPage.SetFlightCategory(FlightCategory.BusinessClass);
        flightsSearchPage.SetFrom("Islamabad");
        flightsSearchPage.setTo("Istanbul");
        flightsSearchPage.setDapertDate(new Date(2022, 8,29));
        //Wait for departure date to be selected...
        Thread.sleep(5000);
        // Uncheck compare flight option before search...
        flightsSearchPage.DisableCompare();
        //Select Direct flight option to fetch only direct flights...
        flightsSearchPage.setFlightSearchType("Direct");
        flightsSearchPage.SearchFlight();
        //Wait for search to fetch results...
        Thread.sleep(8000);
        // Now verify Result populated i.e. search yield some flights.
        flightsSearchPage.VerifyFlights("Direct");
    }
    @Test(priority = 5)
    void SearchTest5() throws Exception //-- Search Direct flights only from One location to destination.
    {
        //Navigate to search fligh page to perform search...
        flightsSearchPage.NavigateTo();
        //Waiting for page to load completely to perform actions...
        Thread.sleep(10000);
        flightsSearchPage.SetFlightType("oneWay");
        flightsSearchPage.SetFrom("Islamabad");
        flightsSearchPage.setTo("Istanbul");
        flightsSearchPage.setDapertDate(new Date(2022, 9,2));
        //Wait for departure date to be selected...
        Thread.sleep(5000);
        // Uncheck compare flight option before search...
        flightsSearchPage.DisableCompare();
        flightsSearchPage.SearchFlight();
        //Wait for search to fetch results...
        Thread.sleep(8000);
        // Now verify Result populated i.e. search yield some flights.
        flightsSearchPage.VerifyFlights();
        flightsSearchPage.selectFilter("Refundable");
        flightsSearchPage.VerifyFlights("Refundable");
    }
    @AfterTest
    void TearDown()
    {
        _driver.quit();
    }
}
