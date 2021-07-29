package helpers;

import com.google.gson.Gson;
import models.EventClass;
import models.ServerLogClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/*
Author : Darshana Patil
*/
public class ParserClass {
    public void parseLogs(BufferedReader reader, DAOClass dao) throws IOException, SQLException {
        HashMap<String, ServerLogClass> eventMap = new HashMap<>();
        Gson gson = new Gson();
        String line;
        while ((line = reader.readLine()) != null) {
            ServerLogClass log = gson.fromJson(line, ServerLogClass.class);
            String eventId = log.getId();
            if (!eventMap.containsKey(eventId)) {
                eventMap.put(eventId, log);
                continue;
            }

            ServerLogClass previousLog = eventMap.remove(eventId);
            long duration = Math.abs(log.getTimeStamp() - previousLog.getTimeStamp());
            boolean alert = false;
            if (duration > 4) {
                alert = true;
            }

            EventClass event = new EventClass.Builder(eventId, duration, alert)
                    .withHost(log.getHost())
                    .withType(log.getType())
                    .build();
            dao.writeEvent(event);
        }
    }
}
