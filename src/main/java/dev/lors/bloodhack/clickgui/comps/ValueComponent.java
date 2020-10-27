package dev.lors.bloodhack.clickgui.comps;

import dev.lors.bloodhack.BloodHack;
import dev.lors.bloodhack.managers.Value;
import dev.lors.bloodhack.utils.ColourUtils;
import dev.lors.bloodhack.utils.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class ValueComponent extends Component{

    public Value value;
    public int offset;
    public boolean slider;
    boolean hasSlider;
    float sliderWidth;
    boolean listening;
    String typeCache = "";
    dev.lors.bloodhack.module.BloodModules.hud.ClickGUI clickGUI = (dev.lors.bloodhack.module.BloodModules.hud.ClickGUI) BloodHack.moduleManager.getModuleByName("ClickGUI");

    public ValueComponent(Value value, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.width = Math.max(fr.getStringWidth(value.name + ": " + value.getMeta())+10, width);
        this.value = value;
        if(value.getValue() instanceof Number && !(value.getValue() instanceof Enum)){
            flags |= FLAG_SLIDER;
        }
    }

    @Override
    public void render(int mouseX, int mouseY) {
        int bgColor = clickGUI.rainbow.value ? 0xcc000000 : ColourUtils.toRGBA(clickGUI.br.value, clickGUI.bg.value, clickGUI.bb.value, clickGUI.ba.value);
        int color = ColourUtils.toRGBA(clickGUI.r.value, clickGUI.g.value, clickGUI.b.value, clickGUI.a.value);
        GL11.glPushMatrix();
        RenderUtil.drawSexyRect(x, y+offsetY, x+width, y+height, bgColor, bgColor);
        if(value.value instanceof Boolean)
            Gui.drawRect(x, y+offsetY, x+width, y+height,((((Boolean) value.value)) ? clickGUI.rainbow.value? ColourUtils.genRainbow() : color : bgColor));
        if(value.value instanceof Enum)
            Gui.drawRect(x, y+offsetY, x+width, y+height,clickGUI.rainbow.value? ColourUtils.genRainbow() : color);

        if(hasFlag(FLAG_SLIDER))
            drawSlider(color);
        if(mouseX > x && mouseX < x+width && mouseY > y+offsetY && mouseY < y+height){
            if(!value.desc.equals("")) {
                ScaledResolution sr = new ScaledResolution(mc);
                fr.drawStringWithShadow("Description: " + value.desc, 0, sr.getScaledHeight()-fr.FONT_HEIGHT, -1);
            }
            int [] col = ColourUtils.toRGBAArray(ColourUtils.genRainbow());
            int finalCol = ColourUtils.toRGBA(col[0], col[1], col[2], 0x70);
            Gui.drawRect(x, y+offsetY, x+width, y+height,clickGUI.rainbow.value ?finalCol : 0x22FFFFFF);
        }
        if(value.value instanceof String)
            fr.drawStringWithShadow(listening ? typeCache + "_" : value.getMeta(), x+5, y+16, -1);
        else
            fr.drawStringWithShadow(value.value instanceof Boolean ? value.name : value.name + ": " + value.getMeta(), x+5, y+16, -1);
        if(expanded)
            for(Component item:components)
                item.render(mouseX, mouseY);
        GL11.glPopMatrix();
        super.render(mouseX, mouseY);
    }

    public void drawSlider(int color){
        float scale = calculateXPos(value);
        RenderUtil.drawSexyRect(x, y+offsetY, x+width, y+height, 0xcc000000, 0xcc000000);
        Gui.drawRect(x, y+offsetY, (int) (x+(scale > width ? width : scale)), y+height, clickGUI.rainbow.value ? ColourUtils.genRainbow() : color);
    }

    @Override
    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        if(value.isVisible())
        if(mouseX > x && mouseX < x+width && mouseY > y+offsetY && mouseY < y+height) {
            if (mouseButton == 0) {
                    handleMouseClick();
            }
        }
    }


    private void handleMouseClick() {
        if(value.value instanceof Boolean){
            value.setValue(!((Boolean) value.value));
        }
        if(value.value instanceof Enum){
            Enum val = (Enum) value.value;
            value.setValue(getNextEnum(getEnumIndex(val), val));
        }
        if(value.value instanceof String) {
            listening = !listening;
        }
        if(hasFlag(FLAG_SLIDER)) {
            slider = !slider;
        }
    }

    public int getEnumIndex(Enum enu){
        int index = 0;
        for(Enum en:enu.getClass().getEnumConstants()){
            if(enu == en)
                break;
            index++;
        }
        return index;
    }

    public Enum getNextEnum(int index, Enum enu){
        Enum[] array = enu.getClass().getEnumConstants();
        if(index+1 >= array.length)
            return array[0];
        else
            return array[index+1];
    }

    public float calculateXPos(final Value val) {
        float minX = x;
        float maxX = x + width;
        if (val.max == null)
            return minX;
        Number l_Val = (Number) val.value;
        Number l_Max = (Number) val.max;
        return Math.round(maxX- minX) * (l_Val.floatValue() / l_Max.floatValue());
    }

    @Override
    public void mouseRelease(int mouseX, int mouseY, int state) {
        if(value.isVisible())
        if(slider)
            slider = false;
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int r, float e) {
        if(parent.expanded && slider) {
                int difference = ((Number) this.value.max).intValue() - ((Number) this.value.min).intValue();
                float percent = (((float) mouseX - x) / ((float) width + 7.4f));
                if (this.value.getValue() instanceof Double) {
                    double result = (Double) this.value.min + (double) ((float) difference * percent);
                    value.setValue(((double) Math.round(10.0 * result) / 10.0));
                } else if (this.value.getValue() instanceof Float) {
                    float result = (Float) this.value.min + (float) difference * percent;
                    value.setValue(((float) Math.round(10.0f * result) / 10.0f));
                } else if (this.value.getValue() instanceof Integer) {
                    value.setValue(((Integer) this.value.min + (int) ((float) difference * percent)));
                }
            if(this.value.value instanceof Integer) {
                if (((Number) this.value.value).intValue() <= ((Number) this.value.min).intValue())
                    this.value.value = this.value.min;
                if (((Number) this.value.value).intValue() >= ((Number) this.value.max).intValue())
                    this.value.value = this.value.max;
            }
            if(this.value.value instanceof Float) {
                if (((Number) this.value.value).floatValue() <= ((Number) this.value.min).floatValue())
                    this.value.value = this.value.min;
                if (((Number) this.value.value).floatValue() >= ((Number) this.value.max).floatValue())
                    this.value.value = this.value.max;
            }
            if(this.value.value instanceof Double) {
                if (((Number) this.value.value).doubleValue() <= ((Number) this.value.min).doubleValue())
                    this.value.value = this.value.min;
                if (((Number) this.value.value).doubleValue() >= ((Number) this.value.max).doubleValue())
                    this.value.value = this.value.max;
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if(listening) {
            if(keyCode == 14 && typeCache.length() > 0) {
                typeCache = typeCache.substring(0, typeCache.length() - 1);
                return;
            }
            if(keyCode == 28){
                listening = !listening;
                value.setValue(typeCache.substring(0, typeCache.length()));
                return;
            }
            typeCache += typedChar;
        }
    }
}
