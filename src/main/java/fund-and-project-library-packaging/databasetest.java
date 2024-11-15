public class databasetest {
    public static void main(String[] args) {
        Database db = new Database();
        db.setHostofDatabase("172.20.10.11");

        System.out.println(db.getAllFunds());
    }
}
