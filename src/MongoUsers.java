import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class MongoUsers {
    private static final String DATABASE_NAME = "FINANCE-JAVA-APP";
    private static final String COLLECTION_NAME = "appUsers";

    public static MongoCollection<Document> getCollection() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://tushar-1:NsV6C6B%40.P%21LMzg@tusharcluster.hh1ta.mongodb.net/");
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    public static void insertUser(String inputUser, String inputPassword) {
        MongoCollection<Document> collection = getCollection();

        Document user = new Document()
                .append("username", inputUser)
                .append("password", inputPassword);

        collection.insertOne(user);
    }

    public boolean checkLogin(String inputUsername, String inputPassword) {
        MongoCollection<Document> collection = getCollection();

        FindIterable<Document> users = collection.find();

        for (Document user : users) {
            String dbUser = user.getString("username");
            String dbPass = user.getString("password");

            if (inputUsername.equals(dbUser)) {
                if (inputPassword.equals(dbPass)) {
                    return true;
                } else {
                    System.out.println("❌ Wrong password!");
                    return false;
                }
            }
        }

        System.out.println("❌ Username not found!");
        return false;
    }

    public boolean isUserExists(String username) {
        MongoCollection<Document> collection = getCollection();

        Document query = new Document("username", username);
        Document result = collection.find(query).first();

        return result != null;
    }
}
