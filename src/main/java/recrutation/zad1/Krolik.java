package recrutation.zad1;
/*
Zadanie 1.
        Doktorant, królik Zbigniew, w roztargnieniu pomylił kserokopiarkę z niszczarką, a na kartce były dane do
        wyznaczania trajektorii lotu. Bez tego kosmolot nie doleci na Księżyc.
        Zorientował się dopiero wtedy, kiedy zmełł już całą kartkę i nie mógł znaleźć tacki, na której powinna pojawić
        się kopia (no cóż, taka gapa).
        Kartka z danymi przeszła przez niszczarkę. Długa kartka. Niszczarka pocięła kartkę na paski, a każdy z pasków na odcinki.
        Mimo wszystko królik Zbigniew musi przed świtem podsumować słupki, bo inaczej zła czarownica uwięzi wszystkich studentów z
        jego uczelni i nakaże im sumowanie wszystkiego ręcznie, na umowę o dzieło. Całe szczęście, że każdy ze słupków miał na
        kartce inny kolor tła - dzięki temu udało się złożyć odcinki w całe słupki (choć kolejność odcinków jest losowa).
        Pomóż królikowi Zbigniewowi, uratuj pozostałych studentów i tym samym pomóż pokonać trudności w misji Kosmolotu.
        Dane wejściowe:

        plik CSV - nie wiadomo ile ma kolumn, ale może ich być dużo. Układ kolumn nie jest znany.
        Niech będzie, że wierszy może być 5000 i tyle samo kolumn.
        Poszczególne komórki mogą zawierać nazwy kolumn (nazwą jest łańcuch znaków ujęty w nawiasach klamrowych, np.
        {nazwa_kolumny}), dane (liczby całkowite większe od -10000 i mniejsze od 10000) lub jakieś śmieci. "Mogą zawierać" oznacza,
         że nazw kolumn, danych lub śmieci może nie być wcale, mogą występować dokładnie raz lub mogą występować wiele razy.
        Sumujemy tylko dane (śmieci nie mają być uwzględniane w sumie, ale program nie powinien się na nich wysypać).
        Plik z programem powinien nazywać się Krolik.java

        Program zostanie skompilowany następującą komendą:
        javac Krolik.java
        Program będzie uruchamiany komendą:
        java Krolik nazwa_kolumny < plik.csv
        Oczekiwany wynik:
        Program zwraca sumę z kolumny, w której występuje nazwa wskazana jako parametr. Brak możliwości jednoznacznego
         wskazania kolumny, z której suma ma zostać zwrócona oznacza błąd danych wejściowych.
        przykładowy stdin (w formie pliku csv - każdy wiersz kończy się znakiem końca linii):

        aaa,1       ,{aaa} ,5,   4
        2  ,  {bbb} ,2    ,{5},{ccc}
        1  ,1      ,2,    3,   {bbb}
        {{}},1      ,1    ,1,  {ddd}

        Przykładowe wyniki działania programu:
        java Krolik aaa < plik.csv
        5
        java Krolik 3 < plik.csv
        klops
        java Krolik '{}' < plik.csv
        3
        java Krolik bbb < plik.csv
        klops
        java Krolik ccc < plik.csv
        4
        java Krolik ddd < plik.csv
        4
*/


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Krolik {

    private static final String ERROR = "klops";

    public static void main(String[] args) {
        if (args.length == 0) {
            exitWithError();
        }

        String column = args[0];
        analizeCSVData(column);
    }

    private static void analizeCSVData(String columnName) {
        List<String> lines = readCSVFromInputStream();
        String[][] data = generateArray(lines);
        printCSVData(data);
        int columnIndex = getColumnIndex(data, columnName);
        int count = countNumbersInColumn(data, columnIndex);
        System.out.println(count);
    }

    private static String[][] generateArray(List<String> lines) {
        String[][] data = new String[lines.size()][];
        for (int i = 0; i < data.length; i++) {
            String[] rows = lines.get(i).split(",");
            data[i] = rows;
        }
        return data;
    }

    private static List<String> readCSVFromInputStream() {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new LinkedList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    private static int getColumnIndex(String[][] data, String columnName) {
        Pattern compiledPattern = Pattern.compile("\\{{1}(.*)}{1}");
        int column = -1;
        int found = 0;
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                String content = data[y][x];
                Matcher matcher = compiledPattern.matcher(content);
                if (matcher.matches() && matcher.group(1).equals(columnName)) {
                    found++;
                    column = x;
                }

                if (found > 1) {
                    exitWithError();
                }

            }
        }

        if (found == 0) {
            exitWithError();
        }

        return column;
    }

    private static int countNumbersInColumn(String[][] data, int columnId) {
        int count = 0;
        Pattern pattern = Pattern.compile("\\d+");

        for (int y = 0; y < data.length; y++) {
            String content = data[y][columnId];
            Matcher matcher = pattern.matcher(content);
            if (!matcher.matches()) {
                continue;
            }

            count += Integer.parseInt(content);
        }

        return count;
    }


    private static void exitWithError() {
        System.err.println(ERROR);
        System.exit(1);
    }

    //test
    private static void printCSVData(String[][] data) {
        for (String[] rows : data) {
            for (String content : rows) {
                System.out.printf("%5s ", content);
            }
            System.out.println();
        }
    }
}
