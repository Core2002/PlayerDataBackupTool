package fun.fifu.tool.test.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import fun.fifu.tool.test.DataManger;
import fun.fifu.tool.test.pojo.DataPojo;
import org.bson.Document;


public class MongoController {
    public MongoCollection<Document> getCollection() {
        return MongoClients.create(DataManger.configPojo.getMongodb_uri())
                .getDatabase(DataManger.configPojo.getMongodb_database())
                .getCollection(DataManger.configPojo.getMongodb_collection());
    }

    public void backupWithUUID(String uuid) {
        MongoCollection<Document> coll = getCollection();
        Document bson = coll.find(new Document("player_uuid", uuid)).first();
        DataPojo pojo;
        if (bson == null) {
            pojo = new DataPojo();
            pojo.setPlayer_uuid(uuid);
            pojo.getData().put(DateUtil.now(), Base64.encode(FileUtil.file(DataManger.configPojo.getWorld_playerdata_path() + uuid + ".dat")));
            coll.insertOne(Document.parse(pojo.toJson()));
        } else {
            pojo = DataManger.gson.fromJson(bson.toJson(), DataPojo.class);
            pojo.getData().put(DateUtil.now(), Base64.encode(FileUtil.file(DataManger.configPojo.getWorld_playerdata_path() + uuid + ".dat")));
            coll.findOneAndUpdate(bson, Document.parse(pojo.toJson()));
        }
    }
}
