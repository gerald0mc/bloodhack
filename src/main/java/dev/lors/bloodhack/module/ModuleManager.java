package dev.lors.bloodhack.module;

import com.google.common.util.concurrent.FakeTimeLimiter;
import dev.lors.bloodhack.module.BloodModules.chat.*;
import dev.lors.bloodhack.module.BloodModules.combat.*;
import dev.lors.bloodhack.module.BloodModules.hud.*;
import dev.lors.bloodhack.module.BloodModules.misc.*;
import dev.lors.bloodhack.module.BloodModules.movement.*;
import dev.lors.bloodhack.module.BloodModules.player.FakePlayer;
import dev.lors.bloodhack.module.BloodModules.render.*;

import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
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



    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}