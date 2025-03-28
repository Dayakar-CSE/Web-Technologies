import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;

public class SAXUserSearch {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new File("users.xml"), new DefaultHandler() {
            boolean nameFlag = false, emailFlag = false;
            boolean userFound = false;
            String currentId = "";

            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equals("user") && attributes.getValue("id").equals(userId)) {
                    userFound = true;
                }
                if (userFound && qName.equals("name")) nameFlag = true;
                if (userFound && qName.equals("email")) emailFlag = true;
            }

            public void characters(char[] ch, int start, int length) {
                if (nameFlag) {
                    System.out.println("Name: " + new String(ch, start, length));
                    nameFlag = false;
                }
                if (emailFlag) {
                    System.out.println("Email: " + new String(ch, start, length));
                    emailFlag = false;
                }
            }

            public void endElement(String uri, String localName, String qName) {
                if (qName.equals("user") && userFound) {
                    userFound = false;
                }
            }
        });
    }
}
