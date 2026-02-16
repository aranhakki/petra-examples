package com.cognitionbox.petra.examples.trading.strategy.data;

import com.cognitionbox.petra.ast.terms.External;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@External
public final class DataSource {
    // A list to store prices loaded from the CSV file.
    private static List<Float> prices = new ArrayList<>();
    // A list to store timestamps corresponding to the prices.
    private static List<LocalDateTime> timestamps = new ArrayList<>();
    // Pointer to the next price.
    private static int currentIndex = -1;

    // Define the date-time formatter based on the expected format in the CSV file
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");

    /**
     * Loads market data from the given CSV file.
     * Assumes the CSV file has a header row, the timestamp is in the first column,
     * and the price is in the "open" column (second column).
     *
     * @param csvFilePath the path to the CSV file
     */
    public static void loadMarketData(String csvFilePath) {
        try (InputStream is = new FileInputStream(csvFilePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + csvFilePath);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    // Skip header row
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    String[] parts = line.split(",");
                    if (parts.length < 2) continue; // ensure there is a timestamp and an open price column

                    // Parse the timestamp (first column)
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0], DATE_TIME_FORMATTER);
                    // Parse the "open" price (second column)
                    float openPrice = Float.parseFloat(parts[1]);

                    timestamps.add(timestamp);
                    prices.add(openPrice);
                }
            } catch (IOException e) {
                // Print the stack trace or handle the exception as needed
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasNextPrice(){
        return currentIndex < prices.size() - 1;
    }

    public static void incTime(){
        currentIndex = (currentIndex + 1);// % prices.size();
    }

    /**
     * Returns the next market price from the loaded data.
     * Cycles back to the start if the end of the list is reached.
     *
     * @return the next market price
     * @throws IllegalStateException if market data has not been loaded
     */
    public static float getMid() {
        if (prices.isEmpty()) {
            throw new IllegalStateException("Market data not loaded. Call loadMarketData() first.");
        }
        float price = prices.get(currentIndex);
        return price;
    }

    /**
     * Returns the timestamp corresponding to the current price.
     * Cycles back to the start if the end of the list is reached.
     *
     * @return the timestamp of the current market price
     * @throws IllegalStateException if market data has not been loaded
     */
    public static LocalDateTime getTimestamp() {
        if (timestamps.isEmpty()) {
            throw new IllegalStateException("Market data not loaded. Call loadMarketData() first.");
        }
        LocalDateTime timestamp = timestamps.get(currentIndex);
        return timestamp;
    }

    public static LocalDateTime incrementAndGetTimestamp() {
        incTime();
        return getTimestamp();
    }

    private SplittableRandom random = new SplittableRandom();
    /**
     * Generates a sinusoidal price dataset that cycles daily.
     * Data format: GMT time, Open, High, Low, Close, Volume.
     */
    public static void generateSinusoidalData() {
        // Chosen alpha for an Exponential Moving Average related to a 24-hour cycle:
        // alpha = 0.3 means each new hour's data will carry ~30% weight,
        // and the older average retains ~70% weight. This is a moderate response speed
        // for a full 24-hour sinusoid.

        List<String> generatedData = new ArrayList<>();

        LocalDateTime startTime = LocalDateTime.of(2003, 6, 2, 8, 0, 0);
        int numHours = 9; // Simulate 8 hours of data, 8am to 4pm
        double basePrice = 1.04;
        double amplitude = 0.005;

        for (int i = 0; i < numHours; i++) {
            LocalDateTime timestamp = startTime.plusHours(i);
            // One full sine cycle across 12 hours:
            double price = basePrice + amplitude * Math.sin(2 * Math.PI * i / 8.0);

            String formattedLine = String.format(
                    "%s,%.5f,%.5f,%.5f,%.5f,%.2f",
                    timestamp.format(DATE_TIME_FORMATTER), price, price, price, price, 0.0
            );
            generatedData.add(formattedLine);
        }

        // Write the generated lines to a CSV file
        try (PrintWriter out = new PrintWriter(new FileWriter("SinusoidalData.csv"))) {
            for (String line : generatedData) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Simple demo main
    public static void main(String[] args) {
        // Generates the CSV file
        generateSinusoidalData();
        System.out.println("SinusoidalData.csv generated successfully.");
    }

    public static boolean isFinished() {
        return prices.size()==currentIndex;
    }

    public static float getMidIfAvgIsZero(float average) {
        if (average==0){
            return getMid();
        }
        return average;
    }
}
