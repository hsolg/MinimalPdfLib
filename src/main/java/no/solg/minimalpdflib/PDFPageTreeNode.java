package no.solg.minimalpdflib;

/**
 *
 * @author Henrik Solgaard
 */
public class PDFPageTreeNode extends PDFObject {
    private final int pageIndex;

    public PDFPageTreeNode(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void printObject(PDFStream s) {
        s.println(String.format("<</Type/Pages/Count 1/Kids[%d 0 R]>>", pageIndex));
    }
}
