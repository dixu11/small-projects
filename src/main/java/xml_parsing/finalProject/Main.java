package xml_parsing.finalProject;

/*
* Plan:
* Aplikacja przyjmuje informacje z linii komend - miasto i ilość dni: 1-14
* Łączy się z serwisem WWO z podanymi danymi
* Otrzymuje dane w postaci XML a następnie przekształca je w dokument za pomocą odpowiednich bibliotek
* za pomocą odpowiednich metod wyłuskije informacje na temat każdego z dni:
* Informacje do uzyskania to:
* - data dnia dla którego sprawdzamy pogodę
* - najwyższa możliwa temp w danym dniu
* - najniższa temp
* - średnia tych temp
* - + inne
*
* - dane dla godziny 12:00 dla każdego dnia:
*   - ciśnienie
*   - zachmurzenie
*   - szansa na opady
*   - temp
*   - odczuwalna temp
*   - opis
* */


import org.w3c.dom.Document;

public class Main {
    // program wymaga uruchomienia z 2 argumentami:
    // - miasto
    // - numer - ilość dni prognozy
    public static void main(String[] args) {

        if (argsValidation(args)) {
            String city = args[0];
            int days = Integer.parseInt(args[1]);
            // tworzymy obiekt odpowiedzialny za polaczenie
         WWOConnector wwoConnector = new WWOConnector(city,days);


            // wywolanie metody getDeocument dostarcza obiekt typu Documen z danymi od serwera w formacie xml
            Document xmlWWODOcument = wwoConnector.getDocument();
            // tworzymy smlParserser odpowiedzialny za odczytanie danych z xml
            // przy tworzeniu przekazujemy otrzymany wczesniej dokument i nazwe miasta
            XMLParser xmlParser = new XMLParser(xmlWWODOcument, city);
            // wywołujemy metode diplay odpowiedzialną za wyświetlenie całości informacji na konsole
            xmlParser.displayWeatherForecast();
        }
    }

// sprawdzanie poprawnosci argumentow miasto - dzien
    public static boolean argsValidation(String[] args) {
        if (args.length < 2) {
            System.out.println("Aplication need to have 2 arguments: city and nr of days: 1-14");
            System.out.println("For example: Lublin 10");
            return false;
        }
        if (args.length == 2) {
            char[] cityLetters = args[0].toCharArray();
            for (char letter : cityLetters) {
                if (!Character.isLetter(letter)) {
                    System.out.println("Something is wrong with first argument: city");
                    System.out.println("Your argument: " + args[0]);
                    System.out.println("Seting default value: Warszawa");
                    args[0] = "Warszawa";
                }
            }
            char[] daysAsChars = args[1].toCharArray();
            for (char character : daysAsChars) {
                if (!Character.isDigit(character)) {
                    System.out.println("Wrong number of days!");
                    System.out.println("Your argument: " + args[1]);
                    System.out.println("Seting default value: 7");
                    args[1] = "7";
                }
            }
            int days = Integer.parseInt(args[1]);
            if (days < 1 || days > 14) {
                System.out.println("Wrong number of days!!");
                System.out.println("Your argument: " + args[1]);
                System.out.println("Seting default value: 7");
                args[1] = "7";
            }
        } else {
            System.out.println("Aplication need to have 2 arguments: city + nr of days");
            System.out.println("Seting default values: Warszawa 7");
            args[0] = "Warszawa";
            args[1] = "7";
            return true;
        }
        return true;
    }
}
