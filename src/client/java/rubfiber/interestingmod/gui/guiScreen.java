package rubfiber.interestingmod.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;
import org.lwjgl.glfw.GLFW;

public class guiScreen extends Screen {

    public guiScreen(Component title) {
        super(title);
    }
    EditBox nameBox;



    public boolean charTyped(@NonNull CharacterEvent event) {
        return nameBox.isVisible() && nameBox.charTyped(event);
    }

    public boolean keyPressed(KeyEvent event) {
        if (event.key() == GLFW.GLFW_KEY_ESCAPE) {
            this.onClose();
            return true;
        }
     //   if (event.key() == GLFW.GLFW_KEY_ENTER) {
       //     nameBox.setFocused(false);
       // }

        return nameBox.isVisible() && nameBox.keyPressed(event);
    }
    public boolean mouseClicked(MouseButtonEvent event) {
        // Let the textbox try first
        if (nameBox.isVisible() && nameBox.mouseClicked(event, true)) {
            return true; // EditBox focused itself
        }

        // Clicked outside → unfocus
        if (nameBox.isFocused()) {
            nameBox.setFocused(false);
        }

        // Let Screen handle buttons, etc.
        return super.mouseClicked(event, true);
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
        nameBox.setMaxLength(64);
        nameBox.setBordered(true);
        nameBox.setVisible(false);
        nameBox.setFocused(false);
        nameBox.setValue("My Waypoint");

        ColorChangerWidget colorChangerWidget = new ColorChangerWidget(300, 300, 100, 100, font);
        colorChangerWidget.setVisible(false);
        this.addRenderableWidget(colorChangerWidget);
        Button buttonWidget = Button.builder(Component.nullToEmpty("Create waypoint"), (btn) -> {
            nameBox.setVisible(true);
            nameBox.setFocused(true);
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

    }


    @Override
    public void render(@NonNull GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        context.drawString(this.font, "Waypoint builder", 40, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
    }
}

