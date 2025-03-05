package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import io.javalin.testtools.JavalinTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    private static MockWebServer mockWebServer;
    private Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void setServer() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse()
                .setBody(App.readResourceFile("exampleHTML.html")));
    }

    @AfterAll
    public static void shutDownServer() throws Exception {
        mockWebServer.shutdown();
    }

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

    @Test
    public void checkUrlTest2() throws SQLException, IOException {
        mockWebServer.start();
        baseUrl = mockWebServer.url("/").toString();
        var url = new Url(baseUrl);
        UrlRepository.save(url);
        var id = url.getId();

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urlChecks(id));
            assertThat(response.code()).isEqualTo(200);
            var list = UrlCheckRepository.getEntities(id);
            var urlCheck = list.get(0);
            assertThat(urlCheck.getStatusCode()).isEqualTo(200);
            assertThat(urlCheck.getTitle()).isEqualTo("Example html");
            assertThat(urlCheck.getH1()).isEqualTo("Hello world!");
            assertThat(urlCheck.getDescription()).isEqualTo("This is example");
        });

    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.mainPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Page analyzer");
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Список сайтов");
        });
    }

    @Test
    public void testUrlsPage2() throws SQLException {
        var url = new Url("https://youtube.com");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://youtube.com");
        });
    }

    //???
    @Test
    public void testUrlsPage3() throws SQLException {
        var url = new Url("https://www.youtube.com/watch?v=jfKfPfyJRdk&ab_channel=LofiGirl");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.youtube.com");
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://youtube.com");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.idUrlPath(url.getId()));
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.idUrlPath(9999));
            assertThat(response.code()).isEqualTo(404);
        });
    }

}
