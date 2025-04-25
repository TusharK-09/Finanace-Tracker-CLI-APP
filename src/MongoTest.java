import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

public class MongoTest {
    private static final String DATABASE_NAME = "FINANCE-JAVA-APP";
    private static final String COLLECTION_NAME = "java";


    private static final Dotenv dotenv = Dotenv.load();

    public static MongoCollection<Document> getCollection() {
        String uri = dotenv.get("MONGO_URL");
        MongoClient mongoClient = MongoClients.create(uri);
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
