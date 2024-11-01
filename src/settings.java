import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class settings {
    private static Boolean onlyOneIsNeeded = false;
    private static String DatabaseHost = new String();
    private static Boolean darkmodeEnabled = false;

    public static void setOnlyOneIsNeeded(boolean value) {
        onlyOneIsNeeded = value;
        writeSettingsToFile();
    }

    public static void setDatabaseHost(String newHost) {
        DatabaseHost = newHost;
        writeSettingsToFile();
    }

    public static void setDarkmodeEnabled(boolean value) {
        darkmodeEnabled = value;
        writeSettingsToFile();
    }

    public static Boolean getOnlyOneIsNeeded(){
        return onlyOneIsNeeded;
    }

    public static Boolean getDarkmodeEnabled(){
        return darkmodeEnabled;
    }

    public static String getDatabaseHost(){
        return DatabaseHost;
    }

    public static void setAllSettingsToNull(){
        onlyOneIsNeeded = null;
        DatabaseHost=null;
        darkmodeEnabled=null;
    }

    public static void readSettingsFromfile() {
        try {
            File settings = new File("settings.ini");
            Scanner scan = new Scanner(settings);
            String tmpstr = new String();
            while (scan.hasNextLine()) {
                tmpstr = scan.nextLine();
                String[] tmparr = tmpstr.split("=");
                switch (tmparr[0]) {
                    case "onlyOneIsNeeded":
                        setOnlyOneIsNeeded(Boolean.parseBoolean(tmparr[1]));
                        break;
                    case "DatabaseHost":
                        setDatabaseHost(tmparr[1]);
                        break;
                    case "darkmodeEnabled":
                        setDarkmodeEnabled(Boolean.parseBoolean(tmparr[1]));
                        break;
                    default:
                        System.out.println("invalid field in settings.ini: " + tmparr[0]);
                        break;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to find settings.ini");
        }

    }

    public static void writeSettingsToFile() {

        try {
            FileWriter settings = new FileWriter("settings.ini");
            if (onlyOneIsNeeded != null) {
                settings.write("onlyOneIsNeeded=" + onlyOneIsNeeded.toString() + "\n");
            }
            if (DatabaseHost != null) {
                settings.write("DatabaseHost=" + DatabaseHost + "\n");
            }
            if (darkmodeEnabled != null) {
                settings.write("darkmodeEnabled=" + darkmodeEnabled.toString() + "\n");
            }

            settings.close();
        } catch (IOException e) {
            System.out.println("unable to write to settings.ini");
        }
    }

}
