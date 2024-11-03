package fr.heriamc.hub.data;

import fr.heriamc.api.data.PersistentDataManager;
import fr.heriamc.api.data.mongo.MongoConnection;
import fr.heriamc.api.data.redis.RedisConnection;

import java.util.UUID;

public class HubPlayerManager extends PersistentDataManager<UUID, HubPlayer> {

    public HubPlayerManager(RedisConnection redisConnection, MongoConnection mongoConnection) {
        super(redisConnection, mongoConnection, "hubPlayers", "hubPlayers");
    }

    @Override
    public HubPlayer getDefault() {
        return new HubPlayer(null, null, null, null, null, null, null, null, null);
    }
}
