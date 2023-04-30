import static org.junit.Assert.assertEquals;

import org.junit.*;

import java.net.URL;
import java.util.*;

public class TransactionVerifierSampleTest {
    private String uri;
    private String transactionId;
    private final List<URL> urls = new ArrayList<>();
    private final static Map<Integer, SampleHttpServer> httpServers = new HashMap<>();

    @BeforeClass
    public static void initServers() throws Exception {
        httpServers.put(8080, new SampleHttpServer(8080));
        httpServers.get(8080).before();
        Thread.sleep(500);
    }

    @Before
    public void init() {
        uri = "/" + UUID.randomUUID();
        transactionId = UUID.randomUUID().toString();
        urls.clear();
    }

    @Test(timeout = 1500)
    public void testSingleUrlWithDefaultTimeout() throws Exception {
        httpServers.get(8080).registerHandler(uri, new SampleHttpResponseHandler(transactionId, 8080, 0));
        urls.add(new URL(httpServers.get(8080).getUriFor(uri)));
        TransactionVerifier transactionVerifier = new TransactionVerifier(urls);

        assertEquals("{\"success\":true,\"port\":8080}", transactionVerifier.verify(transactionId));
    }
}