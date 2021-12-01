import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.Instant;

public class ExtractData {
    public String rawJson;

    public ExtractData(String rawJson) {
        this.rawJson = rawJson;
    }

    public Weather extract(){
        JsonObject jsonString = new Gson().fromJson(this.rawJson, JsonObject.class);
        JsonObject weather = jsonString.get("weather").getAsJsonArray().get(0).getAsJsonObject();
        JsonObject wind = jsonString.get("wind").getAsJsonObject();
        JsonObject main = jsonString.get("main").getAsJsonObject();
        JsonObject location = jsonString.get("coord").getAsJsonObject();

        Weather build = Weather.create().build();

        return new Weather()
                .setTs(Instant.now().toString())
                .setLat(location.get("lat").getAsDouble())
                .setLon(location.get("lon").getAsDouble())
                .setWeather(weather.get("main").getAsString())
                .setTemp( main.get("temp").getAsFloat())
                .setWindDir(wind.get("deg").getAsInt())
                .setWind(wind.get("speed").getAsFloat())
                .setHumidity( main.get("humidity").getAsInt())
                .setPressure(main.get("pressure").getAsInt());
    }
}