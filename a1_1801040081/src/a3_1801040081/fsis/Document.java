package a3_1801040081.fsis;

import utils.DOpt;
import utils.OptType;

/**
 * @overview
 * An interface where its objects can be read as HTML documents by the keyword search engine
 * i.e. KEngine
 */
public interface Document {
    /**
     * @effect
     *      return a String containing the text of a simple HTML document
     *      generated from the state of the current object
     *      e.g. Customer:<4, "John", "12345678", "Hanoi"> invoke toHtmlDoc()
     *      -> output:
     *      <html>
     *          <head><title>Customer:4-John</title></head>
     *          <body>
     *              4 John 12345678 Hanoi
     *          </body>
     *      </html>
     */
    @DOpt(type = OptType.Observer)
    public String toHtmlDoc();
}
