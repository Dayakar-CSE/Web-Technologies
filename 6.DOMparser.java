import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.Scanner;

public class DOMUserSearch {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("users.xml"));

        NodeList users = doc.getElementsByTagName("user");
        boolean found = false;

        for (int i = 0; i < users.getLength(); i++) {
            Element user = (Element) users.item(i);
            if (user.getAttribute("id").equals(userId)) {
                String name = user.getElementsByTagName("name").item(0).getTextContent();
                String email = user.getElementsByTagName("email").item(0).getTextContent();
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("User not found.");
    }
}
