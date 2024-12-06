import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class settings_Test extends testTemplate {
    @Test
    public void settings_test() {
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

        assertEquals(darkmode, settings.getDarkmodeEnabled());
        assertTrue(host.equals(settings.getDatabaseHost()));
        assertEquals(onlyOneIsNeeded, settings.getOnlyOneIsNeeded());

    }

}
