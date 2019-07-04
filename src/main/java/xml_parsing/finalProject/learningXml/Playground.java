package xml_parsing.finalProject.learningXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//iml related packages

public class Playground {
    public static void main(String[] args) {
        String apiKey = "a675f0257edf4aeaab9143643190102";
        String location = "Szczecin";
        String urlString = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=" + apiKey + "info jak stopnie";
        String rawUrl = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=a675f0257edf4aeaab9143643190102&q=Szczecin&num_of_days=3&format=xml";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(rawUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //  int responseCode = urlConnection.getResponseCode();
            //  System.out.println("ResponseCode = " + responseCode);
            String xmlAsString = "";
            Scanner scanner = new Scanner(new InputStreamReader(urlConnection.getInputStream())); // skorzystalem ze scannera zamiast br
            while (scanner.hasNextLine()) {
                xmlAsString += scanner.nextLine();
            }
            // System.out.println(xmlAsString);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(rawUrl);
            System.out.println("Root: " + document.getDocumentElement().getNodeName());

            // otrzymuję listę prognoz na każdy dzień, node lista zawiera pojedyncze dni
            NodeList weatherElements = document.getElementsByTagName("weather");



            System.out.println("ilość elementów w pogodzie: " + weatherElements.getLength());
           // List<NodeList> forcastsForDays = new ArrayList<>();

            for (int i = 0; i < weatherElements.getLength(); i++) {
                //forcastsForDays.add(weatherElements.item(i).getChildNodes());
                // chcę otrzymać konkretny dzień i wyświetlić dla niego datę:
                Node day = weatherElements.item(i);
                if (day.getNodeType() == Node.ELEMENT_NODE) {
                    Element dayElement = (Element) day;
                    String date = getTextContent("date", dayElement);
                    String maxTempC = getTextContent("maxtempC", dayElement);
                    String minTempC = getTextContent("mintempC", dayElement);
                    System.out.println("Date: " + date);
                    System.out.println("Max temperature: " + maxTempC + "C");
                    System.out.println("Min temperature: " + minTempC + "C");
                }
            }
            /*for (NodeList forcastsForDay : forcastsForDays) {
                for (int i = 0; i < forcastsForDay.getLength(); i++) {
                    Node dayForecastNode = forcastsForDay.item(i);
                    if (dayForecastNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element dayForecastElement = (Element) dayForecastNode;
                        String date = getTextContent("date", dayForecastElement);
                        System.out.println("Date is: " + date);
                       *//* if (dayForecastElement.getNodeName().equals("date")) {
                            System.out.println("Forecast for date: " +
                                    dayForecastElement.getTextContent());
                        }*//*

                    }
                }
            }*/


            // Document document = builder.parse(urlConnection.getInputStream());
            //StringBuilder xmlStringBuilder = new StringBuilder();

            // xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
            //ByteArrayInputStream input = new ByteArrayInputStream(
            //        xmlStringBuilder.toString().getBytes("UTF-8"));

            //  doc.getDocumentElement().normalize();  spłaszcza, wywala białe znaki itd
            // Element root = doc.getDocumentElement();

//returns specific attribute
            // getAttribute("attributeName");

//returns a Map (table) of names/values
            // getAttributes();


        } catch (MalformedURLException e) {
            e.printStackTrace(); // from creating URL
        } catch (IOException e) {
            e.printStackTrace(); // from opening connection
        } catch (ParserConfigurationException e) { // from newDocumentBuilder
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

    public static String getTextContent(String elementTag, Element dayWeather) {
        return dayWeather.getElementsByTagName(elementTag).item(0).getTextContent();
    }

}
// chcę otrzymać listę elementów weather
// chcę dla każdej listy elementów weather wyświetlić tekst elementu (dla date, max temp itd...)