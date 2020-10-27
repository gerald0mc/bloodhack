package dev.lors.bloodhack.clickgui.comps;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.utils.ColourUtils;
import dev.lors.bloodhack.utils.RenderUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class ModuleComponent extends Component {

    public Module module;
    public int offset;
    public boolean parExpand;
    dev.lors.bloodhack.module.BloodModules.hud.ClickGUI clickGUI = (dev.lors.bloodhack.module.BloodModules.hud.ClickGUI) BloodHack.moduleManager.getModuleByName("ClickGUI");

    public ModuleComponent(Module module, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.width = Math.max(fr.getStringWidth(module.getName()) + 10, width);
        int count = offsetY;
        for (Value value : module.values) {
            ValueComponent comp = new ValueComponent(value, x + 1, count, width, y + count + height);
            comp.parent = this;
            components.add(comp);
            count += offsetY;
        }
        ValueComponentKeybind keybind = new ValueComponentKeybind(module, x + 1, count + offsetY, width, y + count + height);
        keybind.parent = this;
        components.add(keybind);
        this.module = module;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        parExpand = true;
        int color = ColourUtils.toRGBA(clickGUI.r.value, clickGUI.g.value, clickGUI.b.value, clickGUI.a.value);
        int bgColor = clickGUI.rainbow.value ? 0xcc000000 : ColourUtils.toRGBA(clickGUI.br.value, clickGUI.bg.value, clickGUI.bb.value, clickGUI.ba.value);
        color = clickGUI.rainbow.value ? ColourUtils.genRainbow() : color;
        GL11.glPushMatrix();
        RenderUtil.drawSexyRect(x, y + offsetY, x + width, y + height, clickGUI.rainbow.value ? ColourUtils.genRainbow() : bgColor, module.isToggled() ? color : bgColor);
        if (mouseX > x && mouseX < x + width && mouseY > y + offsetY && mouseY < y + height) {
            RenderUtil.drawRect(x, y + offsetY, x + width, y + height, clickGUI.rainbow.value ? ColourUtils.genRainbow() : color);
        }
        fr.drawStringWithShadow(module.getName(), x + 5, y + 16, -1);
        GL11.glPopMatrix();
        if (expanded) {
            int count = offsetY;
            for (Component item : components) {
                item.offsetY = offsetY - 1;
                item.x = x;
                item.y = y + count;
                item.height = height;
                if (item instanceof ValueComponent && ((ValueComponent) item).value.isVisible()) {
                    ValueComponent value = (ValueComponent) item;
                    if (value.value.value instanceof String)
                        item.width = Math.max(fr.getStringWidth(value.value.getMeta() + 10), width);
                    else
                        item.width = Math.max(fr.getStringWidth(value.value.value instanceof Boolean ? value.value.name : value.value.name + ": " + value.value.getMeta() + 10), width);
                } else if (item instanceof ValueComponentKeybind) {
                    ValueComponentKeybind value = (ValueComponentKeybind) item;
                    if (value.module != null)
                        item.width = Math.max(fr.getStringWidth(
                                value.listening ? "Listening..." : "Bind: " +
                                        Keyboard.getKeyName(module.getKey())),
                                width);
                }
                count += offsetY;
                item.render(mouseX, mouseY);
            }
            offset = count - offsetY;
        } else
            offset = 0;
        super.render(mouseX, mouseY);
    }

    @Override
    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        if (mouseX > x && mouseX < x + width && mouseY > y + offsetY && mouseY < y + height) {
            if (mouseButton == 0) {
                module.toggle();
            }
            if (mouseButton == 1) {
                expanded = !expanded;
            }
        }
        if (expanded)
            for (Component item : components) {
                item.mouseClick(mouseX, mouseY, mouseButton);
            }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (expanded)
            for (Component item : components)
                item.keyTyped(typedChar, keyCode);
    }
}
