package br.com.emmanuelneri.app;

import br.com.emmanuelneri.DisponibilizadorProperties;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/arquivos")
public class ArquivoController {

    private static final String ATRIBUTO_XML = "xml";

    @Autowired
    private DisponibilizadorProperties properties;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> findAll() {
        try (MongoClient mongoClient = new MongoClient(properties.getMongoHost(), properties.getMongoPort())) {

            final MongoCollection<Document> collection = getDocumentMongoCollection(mongoClient);

            final List<String> xmls = new ArrayList<>();
            collection.find().map(document -> document.getString(ATRIBUTO_XML)).forEach((Block<? super String>) xmls::add);

            return xmls;
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String findByProcessIdentifier(@PathVariable("id") String id) {
        try (MongoClient mongoClient = new MongoClient(properties.getMongoHost(), properties.getMongoPort())) {

            final MongoCollection<Document> collection = getDocumentMongoCollection(mongoClient);

            return collection.find(new Document("_id", new ObjectId(id))).first().getString(ATRIBUTO_XML);
        }
    }

    @RequestMapping(path = "/ids", method = RequestMethod.GET)
    public List<String> findIds() {
        try (MongoClient mongoClient = new MongoClient(properties.getMongoHost(), properties.getMongoPort())) {

            final MongoCollection<Document> collection = getDocumentMongoCollection(mongoClient);

            final List<String> ids = new ArrayList<>();
            collection.find().map(document -> document.getObjectId("_id")).forEach((Block<? super ObjectId>) objectId -> ids.add(objectId.toHexString()));
            return ids;
        }
    }

    @RequestMapping(path = "/objectIds", method = RequestMethod.GET)
    public List<ObjectId> findObjectIds() {
        try (MongoClient mongoClient = new MongoClient(properties.getMongoHost(), properties.getMongoPort())) {

            final MongoCollection<Document> collection = getDocumentMongoCollection(mongoClient);

            final List<ObjectId> xmls = new ArrayList<>();
            collection.find().map(document -> document.getObjectId("_id")).forEach((Block<? super ObjectId>) xmls::add);
            return xmls;
        }
    }

    private MongoCollection<Document> getDocumentMongoCollection(MongoClient mongoClient) {
        final MongoDatabase db = mongoClient.getDatabase(properties.getMongoDataBase());
        return db.getCollection("arquivos");
    }

}
