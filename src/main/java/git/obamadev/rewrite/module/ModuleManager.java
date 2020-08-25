package git.obamadev.rewrite.module;

import git.obamadev.rewrite.module.modules.chat.ChatSuffix;
import git.obamadev.rewrite.module.modules.chat.PrefixChat;
import git.obamadev.rewrite.module.modules.combat.AutoCrystal;
import git.obamadev.rewrite.module.modules.combat.AutoTotem;
import git.obamadev.rewrite.module.modules.hud.Arraylist;
import git.obamadev.rewrite.module.modules.hud.ClickGUI;
import git.obamadev.rewrite.module.modules.hud.Watermark;
import git.obamadev.rewrite.module.modules.hud.Welcomer;
import git.obamadev.rewrite.module.modules.misc.DiscordRPC;
import git.obamadev.rewrite.module.modules.misc.EntityAlert;
import git.obamadev.rewrite.module.modules.misc.VBuckGenerator;
import git.obamadev.rewrite.module.modules.misc.WeaknessAlert;
import git.obamadev.rewrite.module.modules.movement.HoleTP;
import git.obamadev.rewrite.module.modules.movement.Sprint;
import git.obamadev.rewrite.module.modules.render.RainRemover;

import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        // modules.add(new Class())
        modules.add(new DiscordRPC());
        modules.add(new Arraylist());
        modules.add(new Sprint());
        modules.add(new RainRemover());
        modules.add(new EntityAlert());
        modules.add(new ChatSuffix());
        modules.add(new Watermark());
        modules.add(new PrefixChat());
        modules.add(new WeaknessAlert());
        modules.add(new ClickGUI());
        modules.add(new Welcomer());
        modules.add(new AutoCrystal());
        modules.add(new AutoTotem());
        modules.add(new VBuckGenerator());
        modules.add(new HoleTP());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}