package fun.fifu.tool.player_backup.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import fun.fifu.tool.player_backup.DataManger;
import fun.fifu.tool.player_backup.pojo.DataPojo;
import org.bson.Document;


public class MongoController {
    public MongoCollection<Document> collection= MongoClients.create(DataManger.configPojo.getMongodb_uri())
                .getDatabase(DataManger.configPojo.getMongodb_database())
                .getCollection(DataManger.configPojo.getMongodb_collection());


    public String getBase64(String uuid) {
        return Base64.encode(FileUtil.file(DataManger.configPojo.getWorld_playerdata_path() + uuid + ".dat"));
    }

    public void backupWithUUID(String uuid) {
        MongoCollection<Document> coll = collection;
        Document bson = coll.find(new Document("player_uuid", uuid)).first();
        DataPojo pojo;
        if (bson == null) {
            pojo = new DataPojo();
            pojo.setPlayer_uuid(uuid);
            pojo.getData().put(DateUtil.now(), getBase64(uuid));
            coll.insertOne(Document.parse(pojo.toJson()));
        } else {
            coll.findOneAndUpdate(new Document("player_uuid", uuid), new Document("$set", new Document("data." + DateUtil.now(), getBase64(uuid))));
        }
    }
}
