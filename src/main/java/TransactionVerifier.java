import java.net.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;

public class TransactionVerifier {
    public TransactionVerifier(List<URL> urls) {
        // TODO: Implement
        this.urls = urls;
    }

    public void setTimeout(Duration timeout) {
        // TODO: Implement
    }

    public URL getFastestServer() {
        // TODO: Implement
        return null;
    }

    public String verify(String transactionId) throws TimeoutException {
        // TODO: Implement
        return null;
    }

    public static void main(String[] args) throws Exception {
        List<URL> urls = new ArrayList<>();
        urls.add(new URL("https://run.mocky.io/v3/33065dc7-b73f-4a05-b149-3a3de5f3ac2f"));
        urls.add(new URL("https://run.mocky.io/v3/8687cda5-7448-42d8-a18d-01cfcf0c5914"));
        urls.add(new URL("https://run.mocky.io/v3/6a90fe24-16b1-42fc-8452-54e44cc771e9"));
        TransactionVerifier transactionVerifier = new TransactionVerifier(urls);
        System.out.println(transactionVerifier.verify("test"));
    }
}
