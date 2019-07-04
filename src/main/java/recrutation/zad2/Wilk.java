package recrutation.zad2;

/*Zadanie 2.
        Ponieważ słupki udało się zsumować, zwierzątka zaplanowały start tuż po podwieczorku.
        Jakież było ich rozczarowanie, gdy przed drugim śniadaniem zorientowały się, że zły wilk Błażej,
        wypił całe paliwo rakietowe ze zbiorników zatankowanego kosmolotu. No bez sensu, nie? Nie pomyślał o innych zwierzątkach,
        które chciały lecieć w podróż na Księżyc.
        Sarenka Kasia i gąska Karolina głowią się jak by tu ponownie napełnić zbiorniki.
        "Najlepiej z kranu!" - krzyknęła sarenka Kasia.
        "Najlepiej z dwóch!" - krzyknęła gąska Karolina.
        "Ta, z miliona!" - burknął zły wilk Błażej, co nie spotkało się z entuzjazmem.
        "Jeszcze słowo i będziesz robił za lajke, draniu!" - krzyknęła sarenka Kasia, z charakterystycznym dla siebie
        tupnięciem kopytkiem, które wykonuje, kiedy werbalizuje swoje zdenerwowanie.
        Dopiero po chwili dotarło do wszystkich, że to nie taki głupi pomysł, bo wtedy zbiorniki napełnią się bardzo,
         bardzo szybko i być może wcale nie będzie potrzeby odroczenia startu.
        "Pamiętaj Kasiu, że musimy wiedzieć, jaka jest temperatura paliwa!" - przypomniała gąska Karolina -
        "Poprzednim razem tego nie sprawdziłyśmy i borsuk Wasyl naprzemiennie wykrzykiwał jakieś brzydkie słowa i
        chuchał na zbiornik z zamarzniętym paliwem..."

        Dane wejściowe:
        ilość paliwa potrzebnego do lotu, przepływność każdego kranu, temperatura paliwa w każdym kranie.
        Nie trzeba się przejmować stratami ciepła przy napełnianiu zbiorników (ponieważ jest to szczęśliwa,
         magiczna kraina, gdzie woda nie stygnie, ani nie paruje).
        Umówmy się, że jednocześnie paliwa nie będzie lać więcej niż milion kranów (a jeśli w danych wejściowych będzie
        więcej, to należy je uznać za niepoprawne), a ilość wymaganego paliwa nie będzie większa niż 100000000 litrów.
        Woda (bo tak naprawdę to ta rakieta lata na wodę) może mieć nie więcej niż 90 stopni i nie mniej niż 1
        (tak, wiemy co to punkt potrójny :)).
        Dane wejściowe mogą mieć dokładność do pięciu miejsc po przecinku. Wyniki należy podawać z dokładnością do
        piątego miejsca po przecinku. Separatorem dziesiętnym jest kropka.
        Plik z programem powinien nazywać się Wilk.java

        Twój program zostanie skompilowany następującą komendą:
        javac Wilk.java
        Program będzie uruchamiany komendą:
        java Wilk < plik.csv
        Oczekiwany wynik:
        Trzeba obliczyć czas napełniania i końcową temperaturę wody (paliwa rakietowego).
        Przykładowy stdin dla dwóch kranów (w formie pliku csv - każdy wiersz kończy się znakiem końca linii):
        10.12345
        100.12345 20.12345
        100.12345 20.12345
        pierwszy wiersz to ilość paliwa potrzebnego do lotu - w tym przypadku 10.12345 m^3
        W pozostałych wierszach mamy odpowiednio przepływność kranu (w drugim wierszu 100.12345 l/min, w trzecim
        100.12345 l/min) i temperaturę wody płynącej z kranu (w drugim wierszu 20.12345 stopni Celsjusza, w
        trzecim 20.12345 stopni Celsjusza)
        Trzeba pamiętać, że poszczególne krany mogą mieć różne przepływności i temperatury wody, a dane mogą być
        niepoprawne na różne sposoby (przygotowujący je osioł Czesław jest bardzo twórczy).
        Wynik (stdout)
        3033.2904
        20.12345
        gdzie pierwsza linijka oznacza liczbę sekund potrzebnych do napełnienia zbiorników
        druga linijka to temperatura paliwa w zbiornikach (w stopniach Celsjusza).*/


public class Wilk {

    private static final String ERROR = "klops";

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        RocketfuelRefilingService refilingService = new RocketfuelRefilingService(csvReader);
        refilingService.printAllStartingData();
    }


    public static void exitWithError() {
        System.err.println(ERROR);
        System.exit(1);
    }
}
