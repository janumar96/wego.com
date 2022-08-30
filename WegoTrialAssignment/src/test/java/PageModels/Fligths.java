package PageModels;

import Enums.FlightCategory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Date;
import java.util.List;

public class Fligths extends PageBase
{
    List<WebElement> flightTypes = null;
    public Fligths(WebDriver driver)
    {
        _driver = driver;
        url = "https://www.wego.pk/flights";
        title = "Cheap Flights, Flight Booking";
    }

    public void SetFlightType(String type)
    {
        String locator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelectorAll(\"div > div.headers-wrapper button\")";
        flightTypes = getWebElementsFromJS(locator);
        for (WebElement button: flightTypes)
        {
            if(button.getDomAttribute("data-category").equals(type))
            {
                button.click();
            }
        }
    }
    public void SetFrom(String from) throws InterruptedException {
       String locator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-location-picker\").shadowRoot.querySelector(\"single-location-picker:nth-child(2)\").shadowRoot.querySelector(\"#locationSelector\").shadowRoot.querySelector(\"div > input[type=text]\")";
       WebElement fromInput = getWebElementFromJS(locator);
       fromInput.click();
       Thread.sleep(15000); //Wait for Search box to appear where popular cities will be fetched.
       String popularCitiesLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-location-picker\").shadowRoot.querySelector(\"single-location-picker:nth-child(2)\").shadowRoot.querySelector(\"#locationOverlay\").shadowRoot.querySelector(\"#defaultResults\").shadowRoot.querySelector(\"popular-cities\").shadowRoot.querySelectorAll(\"div.content.popular-cities div\")";
        List<WebElement> popularCities = getWebElementsFromJS(popularCitiesLocator);
        for (WebElement city: popularCities)
        {
            if(city.getText().equals(from))
            {
                city.click();
                break;
            }
        }
    }
    public void setTo(String to) throws InterruptedException {
        String locator = " return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-location-picker\").shadowRoot.querySelector(\"single-location-picker:nth-child(3)\").shadowRoot.querySelector(\"#locationSelector\").shadowRoot.querySelector(\"div > input[type=text]\")";
        WebElement toInput = getWebElementFromJS(locator);
        toInput.click();
        Thread.sleep(3000); //Wait for Search box to appear where city will be inserted.
        String popUpInputLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-location-picker\").shadowRoot.querySelector(\"single-location-picker:nth-child(3)\").shadowRoot.querySelector(\"#locationOverlay\").shadowRoot.querySelector(\"div > wego-selector\").shadowRoot.querySelector(\"div > input[type=text]\")";
        WebElement popUpInput = getWebElementFromJS(popUpInputLocator);
        popUpInput.clear();
        popUpInput.sendKeys(to);
        popUpInput.sendKeys(Keys.ENTER);
//        String popularCitiesLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-location-picker\").shadowRoot.querySelector(\"single-location-picker:nth-child(2)\").shadowRoot.querySelector(\"#locationOverlay\").shadowRoot.querySelector(\"#defaultResults\").shadowRoot.querySelector(\"popular-cities\").shadowRoot.querySelectorAll(\"div.content.popular-cities div\")";
//        List<WebElement> popularCities = getWebElementsFromJS(popularCitiesLocator);
//        for (WebElement city: popularCities)
//        {
//            if(city.getText().equals(from))
//            {
//                city.click();
//                break;
//            }
//        }
    }
    public void setDapertDate(Date date) throws InterruptedException {
        String departInputLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-date-picker\").shadowRoot.querySelector(\"wego-date-selectors\").shadowRoot.querySelector(\"wego-date-selector\").shadowRoot.querySelector(\"div.container > input[type=text]\")";
        WebElement departDate = getWebElementFromJS(departInputLocator);
        departDate.click();
        Thread.sleep(8000); //Wait for Date calender to open.
        String calenderLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-date-picker\").shadowRoot.querySelector(\"calendar-overlay\").shadowRoot.querySelectorAll(\"div > div.calendars > calendar-month\")";
        List<WebElement> calenderMonths = getWebElementsFromJS(calenderLocator);
        boolean daySelected = false;
        if(calenderMonths.size() > 0)
        {
            for(int i = 0; i< calenderMonths.size(); i++)
            {
                if(!daySelected)
                {
                    String daysLocator = "return arguments[0].shadowRoot.querySelectorAll(\"div.days i.day\")";
                    List<WebElement> days = getWebElementsFromJS(daysLocator,calenderMonths.get(i));
                    for (WebElement day: days)
                    {
                        if(day.getDomAttribute("data-month") != null)
                        {
                            if ((Integer.parseInt(day.getDomAttribute("data-month")) == (date.getMonth() - 1)) && (Integer.parseInt(day.getDomAttribute("data-day")) == (date.getDate())) && (Integer.parseInt(day.getDomAttribute("data-year")) == (date.getYear()))) {
                                day.click();
                                daySelected = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void setReturnDate(Date date) throws InterruptedException {
//        String departInputLocator = "document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-date-picker\").shadowRoot.querySelector(\"calendar-overlay\").shadowRoot.querySelector(\"div > div.wrapper > wego-date-selectors\").shadowRoot.querySelector(\"wego-date-selector:nth-child(3)\").shadowRoot.querySelector(\"div.container > input[type=text]\")";
//        WebElement departDate = getWebElementFromJS(departInputLocator);
//        departDate.click();
        Thread.sleep(8000); //Wait for Date calender to open.
        String calenderLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.inputs-wrapper > wego-date-picker\").shadowRoot.querySelector(\"calendar-overlay\").shadowRoot.querySelectorAll(\"div > div.calendars > calendar-month\")";
        List<WebElement> calenderMonths = getWebElementsFromJS(calenderLocator);
        boolean daySelected = false;
        if(calenderMonths.size() > 0)
        {
            for(int i = 0; i< calenderMonths.size(); i++)
            {
                if(!daySelected)
                {
                    String daysLocator = "return arguments[0].shadowRoot.querySelectorAll(\"div.days i.day\")";
                    List<WebElement> days = getWebElementsFromJS(daysLocator,calenderMonths.get(i));
                    for (WebElement day: days)
                    {
                        if(day.getDomAttribute("data-month") != null)
                        {
                            if((Integer.parseInt(day.getDomAttribute("data-month")) == (date.getMonth() -1)) && (Integer.parseInt(day.getDomAttribute("data-day")) == (date.getDate())) && (Integer.parseInt(day.getDomAttribute("data-year")) == (date.getYear())))
                            {
                                day.click();
                                daySelected = true;
                                break;
                            }
                        }

                    }
                }
            }
        }
    }
    public void SearchFlight()
    {
        String searchButtonLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.options-wrapper > div.nested-right-options-wrapper > button.search\")";
        WebElement searchButton = getWebElementFromJS(searchButtonLocator);
        searchButton.click();
    }
    public void DisableCompare()
    {
        String checkboxStatusLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > comparison-providers\").shadowRoot.querySelector(\"div > div > div.provider > wego-checkbox\")";
        WebElement checkboxStatus = getWebElementFromJS(checkboxStatusLocator);
        if(checkboxStatus.getDomAttribute("checked")!= null && checkboxStatus.getDomAttribute("checked").equals("true"))
        {
            String providerLocator = "return arguments[0].shadowRoot.querySelector(\"div.checkbox-container\").click()";
            WebElement provider = getWebElementFromJS(providerLocator, checkboxStatus);
            if(provider != null)
            {
                provider.click();
            }
        }

    }
    public void VerifyFlights()
    {
        String flightsListLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#flights-search\").shadowRoot.querySelector(\"#flightResultList\").shadowRoot.querySelectorAll(\"#listview > div.group\")";
        List<WebElement> flights = getWebElementsFromJS(flightsListLocator);
        if(flights.size() == 0)
        {
            Assert.fail("Flights not searched correctly");
        }
    }
    public void VerifyFlights(String... filters)
    {
        String flightsListLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#flights-search\").shadowRoot.querySelector(\"#flightResultList\").shadowRoot.querySelectorAll(\"#listview > div.group\")";
        List<WebElement> flights = getWebElementsFromJS(flightsListLocator);
        if(flights.size() == 0)
        {
            Assert.fail("Flights not searched correctly");
        }
        else
        {
            for(int i = 0; i< flights.size(); i++)
            {
                for (String filter: filters)
                {
                    switch (filter)
                    {
                        case "Direct":
                        {
                            String directFlightLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#flights-search\").shadowRoot.querySelector(\"#flightResultList\").shadowRoot.querySelector(\"#listview > div:nth-child("+ i+2 +") > flight-card\").shadowRoot.querySelector(\"div.card > div.card-trip > div.bound > div.time > div.line.line--middle > div.direct-label\")";
                            WebElement directFlight = getWebElementFromJS(directFlightLocator);
                            if(directFlight.getDomAttribute("hidden") != null)
                            {
                                Assert.fail("Only Direct flights not listed..");
                            }
                        }
                        case "Refundable":
                        {
                            String refundTagLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#flights-search\").shadowRoot.querySelector(\"#flightResultList\").shadowRoot.querySelector(\"#listview > div:nth-child(2) > flight-card\").shadowRoot.querySelector(\"div.card > div.card-trip\")";
                            WebElement refundTag = getWebElementFromJS(refundTagLocator);
                            if(refundTag.getDomAttribute("refundable") == null)
                            {
                                Assert.fail("Only Refundable flights not listed..");
                            }
                        }
                    }
                }
            }
        }
    }
    public void SetFlightCategory(FlightCategory categoryType) throws InterruptedException {
        String categoryLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.options-wrapper > div.nested-left-options-wrapper > div.dropdown-list > cabin-class-picker\")";
        WebElement categoryDropdownButton = getWebElementFromJS(categoryLocator);
        categoryDropdownButton.click();
        Thread.sleep(5000);
        String categoryListLocator = "return arguments[0].shadowRoot.querySelectorAll(\"#popup > div > span\")";
        List<WebElement> categoryList = getWebElementsFromJS(categoryListLocator, categoryDropdownButton);
        for (WebElement category: categoryList)
        {
            if(category.getText().replace(" ", "").equals(categoryType.name()))
            {
                category.click();
                break;
            }
        }
    }
    public void setFlightSearchType(String type)
    {
        String checkStatusLocator = "return document.querySelector(\"#app\").shadowRoot.querySelector(\"#base > wego-search-form\").shadowRoot.querySelector(\"div.container > div.search-form-container > wego-flight-search-form\").shadowRoot.querySelector(\"wego-flight-search-form-home\").shadowRoot.querySelector(\"div > div.options-wrapper > div.nested-left-options-wrapper > div.direct-flight > wego-checkbox\")";
        WebElement checkSearchTypeStatus = getWebElementFromJS(checkStatusLocator);
        if(checkSearchTypeStatus.getDomAttribute("checked") == null && type.equals("Direct"))
        {
            String flightSearchTypeLocator = "return arguments[0].shadowRoot.querySelector(\"#checkbox\")";
            WebElement flightSearchType = getWebElementFromJS(flightSearchTypeLocator, checkSearchTypeStatus);
            flightSearchType.click();
        }

    }
    public void selectFilter(String... filters)
    {
        for (String filter: filters)
        {
            switch (filter)
            {
                case "Refundable":
                {
                    String refundFilterStatusLocator = " return document.querySelector(\"#app\").shadowRoot.querySelector(\"#flights-search\").shadowRoot.querySelector(\"#filter\").shadowRoot.querySelector(\"#flexible\")";
                    WebElement refundFilterStatus = getWebElementFromJS(refundFilterStatusLocator);
                    if(refundFilterStatus.getDomAttribute("checked") == null)
                    {
                        String refundFilterCheckBoxLocator = "return arguments[0].shadowRoot.querySelector(\"#checkbox\")";
                        WebElement refundFilterCheckBox = getWebElementFromJS(refundFilterCheckBoxLocator, refundFilterStatus);
                        refundFilterCheckBox.click();
                    }
                }
            }
        }
    }
}
