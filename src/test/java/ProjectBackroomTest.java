import projectbackroom.jonathanx.util.MojangUUIDFetcher;

public class ProjectBackroomTest {
    public static void main(String[] args) {
        MojangUUIDFetcher.getUUID("Notch").thenAccept(opt -> {
            System.out.println("Result: " + opt.get());
        }).join(); // join so program doesn't exit early
    }
}
