package com.orange.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ScreenshotUtility extends BaseUtility{

    public ScreenshotUtility(WebDriver driver){
        super(driver);
    }

    public String takeScreenshot(){
        try {
            String dateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("target/screenshots/screenshot_" + dateTimeNow + ".png");
            destinationFile.getParentFile().mkdirs();
            Files.copy(source.toPath(), destinationFile.toPath());
            return destinationFile.getAbsolutePath();
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String takeScreenshotAsBase64(){
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.BASE64);
    }

}
