package Utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {
    String jsonReader;
    String jsonFileName;
    private final String Test_Data_Path = "src/test/java/TestData/";
    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(Test_Data_Path + jsonFileName+".json"));
            jsonReader = data.toString();
        }
        catch (Exception e) {
            System.out.println("Error reading Json file");
        }
    }
    public String getJsonReader(String path) {
        try {
            return JsonPath.read(jsonReader, path);
        }
        catch (Exception e) {
            System.out.println("Error reading Json file");
            return "";
        }
    }
}
