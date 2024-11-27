package management;

import java.io.FileWriter;
import java.io.IOException;

public class LogSystem {
    public void logEvent(String event) {
        try (FileWriter writer = new FileWriter("logs/system_logs.txt", true)) {
            writer.write(event + "\n");
            System.out.println("Logged: " + event);
        } catch (IOException e) {
            System.err.println("Error logging event: " + e.getMessage());
        }
    }
}