package git.obamadev.rewrite.module;

import git.obamadev.rewrite.module.modules.render.Arraylist;
import git.obamadev.rewrite.module.modules.misc.DiscordRPC;

import java.util.ArrayList;

import static git.obamadev.rewrite.ObamaMod.SETTINGS_MANAGER;
import static git.obamadev.rewrite.ObamaMod.moduleManager;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        // modules.add(new Class())
        modules.add(new DiscordRPC());
        modules.add(new Arraylist());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void loadModules(){
        SETTINGS_MANAGER.loadSettings();

        for (Module m: moduleManager.getModules()){
            if ((Boolean) m.settings.getSetting("enabled")){
                m.toggle();
            }
        }
    }
}