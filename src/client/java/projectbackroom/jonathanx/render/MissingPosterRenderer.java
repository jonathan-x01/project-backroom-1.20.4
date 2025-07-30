package projectbackroom.jonathanx.render;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileResult;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.joml.Matrix4f;
import projectbackroom.jonathanx.init.BackroomComponents;
import projectbackroom.jonathanx.networking.s2c.MissingPosterS2CPayload;
import projectbackroom.jonathanx.util.DebugLogger;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Environment(EnvType.CLIENT)
public class MissingPosterRenderer {
    private static final LoadingCache<UUID, CompletableFuture<Optional<GameProfile>>> PROFILE_CACHE =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(Duration.ofMinutes(30)) // keep for 30 mins
                    .maximumSize(512)
                    .build(new CacheLoader<>() {
                        @Override
                        public CompletableFuture<Optional<GameProfile>> load(UUID uuid) {
                            return CompletableFuture.supplyAsync(() -> {
                                try {
                                    ProfileResult result = MinecraftClient.getInstance()
                                            .getSessionService()
                                            .fetchProfile(uuid, true);
                                    return Optional.ofNullable(result).map(ProfileResult::profile);
                                } catch (Exception e) {
                                    return Optional.empty();
                                }
                            });
                        }
                    });

    private final MissingPosterRenderState state;
    private final MatrixStack matrices;
    private final VertexConsumerProvider vertexConsumers;
    private final int light;
    private final MinecraftClient client;

    private final TextRenderer textRenderer;

    public static final int WIDTH = 44 * 2;
    public static final int HEIGHT = 54 * 2;

    public MissingPosterRenderer(MissingPosterRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light){
        this.state = state;
        this.matrices = matrices;
        this.vertexConsumers = vertexConsumers;
        this.light = light;

        this.client = MinecraftClient.getInstance();
        this.textRenderer = client.textRenderer;
    }

    public void draw(ItemStack stack, MissingPosterRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        // Draw background texture
        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getText(state.backgroundTexture));
        buffer.vertex(matrix4f, 0, HEIGHT, -0.01F).color(255,255,255,255).texture(0,1).light(light);
        buffer.vertex(matrix4f, WIDTH, HEIGHT, -0.01F).color(255,255,255,255).texture(1,1).light(light);
        buffer.vertex(matrix4f, WIDTH, 0, -0.01F).color(255,255,255,255).texture(1,0).light(light);
        buffer.vertex(matrix4f, 0, 0, -0.01F).color(255,255,255,255).texture(0,0).light(light);

        if (state.playerUUID != null) {
            try {
                CompletableFuture<Optional<GameProfile>> future = PROFILE_CACHE.get(UUID.fromString(state.playerUUID));
                future.thenAccept(optionalProfile -> {
                    AtomicReference<Text> name = new AtomicReference<>(Text.translatable("missing_poster.unknown"));
                    if (optionalProfile.isEmpty()){
                        stack.set(BackroomComponents.MISSING_POSTER_PLAYER_NAME, name.get().getString());
                    }

                    optionalProfile.ifPresent(gameProfile -> {
                        client.execute(() -> {
                            name.set(Text.of(gameProfile.getName()));
                            setText(name.get());
                            stack.set(BackroomComponents.MISSING_POSTER_PLAYER_NAME, name.get().getString());
                            ClientPlayNetworking.send(new MissingPosterS2CPayload(UUID.fromString(state.playerUUID), name.get().getString()));
                        });
                        setProfileHeadTexture(gameProfile);
                    });

                });
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (state.skinTexture != null) {
                matrices.push();

                RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR);
                RenderSystem.enableDepthTest();
                RenderSystem.setShaderTexture(0, state.skinTexture);
                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

                Matrix4f matrix = matrices.peek().getPositionMatrix();

                BufferBuilder headBuffer = Tessellator.getInstance().begin(
                        VertexFormat.DrawMode.QUADS,
                        VertexFormats.POSITION_TEXTURE_COLOR
                );

                // Position
                int size = 32;
                float drawX = (WIDTH - size) / 2f;
                float drawY = (HEIGHT - size) / 2f;
                float drawZ = -1f;

                float u0 = 8f / 64f;   // left
                float v0 = 8f / 64f;   // top
                float u1 = 16f / 64f;  // right
                float v1 = 16f / 64f;  // bottom

                headBuffer.vertex(matrix, drawX,        drawY + size, drawZ).texture(u0, v1).color(255,255,255,255);
                headBuffer.vertex(matrix, drawX + size, drawY + size, drawZ).texture(u1, v1).color(255,255,255,255);
                headBuffer.vertex(matrix, drawX + size, drawY,        drawZ).texture(u1, v0).color(255,255,255,255);
                headBuffer.vertex(matrix, drawX,        drawY,        drawZ).texture(u0, v0).color(255,255,255,255);

                BufferRenderer.drawWithGlobalProgram(headBuffer.end());
                matrices.pop();
            }
        }
    }

    public void setProfileHeadTexture(GameProfile gameProfile){
        client.getSkinProvider().fetchSkinTextures(gameProfile).thenAccept(skinTextures -> {
            state.skinTexture = skinTextures.texture();
        });
    }

    public void setText(Text name){
        Text missingTitle = Text.translatable("missing_poster.missing_title").formatted(Formatting.RED, Formatting.BOLD);

        matrices.push();
        matrices.translate(0, 0, -0.02F);
        textRenderer.draw(
                name,
                getCenter(name, textRenderer),
                20,
                0x000000,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        textRenderer.draw(
                missingTitle,
                getCenter(missingTitle, textRenderer),
                10,
                0x000000,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        matrices.pop();
    }

    float getCenter(Text text, TextRenderer textRenderer){
        return (WIDTH - textRenderer.getWidth(text)) / 2f;
    }
}
