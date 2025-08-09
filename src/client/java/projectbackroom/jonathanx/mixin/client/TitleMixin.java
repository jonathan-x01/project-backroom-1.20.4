package projectbackroom.jonathanx.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.TextIconButtonWidget;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectbackroom.jonathanx.ProjectBackroom;
import projectbackroom.jonathanx.gui.screen.DiscordButtons;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Mixin(TitleScreen.class)
public class TitleMixin extends Screen {
    @Unique
    @SuppressWarnings("all")
    private float backgroundAlpha = 1.0F;
    @Unique
    private static final String panoramaPrefix = "textures/gui/title/";
    @Unique
    private static final List<CubeMapRenderer> MENU_TEXTURES = List.of(
            new CubeMapRenderer(ProjectBackroom.id(getPanoramaPath("panorama_0")))
            /*new CubeMapRenderer(ProjectBackroom.id(getPanoramaPath("panorama_1")))*/
    );
    @Unique
    private static List<RotatingCubeMapRenderer> ROTATING_PANORAMA_RENDERERS = null;

    @Unique
    private static int counter;
    @Unique
    private static int panoramaCooldown = 0;

    @Unique
    private float panoramaPitch = 0;

    protected TitleMixin(Text title) {
        super(title);
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/TitleScreen;renderPanoramaBackground(Lnet/minecraft/client/gui/DrawContext;F)V"
            )
    )
    private void redirectRenderPanoramaBackground(TitleScreen instance, DrawContext context, float delta){
        if (!ROTATING_PANORAMA_RENDERERS.isEmpty()){

            int normalToCursedTime = Random.create().nextBetween(20 * 6, 20 * 9);
            int cursedCooldown = Random.create().nextBetween(20 * 4, 20 * 7);

            int randomCursedPanorama = Random.create().nextBetween(0, ROTATING_PANORAMA_RENDERERS.size() - 1);

            counter++;
            if (panoramaCooldown > 0){
                panoramaCooldown--;
                renderCustomPanoramaBackground(ROTATING_PANORAMA_RENDERERS.get(randomCursedPanorama), context, delta);
            } else {
                renderCustomPanoramaBackground(ROTATING_PANORAMA_RENDERER, context, delta);
            }

            if (counter % normalToCursedTime == 0) {
                panoramaCooldown = cursedCooldown;
            }

            if (counter > 1000){
                counter = 0;
            }
        } else {
            renderCustomPanoramaBackground(ROTATING_PANORAMA_RENDERER, context, delta);
        }
    }

    @Unique
    private void renderCustomPanoramaBackground(RotatingCubeMapRenderer cubeMapRenderer, DrawContext context, float delta){
        ((RotatingCubeMapRendererAccessor) cubeMapRenderer).setPitch(panoramaPitch);
        cubeMapRenderer.render(context, this.width, this.height, this.backgroundAlpha, delta);
        panoramaPitch = ((RotatingCubeMapRendererAccessor) cubeMapRenderer).getPitch();
    }

    @Inject(method = "addNormalWidgets", at = @At("HEAD"))
    public void onInsertNormalWidgets(int y, int spacingY, CallbackInfoReturnable<Integer> cir){

        TextIconButtonWidget textIconButtonWidget3 = this.addDrawableChild(
                DiscordButtons.createDiscordButton(20, button -> {
                    try {
                        Util.getOperatingSystem().open(new URI("https://discord.gg/7CAJZvB9WZ"));
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                },true)
        );

        textIconButtonWidget3.setPosition(5, 5);
    }

    @Unique
    @SuppressWarnings("all")
    private static String getPanoramaPath(String name){
        return "textures/gui/title/" + name + "/panorama";
    }

    static {
        List<RotatingCubeMapRenderer> list = new ArrayList<>();
        for (CubeMapRenderer cubeMapRenderer : MENU_TEXTURES){
            list.add(new RotatingCubeMapRenderer(cubeMapRenderer));
        }
        ROTATING_PANORAMA_RENDERERS = list;
    }
}