package git.obamadev.rewrite.command.commands;

import git.obamadev.rewrite.ObamaMod;
import git.obamadev.rewrite.command.Command;
import git.obamadev.rewrite.managers.MessageManager;
import git.obamadev.rewrite.module.Module;
import net.minecraft.util.text.TextFormatting;

public class Toggle extends Command {
    public Toggle() {
        super("Toggle", new String[]{"t", "toggle"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length > 1) {
            try {
                for (Module m : ObamaMod.moduleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(args[1])) {
                        m.toggle();
                        if (m.isToggled()) {
                            MessageManager.sendMessagePrefix(TextFormatting.AQUA + m.getName() + TextFormatting.WHITE + " is now " + TextFormatting.GREEN + "ON");
                        } else {
                            MessageManager.sendMessagePrefix(TextFormatting.AQUA + m.getName() + TextFormatting.WHITE + " is now " + TextFormatting.RED + "OFF");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
