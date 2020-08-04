package git.obamadev.rewrite.managers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.module.Module;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    File configFile = new File("obamaconf.json");

    public Map<String, Settings> readSettings() {
        Map<String, Settings> settingsArray = new HashMap<>();
        if (configFile.exists() && configFile.isFile()) {
            try {
                settingsArray = gson.fromJson(new FileReader(configFile), new TypeToken<Map<String, Settings>>(){}.getType());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter fw = new FileWriter(configFile);
                gson.toJson(settingsArray, fw);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return settingsArray;
    }

    public void writeSettings(Map<String, Settings> settingsArray) {
        try {
            FileWriter fw = new FileWriter(configFile);
            gson.toJson(settingsArray, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSettings() {
        Map<String, Settings> settingsArray = new HashMap<>();
        for (Module module : ObamaMod.moduleManager.getModules()) {
            settingsArray.put(module.getName(), module.settings);
        }
        writeSettings(settingsArray);
    }

    public void loadSettings() {
        Map<String, Settings> settingsArray = readSettings();
        for (Module module : ObamaMod.moduleManager.getModules()) {
            String moduleName = module.getName();
            try {
                if (settingsArray.containsKey(moduleName)) {
                    module.setSettings(settingsArray.get(moduleName));
                } else {
                    module.registerSettings();
                }
            } catch (NullPointerException npe) {
                module.registerSettings();
            }
        }
        updateSettings();
    }
}