
public class settings_test{
    public static void main(String[] args){
        boolean darkmode= true;
        String host = "9.9.9.9";
        boolean onlyOneIsNeeded=false;
        settings.setDarkmodeEnabled(darkmode);
        settings.setDatabaseHost(host);
        settings.setOnlyOneIsNeeded(onlyOneIsNeeded);
        settings.writeSettingsToFile();

        settings.setAllSettingsToNull();

        settings.readSettingsFromfile();

        assert darkmode == settings.getDarkmodeEnabled();
        assert host == settings.getDatabaseHost();
        assert onlyOneIsNeeded == settings.getOnlyOneIsNeeded();
        System.out.println("Asserts passed successfully");
    }

}
