package dev.lors.bloodhack.clickgui.comps;

import dev.lors.bloodhack.module.Module;
import dev.lors.bloodhack.utils.ColourUtils;
import dev.lors.bloodhack.utils.RenderUtil;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class ValueComponentKeybind extends Component {

    Module module;
    boolean listening;

    public ValueComponentKeybind(Module module, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.module = module;
    }

    @Override
    public void render(int mouseX, int mouseY) {
        int bgColor = clickGUI.rainbow.value ? 0xcc000000 : ColourUtils.toRGBA(clickGUI.br.value, clickGUI.bg.value, clickGUI.bb.value, clickGUI.ba.value);
        int color = ColourUtils.toRGBA(clickGUI.r.value, clickGUI.g.value, clickGUI.b.value, clickGUI.a.value);
        GL11.glPushMatrix();
        RenderUtil.drawSexyRect(x, y + offsetY, x + width, y + height, bgColor, bgColor);
        Gui.drawRect(x, y + offsetY, x + width, y + height, clickGUI.rainbow.value ? ColourUtils.genRainbow() : color);
        if (mouseX > x && mouseX < x + width && mouseY > y + offsetY && mouseY < y + height) {
            int[] col = ColourUtils.toRGBAArray(ColourUtils.genRainbow());
            int finalCol = ColourUtils.toRGBA(col[0], col[1], col[2], 0x70);
            Gui.drawRect(x, y + offsetY, x + width, y + height, clickGUI.rainbow.value ? finalCol : 0x22FFFFFF);
        }
        //if(value.value instanceof Boolean)
        //    fr.drawStringWithShadow(value.name, x+5, y+16, -1);
        //else
        fr.drawStringWithShadow(listening ? "Listening..." : "Bind: " + Keyboard.getKeyName(module.getKey()), x + 5, y + 16, -1);
        if (expanded)
            for (Component item : components)
                item.render(mouseX, mouseY);
        GL11.glPopMatrix();
        super.render(mouseX, mouseY);
    }

    @Override
    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        if (mouseX > x && mouseX < x + width && mouseY > y + offsetY && mouseY < y + height) {
            if (mouseButton == 0) {
                listening = true;
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (listening) {
            module.setKey(keyCode);
            listening = false;
        }
    }
}
