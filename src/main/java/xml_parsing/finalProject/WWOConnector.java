package xml_parsing.finalProject;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WWOConnector {
    private  String city;
    private  int days;

    private static final String URL_PREFIX = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=";
    private static final String URL_USER_KEY = "dd70913624dc41178ad102232191706";
    private static final String URL_LOCATION_PREFIX = "&q=";
    private static final String URL_DAYS_PREFIX = "&num_of_days=";
    private static final String URL_SUFIX = "&format=xml";


    //http://api.worldweatheronline.com/premium/v1/weather.ashx?key=dd70913624dc41178ad102232191706&q=London&num_of_days=3&format=xml


    private final String urlString;

    public WWOConnector(String city, int days) {
        this.city = city;
        this.days = days;
        urlString = getUrlString();
        System.out.println(urlString);
    }

    private String getUrlString() {
        return URL_PREFIX +
                URL_USER_KEY +
                URL_LOCATION_PREFIX +
                city +
                URL_DAYS_PREFIX +
                days +
                URL_SUFIX;
    }

    public Document getDocument() {
        System.out.println("connecting...");
        Document document;
        // blok try catch wymuszony przez narzedzia z ktorych korzystam do odebrania danych
        // poniewaz w roznych sytuacjach mogą pojawić się błędy - wyjątki np przy braku odpowiedzi
        // z serwera
        try {
            // przekształcam w obiekt url
            URL url = new URL(urlString);

            // otwieram połączenie
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

           

            URLConnection connection = url.openConnection();
            InputStream wejscie = connection.getInputStream();
            Scanner scanner = new Scanner(wejscie);
           /* while (scanner.hasNextLine()) {
                System.out.println(scanner.next()+ "\n");
            }*/

            // pobieram kod odpowiedzi od serwera
            int responseCode = urlConnection.getResponseCode();
            System.out.println("\t\t\t...server response code: " + responseCode + "\n");

            // przygotowanie bibliotek od tworzenia dokumentu
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // uzyskanie dostępu do strumienia danych z połączenia z serwerem
            InputStream inputStream = urlConnection.getInputStream();
            // przetłumaczenie streama danych od serwera na dane xml - document
            document = builder.parse(inputStream);
            return document;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }


}
