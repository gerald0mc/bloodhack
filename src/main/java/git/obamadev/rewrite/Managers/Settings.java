package git.obamadev.rewrite.managers;

import git.obamadev.rewrite.ObamaMod;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public Map<String, Object> settings = new HashMap<>();

    public Object addSetting(String settingName, Object defaultValue) {
        ObamaMod.settingsManager.updateSettings();
        return settings.put(settingName, defaultValue);
    }

    public void setSetting(String settingName, Object newValue) {
        settings.replace(settingName, newValue);
        ObamaMod.settingsManager.updateSettings();
    }

    public Object getSetting(String settingName) {
        ObamaMod.settingsManager.loadSettings();
        return settings.get(settingName);
    }
}