package main;

import org.testng.TestNG;

import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utilities.ExtentReportManager.reportName;

public class mainRunner {

    static TestNG testng;

    public static void main(String[] args) {

        testng = new TestNG();
        String path = System.getProperty("user.dir")+ "/testng.xml";
        List<String> xmlList = new ArrayList<String>();
        xmlList.add(path);

        testng.setTestSuites(xmlList);
        testng.run();


        try {
            Desktop.getDesktop().browse(new File(System.getProperty("user.dir") +"\\reports\\" + reportName).toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
