import java.net.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;

public class TransactionVerifier {
    List<URL> urls = new ArrayList<>();
    Duration timeout = Duration.ofSeconds(3);

    public TransactionVerifier(List<URL> urls) {
        if(Objects.isNull(urls)){
            throw new IllegalArgumentException("the urls are empty");
        }
        this.urls = urls;
    }

    public void setTimeout(Duration timeout) {
        if (Objects.isNull(timeout)){
           timeout = Duration.ofSeconds(3);
        }
        this.timeout = timeout;
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
        urls.add(new URL("https://run.mocky.io/v3/717f6b9c-8c65-40e5-8572-a2f9ed7a369f"));
        urls.add(new URL("https://run.mocky.io/v3/b503f8ea-0465-4bb9-908f-a68913da99ef"));
        urls.add(new URL("https://run.mocky.io/v3/074cba52-e249-483b-afac-f3dd2488b56f"));
        TransactionVerifier transactionVerifier = new TransactionVerifier(urls);
        System.out.println(transactionVerifier.verify("test"));
    }
}
