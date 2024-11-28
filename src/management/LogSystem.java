package management;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;

public class LogSystem {
    private final String logFileName = "logs/system_logs.txt";
    private final String archiveDir = "logs/archived/";

    // Ensure log file and archive folder exist
    public LogSystem() {
        try {
            File logFile = new File(logFileName);
            if (logFile.getParentFile().mkdirs()) {
                System.out.println("Log directory created.");
            }
            if (logFile.createNewFile()) {
                System.out.println("Log file created: " + logFileName);
            }
            File archiveFolder = new File(archiveDir);
            if (archiveFolder.mkdirs()) {
                System.out.println("Archive directory created.");
            }
        } catch (IOException e) {
            System.err.println("Error initializing log system: " + e.getMessage());
        }
    }

    // Write log entry using character streams
    public void logEvent(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
            writer.write(LocalDate.now() + ": " + message);
            writer.newLine();
            System.out.println("Log entry added: " + message);
        } catch (IOException e) {
            System.err.println("Error writing log entry: " + e.getMessage());
        }
    }

    // Read logs
    public void readLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFileName))) {
            String line;
            System.out.println("Log contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }
    }

    // Archive current log file
    public void archiveLogFile() {
        try {
            Path source = Paths.get(logFileName);
            Path target = Paths.get(archiveDir + "system_logs_" + LocalDate.now() + ".txt");
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Log file archived to: " + target);
            // Create a new empty log file
            new File(logFileName).createNewFile();
        } catch (IOException e) {
            System.err.println("Error archiving log file: " + e.getMessage());
        }
    }

    // Delete archived logs older than 7 days
    public void deleteOldLogs() {
        File archiveFolder = new File(archiveDir);
        File[] files = archiveFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith("system_logs")) {
                    if (file.lastModified() < System.currentTimeMillis() - (7L * 24 * 60 * 60 * 1000)) {
                        if (file.delete()) {
                            System.out.println("Deleted old log: " + file.getName());
                        }
                    }
                }
            }
        }
    }
}
