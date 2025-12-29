package rubfiber.interestingmod.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class ColorChangerWidget extends AbstractWidget {
    int color = 0xFFFFFFFF;
    public void changeColor(int r, int g, int b) {
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));

        color = ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF) | (0xFF << 24);
    }
    Font font;
    public ColorChangerWidget(int x, int y, int width, int height, Font font) {
        super(x, y, width, height, Component.empty());
        this.font = font;
    }
    public void setVisible(boolean bl) { visible = bl; }


    @Override
    protected void renderWidget(@NonNull GuiGraphics context, int mouseX, int mouseY, float delta) {
        int rectangleX = 375;
        int rectangleY = 120;
        int rectangleWidth = 25;
        int rectangleHeight = 25;


// x1, y1, x2, y2, color
        if (visible) {
            context.drawString(font, "RGB Value adjuster", 250, 125 - this.font.lineHeight - 10, 0xFFFFFFFF, true);

            context.drawString(font, "R", 190, 148 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
            context.drawString(font, "G", 250, 148 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
            context.drawString(font, "B", 310, 148 - this.font.lineHeight - 10, 0xFFFFFFFF, true);



            context.fill(rectangleX, rectangleY, rectangleX + rectangleWidth, rectangleY + rectangleHeight, color);
        }


    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput builder) {
        // For brevity, we'll just skip this for now - if you want to add narration to your widget, you can do so here.
        return;
    }
}
