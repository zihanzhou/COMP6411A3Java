import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Game{
    public static void main(String[] args) {
        String inputFile = "p1.txt"; // Replace with your file path

        try {
            Map<String, Integer> playerCredits = loadPlayerCredits(inputFile);
            System.out.println("Player Credits:");
            playerCredits.forEach((name, credits) -> System.out.println(name + ": " + credits));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> loadPlayerCredits(String inputFile) throws IOException {
        Map<String, Integer> playerCredits = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Regex pattern to match {name, credits}.
                Pattern pattern = Pattern.compile("\\{(\\w+),(\\d+)\\}");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    String name = matcher.group(1);
                    int credits = Integer.parseInt(matcher.group(2));
                    playerCredits.put(name, credits);
                }
            }
        }
        return playerCredits;
    }
}