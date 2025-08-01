package projectbackroom.jonathanx.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MojangUUIDFetcher {
    private static final LoadingCache<String, CompletableFuture<Optional<UUID>>> UUID_CACHE =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(Duration.ofMinutes(30))
                    .maximumSize(512)
                    .build(new CacheLoader<>() {
                        @Override
                        public CompletableFuture<Optional<UUID>> load(String username) {
                            return CompletableFuture.supplyAsync(() -> fetchUUID(username));
                        }
                    });

    /**
     * Public API: Get a UUID for a username (async)
     */
    public static CompletableFuture<Optional<UUID>> getUUID(String username) {
        return UUID_CACHE.getUnchecked(username.toLowerCase());
    }

    /**
     * Actual API call to Mojang (blocking, but runs async in the cache loader)
     */
    private static Optional<UUID> fetchUUID(String username) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                JsonObject json = JsonParser.parseReader(new InputStreamReader(connection.getInputStream()))
                        .getAsJsonObject();
                String id = json.get("id").getAsString();
                return Optional.of(parseMojangUUID(id));
            }
        } catch (Exception ignored) {}
        return Optional.empty();
    }

    /**
     * Converts Mojang's no-dash UUID string into a proper UUID
     */
    private static UUID parseMojangUUID(String id) {
        return UUID.fromString(id.replaceFirst(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5"));
    }
}
