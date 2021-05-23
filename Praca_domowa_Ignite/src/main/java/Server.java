import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

public class Server {
    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration().setPeerClassLoadingEnabled(true);
         Ignition.start(cfg);
    }
}
