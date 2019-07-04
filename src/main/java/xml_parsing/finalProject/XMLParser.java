package xml_parsing.finalProject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

    private Document xml;
    private String city;

    public XMLParser(Document xml, String city) {
        this.xml = xml;
        this.city = city;
    }

    public void displayWeatherForecast() {
        // dostaję się do listy elementów weather pliku XML
        NodeList days = xml.getElementsByTagName("weather");
        // z wielkości listy wiem ile dni prognozy
        int nrOfDays = days.getLength();
        System.out.println("--- " + nrOfDays + " days of weather for " + city + " ---\n");
        // przeglądam w pętli wszystykie node
        for (int i = 0; i < nrOfDays; i++) {
            // zapisuje do zmiennej pojedynczy node
            Node weatherNode = days.item(i);
            // upewniam się czy ten Node to element
            if (weatherNode.getNodeType() == Node.ELEMENT_NODE) {
                // przekształcam Node w element
                Element weather = (Element) weatherNode;
                displayWeatherForDay(weather);
                System.out.println();
            }
        }
    }

    public void displayWeatherForDay(Element weather) {
    // wyjecie danych o dacie poprzez pomocnicza metode getTextContent
        System.out.println("\t" + getTextContent("date", weather));
        // pobranie tempetratur z dodatkowym wyliczeniem średniej
        String maxTemp = getTextContent("maxtempC", weather);
        String minTemp = getTextContent("mintempC", weather);
        double maxTempD = Double.parseDouble(maxTemp);
        double minTempD = Double.parseDouble(minTemp);
        System.out.println("Max temperature: " + maxTemp + "C");
        System.out.println("Min temperature: " + minTemp + "C");
        System.out.printf("Average temperature: %.1fC\n", calculateAverage(maxTempD, minTempD));

        System.out.println("Total snow: " + getTextContent("totalSnow_cm", weather) + "cm");
        System.out.println("Sun on the sky: " + getTextContent("sunHour", weather) + "h");
        System.out.println();
        // wywołujemy zagłębione dane dotyczące konkretnej godziny
        displayNestedData(weather);
    }

    private void displayNestedData(Element weather) {
        NodeList dayHourly = weather.getElementsByTagName("hourly");
        // dostaję się bezpośrednio tylko do informacji o pogodzie w godzinie 12:00AM
        Element dayAt12AM = (Element) dayHourly.item(4);
        System.out.println("At 12:00 day will look like this: ");
        System.out.println("\tShort description: " + getTextContent("weatherDesc", dayAt12AM));
        System.out.println("\tTemperature: " + getTextContent("tempC", dayAt12AM) + "C");
        System.out.println("\tTemperature feel like: " + getTextContent("FeelsLikeC", dayAt12AM) + "C");
        System.out.println("\tWind speed: " + getTextContent("windspeedKmph", dayAt12AM) + "km/h");
        System.out.println("\tPressure: " + getTextContent("pressure", dayAt12AM) + "hPa");
        System.out.println("\tCloudcover: " + getTextContent("cloudcover", dayAt12AM) + "%");
        System.out.println("\tChance of rain: " + getTextContent("chanceofrain", dayAt12AM) + "%");
        System.out.println("\tChance of snow: " + getTextContent("chanceofsnow", dayAt12AM) + "%");
        System.out.println();
        System.out.println("----------------------------------------");
    }

    // z elementu wyjmuje dane pod konkretnym tagiem, z pierwszej pozycji w formie tekstu (String)
    private String getTextContent(String elementTag, Element element) {
        return element.getElementsByTagName(elementTag).item(0).getTextContent();
    }

    private double calculateAverage(double a, double b) {
        return (a + b) / 2;
    }
}
