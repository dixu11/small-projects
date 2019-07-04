package swing.quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuestionsFileAccess {
    private File file;

    public QuestionsFileAccess(String fileName) {
        file = new File(fileName + ".txt");
    }

    public String getFileContent() {
        String content = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found! - "+ file.getName());
        }
        return content;
    }


}
