package no.solg.minimalpdflib;

/**
 *
 * @author Henrik Solgaard
 */
public class PDFPage extends PDFObject {
    private int parentIndex;
    private final int contentIndex;

    public PDFPage(int contentIndex) {
        this.contentIndex = contentIndex;
    }

    public void setParent(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    @Override
    public void printObject(PDFStream s) {
        String procSet = "/PDF /Text /ImageB /ImageC /ImageI";
        String resources = "/ProcSet [" + procSet + "]";
        String box = "0 0 595 842";
        s.println(String.format("<</Type/Page/Parent %d 0 R/Contents %d 0 R/Resources<<%s>>/MediaBox[%s]>>", parentIndex, contentIndex, resources, box));
    }
}
