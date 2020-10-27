package dev.lors.bloodhack.managers;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.util.EnumConverter;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import static dev.lors.bloodhack.BloodHack.moduleManager;


public class ConfigManager {
    public File BloodHack;
    public File Settings;

    public ConfigManager() {
        this.BloodHack = new File("BloodHack");
        if (!this.BloodHack.exists()) {
            this.BloodHack.mkdirs();
        }

        this.Settings = new File("BloodHack" + File.separator + "Settings");
        if (!this.Settings.exists()) {
            this.Settings.mkdirs();
        }
        load();
    }

    public void save() {
        try {
            for (Module module : moduleManager.getModules()) {
                File moduleFile = new File(BloodHack.getAbsolutePath(), "Settings/" + module.getCategory() + "/" + module.getName()  + ".json");
                moduleFile.getParentFile().mkdirs();
                if(!moduleFile.exists())
                moduleFile.createNewFile();
                JsonObject object = new JsonObject();
                object.addProperty("bind", Keyboard.getKeyName(module.getKey()));
                object.addProperty("enabled", module.isToggled());
                for (Value value : module.values) {
                    if(value.value instanceof Boolean)
                        object.addProperty(value.name, (Boolean)value.value);
                    if(value.value instanceof Number)
                        object.addProperty(value.name, (Number)value.value);
                    if(value.value instanceof String)
                        object.addProperty(value.name, (String)value.value);
                    if(value.value instanceof Enum)
                        object.addProperty(value.name, ((Enum)value.value).name());
                }
                FileWriter fileWriter = new FileWriter(moduleFile);
                fileWriter.write(object.toString());
                fileWriter.flush();
                fileWriter.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            this.save();
        }
    }

    public void load() {
        try {
            for (Module module : moduleManager.getModules()) {
                File moduleFile = new File(BloodHack.getAbsolutePath(), "Settings/" + module.getCategory() + "/" + module.getName() + ".json");
                moduleFile.getParentFile().mkdirs();
                if(!moduleFile.exists())
                moduleFile.createNewFile();
                String content = Files.readAllLines(moduleFile.toPath()).stream().collect(Collectors.joining());
                JsonObject object = new JsonParser().parse(content).getAsJsonObject();
                int bind = Keyboard.getKeyIndex(object.get("bind").getAsString());
                module.setKey(bind);
                module.toggled = object.get("enabled").getAsBoolean();
                for (Value value : module.values) {
                    JsonElement element = object.get(value.name);
                    if(value.value instanceof Boolean)
                        value.value = element.getAsBoolean();
                    if(value.value instanceof Number) {
                        if(value.value instanceof Integer)
                            value.value = element.getAsNumber().intValue();
                        if(value.value instanceof Double)
                            value.value = element.getAsNumber().doubleValue();
                        if(value.value instanceof Float)
                            value.value = element.getAsNumber().floatValue();
                        if(value.value instanceof Long)
                            value.value = element.getAsNumber().longValue();
                        if(value.value instanceof Byte)
                            value.value = element.getAsNumber().byteValue();
                        if(value.value instanceof Short)
                            value.value = element.getAsNumber().shortValue();
                    }
                    if(value.value instanceof String)
                        value.value = element.getAsString();
                    if(value.value instanceof Enum) {
                        EnumConverter converter = new EnumConverter(((Enum)value.getValue()).getClass());
                        value.value = converter.doBackward(element);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            save();
        }
    }
}