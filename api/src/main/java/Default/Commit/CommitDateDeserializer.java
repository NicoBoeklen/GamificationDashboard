package Default.Commit;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommitDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();
        JsonNode commitNode = node.get("commit");
        if (commitNode != null) {
            JsonNode authorNode = commitNode.get("author");
            if (authorNode != null) {
                JsonNode dateNode = authorNode.get("date");
                if (dateNode != null) {
                    String dateString = dateNode.asText();
                    System.out.println(dateString);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        return dateFormat.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace(); // Handle parse exception
                    }
                }
            }
        }
        return null;
    }
}

