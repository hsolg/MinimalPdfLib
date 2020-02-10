package no.solg.minimalpdflib;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for MinimalPdfLib.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Create a PDF file.
     */
    public void testApp() throws IOException {
        PDFWriter writer = new PDFWriter("test.pdf");
        PDFContent content = writer.getContent();
        content.moveTo(0, 0);
        content.lineTo(300, 300);
        content.moveTo(0, 300);
        content.lineTo(300, 0);
        content.stroke();
        content.setColorStroke(255, 255, 0);
        content.rectangle(50, 0, 100, 50);
        content.stroke();
        writer.save();
    }
}
