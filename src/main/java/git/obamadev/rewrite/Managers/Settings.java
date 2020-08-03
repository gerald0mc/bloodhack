package git.obamadev.rewrite.Managers;

import git.obamadev.rewrite.ObamaMod;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public Map<String, Object> settings = new HashMap<>();

    public Object addSetting(String settingName, Object defaultValue) {
        ObamaMod.SETTINGS_MANAGER.updateSettings();
        return settings.put(settingName, defaultValue);
    }

    public void setSetting(String settingName, Object newValue) {
        settings.replace(settingName, newValue);
        ObamaMod.SETTINGS_MANAGER.updateSettings();
    }

    public Object getSetting(String settingName) {
        ObamaMod.SETTINGS_MANAGER.loadSettings();
        return settings.get(settingName);
    }

}