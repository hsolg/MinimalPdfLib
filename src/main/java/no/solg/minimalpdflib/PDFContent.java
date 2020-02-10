package no.solg.minimalpdflib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.zip.DeflaterOutputStream;

/**
 *
 * @author Henrik Solgaard
 */
public class PDFContent extends PDFObject {
    private final ByteArrayOutputStream bs;
    private final DeflaterOutputStream d;

    public PDFContent() {
        bs = new ByteArrayOutputStream();
        d = new DeflaterOutputStream(bs);
    }

    public void setColorFill(int r, int g, int b) throws IOException {
        outputCommand(String.format((Locale)null, "%f %f %f rg\n", r/255.0, g/255.0, b/255.0));
    }

    public void setColorStroke(int r, int g, int b) throws IOException {
        outputCommand(String.format((Locale)null, "%f %f %f RG\n", r/255.0, g/255.0, b/255.0));
    }

    public void setLineWidth(double w) throws IOException {
        outputCommand(String.format((Locale)null, "%f w\n", w));
    }

    public void moveTo(double x, double y) throws IOException {
        outputCommand(String.format((Locale)null, "%f %f m\n", x, y));
    }

    public void lineTo(double x, double y) throws IOException {
        outputCommand(String.format((Locale)null, "%f %f l\n", x, y));
    }

    public void rectangle(double x, double y, double w, double h) throws IOException {
        outputCommand(String.format((Locale)null, "%f %f %f %f re\n", x, y, w, h));
    }

    public void fill() throws IOException {
        outputCommand("f\n");
    }

    public void stroke() throws IOException {
        outputCommand("S\n");
    }

    public void beginText() throws IOException {
        outputCommand("BT\n");
    }

    public void endText() throws IOException {
        outputCommand("ET\n");
    }

    @Override
    public void printObject(PDFStream s) throws IOException {
        d.close();
        byte deflated[] = bs.toByteArray();
        s.println(String.format("<</Length %d/Filter/FlateDecode>>stream", deflated.length));
        s.write(deflated);
        s.println();
        s.println("endstream");
    }

    private void outputCommand(String cmd) throws IOException {
        d.write(cmd.getBytes());
    }
}
