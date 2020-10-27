package dev.lors.bloodhack.clickgui;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.clickgui.comps.Frame;
import dev.lors.bloodhack.module.Category;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;

public class ClickGUI extends GuiScreen {

    ArrayList<Frame> frames = new ArrayList<>();
    dev.lors.bloodhack.module.BloodModules.hud.ClickGUI clickGUI = (dev.lors.bloodhack.module.BloodModules.hud.ClickGUI) BloodHack.moduleManager.getModuleByName("ClickGUI");

    public ClickGUI() {
        int count = 0;

        for (Category category : Category.values()) {
            frames.add(new Frame(category, 28 + count, 0));
            count += 110;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Frame frame : frames) {
            frame.render(mouseX, mouseY);
            frame.mouseMove(mouseX, mouseY);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Frame frame : frames) {
            frame.mouseRelease(mouseX, mouseY, state);
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Frame frame : frames) {
            frame.mouseClick(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for (Frame frame : frames) {
            frame.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        }
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (Frame frame : frames) {
            frame.keyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }
}
