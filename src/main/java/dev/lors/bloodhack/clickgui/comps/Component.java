package dev.lors.bloodhack.clickgui.comps;

import dev.lors.bloodhack.BloodHack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Component{

    dev.lors.bloodhack.module.BloodModules.hud.ClickGUI clickGUI = (dev.lors.bloodhack.module.BloodModules.hud.ClickGUI) BloodHack.moduleManager.getModuleByName("ClickGUI");
    FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
    Minecraft mc = Minecraft.getMinecraft();
    List<Component> components = new ArrayList<>();
    Component parent;
    int x, y;
    int dx, dy;
    int width, height;
    int offsetX, offsetY;
    boolean dragging;
    boolean expanded;
    int flags;
    public int FLAG_SLIDER = 0x69;
    public int FLAG_ENUM = 0x420;
    public int FLAG_BOOLEAN = 0x1337;

    public Component(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(int mouseX, int mouseY){

    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton){
        if(!(this instanceof Frame) && expanded)
        for(Component item:components)
            item.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void mouseRelease(int mouseX, int mouseY, int state){
        if(!(this instanceof Frame) && expanded)
        for(Component item:components)
            item.mouseRelease(mouseX, mouseY, state);
    }

    public void mouseClickMove(int mouseX, int mouseY, int mouseButton, float timeSinceLastCLick){
        if(!(this instanceof Frame) && expanded)
        for(Component item:components)
            item.mouseClickMove(mouseX, mouseY, mouseButton, timeSinceLastCLick);
    }

    public void mouseMove(int mouseX, int mouseY){
        if(!(this instanceof Frame) && expanded)
        for(Component item:components)
            item.mouseMove(mouseX, mouseY);
    }

    public boolean hasFlag(int flag)
    {
        return (flags & flag) != 0;
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
    }


}
