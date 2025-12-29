package rubfiber.interestingmod.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class guiScreen extends Screen {

    public guiScreen(Component title) {
        super(title);
    }
    EditBox nameBox;
    EditBox redBox;
    EditBox greenBox;
    EditBox blueBox;
    ColorChangerWidget colorChangerWidget;

    public boolean charTyped(@NonNull CharacterEvent event) {
        if (nameBox.isFocused() && nameBox.charTyped(event)) return true;


        if (redBox.isFocused() && redBox.charTyped(event)) return true;
        if (greenBox.isFocused() && greenBox.charTyped(event)) return true;
        if (blueBox.isFocused() && blueBox.charTyped(event)) return true;

        return super.charTyped(event);
    }

    public boolean keyPressed(KeyEvent event) {
        if (event.key() == GLFW.GLFW_KEY_ESCAPE) {
            this.onClose();
            return true;
        }
        if (nameBox.isFocused() && nameBox.keyPressed(event)) return true;


        if (redBox.isFocused() && redBox.keyPressed(event)) return true;
        if (greenBox.isFocused() && greenBox.keyPressed(event)) return true;
        if (blueBox.isFocused() && blueBox.keyPressed(event)) return true;

        return super.keyPressed(event);
    }


    @Override
    protected void init() {
        Font font = this.font;
        nameBox = new EditBox(
                font,
                200,
                40,
                200,
                15,
                Component.literal("Input")
        );
        redBox = new EditBox(font, 200, 125, 40, 15, Component.literal("Input"));
        redBox.setMaxLength(3);
        redBox.setBordered(true);
        redBox.setVisible(false);
        redBox.setFocused(false);
        greenBox = new EditBox(font, 260, 125, 40, 15, Component.literal("Input"));
        greenBox.setMaxLength(3);
        greenBox.setBordered(true);
        greenBox.setVisible(false);
        greenBox.setFocused(false);
        blueBox = new EditBox(font, 320, 125, 40, 15, Component.literal("Input"));
        blueBox.setMaxLength(3);
        blueBox.setBordered(true);
        blueBox.setVisible(false);
        blueBox.setFocused(false);

        nameBox.setMaxLength(64);
        nameBox.setBordered(true);
        nameBox.setVisible(false);
        nameBox.setFocused(false);
        nameBox.setValue("My Waypoint");

        colorChangerWidget = new ColorChangerWidget(300, 300, 100, 100, font);
        colorChangerWidget.setVisible(false);
        this.addRenderableWidget(colorChangerWidget);
        Button buttonWidget = Button.builder(Component.nullToEmpty("Create waypoint"), (btn) -> {
            nameBox.setVisible(true);
            redBox.setVisible(true);
            greenBox.setVisible(true);
            blueBox.setVisible(true);
            colorChangerWidget.setVisible(true);
            String name = nameBox.getValue();



            // When the button is clicked, we can display a toast to the screen.

            //this.minecraft.getToastManager().addToast(
           //         SystemToast.multiline(this.minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.nullToEmpty("Waypoint created!"), Component.nullToEmpty("Successfully created waypoint."))
           // );
        }).bounds(40, 40, 120, 20).build();
        // x, y, width, height
        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        // Register the button widget.
        this.addRenderableWidget(buttonWidget);
        this.addRenderableWidget(nameBox);
        this.addRenderableWidget(redBox);
        this.addRenderableWidget(greenBox);
        this.addRenderableWidget(blueBox);

    }


    @Override
    public void render(@NonNull GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        context.drawString(this.font, "Waypoint Workshop", 40, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);

        if (emptyStringToZero(redBox.getValue()) > 255 && !redBox.isFocused()) redBox.setValue("255");
        if (emptyStringToZero(greenBox.getValue()) > 255 && !greenBox.isFocused()) greenBox.setValue("255");
        if (emptyStringToZero(blueBox.getValue()) > 255 && !blueBox.isFocused()) blueBox.setValue("255");

        colorChangerWidget.changeColor(emptyStringToZero(redBox.getValue()), emptyStringToZero(greenBox.getValue()), emptyStringToZero(blueBox.getValue()));
    }
    public static int emptyStringToZero(String s) {
        return (s.isBlank()) ? 0 : Integer.parseInt(s);
    }
}

