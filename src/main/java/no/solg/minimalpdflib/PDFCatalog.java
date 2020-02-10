package no.solg.minimalpdflib;

/**
 *
 * @author Henrik Solgaard
 */
public class PDFCatalog extends PDFObject {
    private final int rootIndex;

    public PDFCatalog(int rootIndex) {
        this.rootIndex = rootIndex;
    }

    @Override
    public void printObject(PDFStream s) {
        s.println(String.format("<</Type/Catalog/Pages %d 0 R>>", rootIndex));
    }
}
