package dev.lors.bloodhack.command;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.command.commands.Bind;
import dev.lors.bloodhack.command.commands.Save;
import dev.lors.bloodhack.command.commands.Toggle;
import dev.lors.bloodhack.managers.MessageManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;

public class CommandManager {
    public static HashSet<Command> commands = new HashSet<>();

    public static void init() {
        commands.clear();
        commands.add(new Toggle());
        commands.add(new Bind());
        commands.add(new Save());
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chatEvent(ClientChatEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(BloodHack.prefix)) {
            event.setCanceled(true);
            boolean commandExists = false;
            for (Command c : commands) {
                if (args[0].equalsIgnoreCase(BloodHack.prefix + c.getCommand())) {
                    c.onCommand(args);
                    commandExists = true;
                }
            }
            if (!commandExists)
                for (Command c : commands) {
                    MessageManager.sendMessagePrefix(c.getCommand() + ": " + c.usage);
                }
        }
    }
}
