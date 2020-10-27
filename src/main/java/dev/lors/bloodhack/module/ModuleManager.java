package dev.lors.bloodhack.module;

import com.google.common.util.concurrent.FakeTimeLimiter;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.BloodModules.chat.*;
import dev.lors.bloodhack.module.BloodModules.combat.*;
import dev.lors.bloodhack.module.BloodModules.hud.*;
import dev.lors.bloodhack.module.BloodModules.misc.*;
import dev.lors.bloodhack.module.BloodModules.movement.*;
import dev.lors.bloodhack.module.BloodModules.player.FakePlayer;
import dev.lors.bloodhack.module.BloodModules.render.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() throws IllegalAccessException {
        // modules.add(new Class())

        ///Combat

        modules.add(new BedAura());
        modules.add(new BetterAC());
        modules.add(new AutoArmor());
        modules.add(new AutoCrystal());
        modules.add(new AutoTotem());
        modules.add(new BowSpam());

        ///Movement

        modules.add(new Sanic());
        modules.add(new HoleTP());
        modules.add(new Sprint());

        ///Render

        modules.add(new Fullbright());
        //modules.add(new HoleESP());

        ///Chat

        modules.add(new AutoSuicide());
        modules.add(new PrefixChat());
        modules.add(new ChatSuffix());
        modules.add(new TotemPopAnnouncer());

        ///misc

        modules.add(new DiscordRPC());
        modules.add(new WeaknessAlert());
        modules.add(new EntityAlert());

        ///ui

        modules.add(new Watermark());
        modules.add(new Arraylist());
        modules.add(new ClickGUI());
        modules.add(new Welcomer());

        ///player

        modules.add(new FakePlayer());

        ///Testing area, move up if module works

        for(Module module:modules){
            for (Field field : module.getClass().getDeclaredFields()) {
                if (Value.class.isAssignableFrom(field.getType())) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final Value val = (Value) field.get(module);
                    val.module = module;
                    module.values.add(val);
                }
            }
        }

    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Module> getModulesByCategory(Category category){
        List<Module> list = new ArrayList<>();
        for(Module module:modules)
            if(module.getCategory().equals(category))
                list.add(module);
        return list;
    }
}