package nl.patrick.HOPE.commands;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.Module.ModuleManager;
import nl.patrick.HOPE.commands.Commands.ToggleCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    public static ArrayList<Command> commands = new ArrayList<Command>();
    public CommandManager() {
        commands.clear();
        commands.add(new ToggleCommand());

        MinecraftForge.EVENT_BUS.register(this);
    }


        @SubscribeEvent
    public void chatEvent(ClientChatEvent event) {
        String commandPrefix = Hope.prefix;
        if (event.getMessage().startsWith(commandPrefix)) {
            boolean commandExists = false;
            if (event.getMessage().length() > 1) {
                String firstArg = event.getMessage().replaceFirst(commandPrefix, "").split(" ")[0];
                // regex by @dominikaaaa from KAMI Blue
                String[] argsList = event.getMessage().replaceFirst(commandPrefix + firstArg, "").split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Split by every space if it isn't surrounded by quotes
                for (Command command : commands) {
                    for (String alias : command.getAliases()) {
                        if (alias.equals(firstArg)) {
                            command.onCommand(argsList);
                            commandExists = true;
                            break;
                        }
                    }
                    if (commandExists) {
                        break;
                    }
                }
                if (!commandExists) {
                }
            } else {
            }event.setCanceled(true);
        }
    }
}
