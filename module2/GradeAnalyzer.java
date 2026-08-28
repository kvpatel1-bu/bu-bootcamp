import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    static int invalidLines = 0;

    public static void main(String[] args) {
        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");

        // Step 2: calculate statistics
        double average = calculateAverage(scores);
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > highest) {
                highest = scores.get(i);
            }
            if (scores.get(i) < lowest) {
                lowest = scores.get(i);
            }
        }

        // Step 3: write and print report
        writeReport(scores, average, highest, lowest, "report.txt");
    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        invalidLines = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);
                    if (score < 0 || score > 100) {
                        System.out.println("Warning: skipping out-of-range score: " + line);
                        invalidLines++;
                    } else {
                        scores.add(score);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Warning: skipping invalid entry: " + line);
                    invalidLines++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.size() == 0) {
            return 0.0;
        }

        double total = 0;
        for (int i = 0; i < scores.size(); i++) {
            total = total + scores.get(i);
        }

        double avg = total / scores.size();
        return avg;
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) >= 90) {
                countA++;
            } else if (scores.get(i) >= 80) {
                countB++;
            } else if (scores.get(i) >= 70) {
                countC++;
            } else if (scores.get(i) >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        // print to terminal
        System.out.println("=== Grade Analysis Report ===");
        System.out.println(String.format("Total scores processed: %d", scores.size()));
        System.out.println(String.format("Invalid lines skipped:  %d", invalidLines));
        System.out.println();
        if (scores.size() == 0) {
            System.out.println("No valid scores were found.");
            System.out.println();
        }
        System.out.println(String.format("Average score:  %.2f", avg));
        if (scores.size() == 0) {
            System.out.println("Highest score:  N/A");
            System.out.println("Lowest score:   N/A");
        } else {
            System.out.println(String.format("Highest score:  %d", high));
            System.out.println(String.format("Lowest score:   %d", low));
        }
        System.out.println();
        System.out.println("Grade distribution:");
        System.out.println(String.format("  A (90-100):   %d", countA));
        System.out.println(String.format("  B (80-89):    %d", countB));
        System.out.println(String.format("  C (70-79):    %d", countC));
        System.out.println(String.format("  D (60-69):    %d", countD));
        System.out.println(String.format("  F (below 60): %d", countF));

        // write same thing to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("=== Grade Analysis Report ===");
            writer.newLine();
            writer.write(String.format("Total scores processed: %d", scores.size()));
            writer.newLine();
            writer.write(String.format("Invalid lines skipped:  %d", invalidLines));
            writer.newLine();
            writer.newLine();
            if (scores.size() == 0) {
                writer.write("No valid scores were found.");
                writer.newLine();
                writer.newLine();
            }
            writer.write(String.format("Average score:  %.2f", avg));
            writer.newLine();
            if (scores.size() == 0) {
                writer.write("Highest score:  N/A");
                writer.newLine();
                writer.write("Lowest score:   N/A");
                writer.newLine();
            } else {
                writer.write(String.format("Highest score:  %d", high));
                writer.newLine();
                writer.write(String.format("Lowest score:   %d", low));
                writer.newLine();
            }
            writer.newLine();
            writer.write("Grade distribution:");
            writer.newLine();
            writer.write(String.format("  A (90-100):   %d", countA));
            writer.newLine();
            writer.write(String.format("  B (80-89):    %d", countB));
            writer.newLine();
            writer.write(String.format("  C (70-79):    %d", countC));
            writer.newLine();
            writer.write(String.format("  D (60-69):    %d", countD));
            writer.newLine();
            writer.write(String.format("  F (below 60): %d", countF));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing " + outputFile + ": " + e.getMessage());
        }
    }
}