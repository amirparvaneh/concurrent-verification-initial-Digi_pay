import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.*;

public class SampleHttpServer {
    private final int PORT;
    private com.sun.net.httpserver.HttpServer server;

    public SampleHttpServer(int port) {
        PORT = port;
    }

    public void before() throws IOException {
        server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(PORT), 0);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }

    public void after() {
        if (server == null) {
            return;
        }
        server.stop(0);
    }

    public String getUriFor(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        String host = "http://localhost:" + PORT;
        return host + path;
    }

    public void registerHandler(String uriToHandle, HttpHandler httpHandler) {
        server.createContext(uriToHandle, httpHandler);
    }
}
