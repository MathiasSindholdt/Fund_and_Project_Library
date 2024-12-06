
public class settings_test extends testTemplate {
    @Override
    public boolean test() {
        this.TestName = "settings test";
        boolean darkmode = true;
        String host = "9.9.9.9";
        boolean onlyOneIsNeeded = false;
        settings.setDarkmodeEnabled(darkmode);
        settings.setDatabaseHost(host);
        settings.setOnlyOneIsNeeded(onlyOneIsNeeded);
        settings.writeSettingsToFile();

        settings.setAllSettingsToNull();

        settings.readSettingsFromfile();

        if (!darkmode == settings.getDarkmodeEnabled() ||
                !host.equals(settings.getDatabaseHost()) ||
                !onlyOneIsNeeded == settings.getOnlyOneIsNeeded()) {
            return false;
        }

        return true;
    }

}
