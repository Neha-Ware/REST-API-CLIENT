import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class WeatherInfo {
    private static final String API_KEY = "984faba43a1102d9deaab23406aede82";  // Replace with your OpenWeatherMap API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=" + API_KEY;

    public static void main(String[] args) {
        try {
            // Make the HTTP request
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

            // Extract and display the data
            String cityName = jsonResponse.get("name").getAsString();
            double temperature = jsonResponse.get("main").getAsJsonObject().get("temp").getAsDouble();
            int humidity = jsonResponse.get("main").getAsJsonObject().get("humidity").getAsInt();
            String weatherDescription = jsonResponse.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();

            System.out.println("Weather Data:");
            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature + "K");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Description: " + weatherDescription);
        } catch (IOException e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
        }
    }

    public static String getAPI_KEY() {
        return API_KEY;
    }
}
