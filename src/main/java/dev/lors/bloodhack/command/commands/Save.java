package dev.lors.bloodhack.command.commands;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.command.Command;
import dev.lors.bloodhack.managers.ConfigManager;
import dev.lors.bloodhack.managers.MessageManager;
import dev.lors.bloodhack.module.Module;
import net.minecraft.util.text.TextFormatting;

public class Save extends Command {
    public Save() {
        super("save", "Saves all settings.");
    }

    @Override
    public void onCommand(String[] args) {
        BloodHack.configManager.save();
    }
}
