package nl.patrick.HOPE.commands;

import net.minecraft.client.Minecraft;

public class Command {
    protected Minecraft mc = Minecraft.getMinecraft();

    private String name;
    private String description;
    private String[] aliases;

    public Command(String name, String[] aliases, String description) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
    }

    public void onCommand(String[] args) {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}