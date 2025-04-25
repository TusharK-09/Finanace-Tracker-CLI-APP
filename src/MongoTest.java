import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoTest {
    private static final String DATABASE_NAME = "FINANCE-JAVA-APP";
    private static final String COLLECTION_NAME = "java";

    public static MongoCollection<Document> getCollection() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://tushar-1:NsV6C6B%40.P%21LMzg@tusharcluster.hh1ta.mongodb.net/");
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    public static void insertExpense(double amount, String category, String description, String date) {
        MongoCollection<Document> collection = getCollection();

        Document expense = new Document()
                .append("amount", amount)
                .append("category", category)
                .append("description", description)
                .append("date", date);

        collection.insertOne(expense);
    }
}
