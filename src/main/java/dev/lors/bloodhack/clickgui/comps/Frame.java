package dev.lors.bloodhack.clickgui.comps;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.module.Category;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.module.ModuleManager;
import dev.lors.bloodhack.utils.ColourUtils;
import dev.lors.bloodhack.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Frame extends Component{

    List<Component> components = new ArrayList<>();
    private Category category;
    int offsetX, offsetY;
    int offset;
    boolean dragging;
    private boolean expanded;
    dev.lors.bloodhack.module.BloodModules.hud.ClickGUI clickGUI = (dev.lors.bloodhack.module.BloodModules.hud.ClickGUI) BloodHack.moduleManager.getModuleByName("ClickGUI");

    public Frame(Category category, int x, int y){
        super(x, y, 70, 27);
        this.category = category;
        this.x = x;
        this.y = y;
        offsetY = 13;
        int count = offsetY;
        for(Module module: BloodHack.moduleManager.getModulesByCategory(category)) {
            Component comp = new ModuleComponent(module, x, count, width, y + count + height);
            comp.parent = this;
            components.add(comp);
            count += offsetY;
        }
    }

    public void render(int mouseX, int mouseY){
        if (Mouse.getEventDWheel() > 0)
        {
            y -= 1;
        }
        else if (Mouse.getEventDWheel() < 0)
        {
            y += 1;
        }
        if(dragging){
            x = mouseX-dx;
            y = mouseY-dy;
        }
        int color = ColourUtils.toRGBA(clickGUI.r.value, clickGUI.g.value, clickGUI.b.value, clickGUI.a.value);
        int bgColor = clickGUI.rainbow.value ? 0xcc000000 : ColourUtils.toRGBA(clickGUI.br.value, clickGUI.bg.value, clickGUI.bb.value, clickGUI.ba.value);
        GL11.glPushMatrix();
        RenderUtil.drawSexyRect(x, y+offsetY, x+width+32, y+height, clickGUI.rainbow.value ? ColourUtils.genRainbow() : bgColor, bgColor);
        if(mouseX > x && mouseX < x+width && mouseY > y+offsetY && mouseY < y+height){
            RenderUtil.drawRect(x, y+offsetY, x+width+32, y+height, clickGUI.rainbow.value ? ColourUtils.genRainbow() : color);
        }
        fr.drawStringWithShadow(category.name(), x+5, y+16, -1);
        if(expanded)
            for(Component item:components)
                item.render(mouseX, mouseY);
        else
            for(Component item:components)
                ((ModuleComponent)item).parExpand = false;
        GL11.glPopMatrix();
        int count = offsetY;
        for(Component item:components){
            if(item instanceof ModuleComponent) {
                item.offsetY = offsetY+1;
                item.x = x;
                item.y = y + count;
                item.height = height;
                item.width = width+32;
                count += ((ModuleComponent) item).offset;
            }
            count += offsetY;
        }
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton){
        if(mouseX > x && mouseX < x+width && mouseY > y-offsetY && mouseY < y+height) {
            if (mouseButton == 1) {
                if(expanded)
                    expanded = false;
                else
                    expanded = true;
            }
            if (mouseButton == 0)
                dragging = true;
            dx = mouseX - x;
            dy = mouseY - y;
        }
        if(expanded)
            for(Component item:components)
                item.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void mouseRelease(int mouseX, int mouseY, int state){
        if(dragging)
            dragging = false;
        for(Component item:components)
            item.mouseRelease(mouseX, mouseY, state);
    }

    public void mouseClickMove(int mouseX, int mouseY, int mouseButton, float timeSinceLastCLick){
        for(Component item:components)
            item.mouseClickMove(mouseX, mouseY, mouseButton, timeSinceLastCLick);
    }

    @Override
    public void mouseMove(int mouseX, int mouseY) {
        for(Component item:components)
            item.mouseMove(mouseX, mouseY);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        for(Component component:components)
            component.keyTyped(typedChar, keyCode);
    }
}
