package hexlet.code;

//import hexlet.code.model.Url;
//import hexlet.code.repository.UrlRepository;
//import hexlet.code.util.NamedRoutes;
//import io.javalin.Javalin;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import io.javalin.testtools.JavalinTest;
//
//import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
//    private Javalin app;
//
//    @BeforeEach
//    public final void setUp() throws IOException, SQLException {
//        app = App.getApp();
//    }
//
//    @Test
//    public void testMainPage() {
//        JavalinTest.test(app, (server, client) -> {
//            var response = client.get(NamedRoutes.mainPath());
//            assertThat(response.code()).isEqualTo(200);
//            assertThat(response.body().string()).contains("Page analyzer");
//        });
//    }
//
//    @Test
//    public void testUrlsPage() {
//        JavalinTest.test(app, (server, client) -> {
//            var response = client.get(NamedRoutes.urlsPath());
//            assertThat(response.code()).isEqualTo(200);
//            assertThat(response.body().string()).contains("Список сайтов");
//        });
//    }
//
//    @Test
//    public void testUrlsPage2() throws SQLException {
//        var url = new Url("https://youtube.com");
//        UrlRepository.save(url);
//        JavalinTest.test(app, (server, client) -> {
//            var response = client.get(NamedRoutes.urlsPath());
//            assertThat(response.code()).isEqualTo(200);
//            assertThat(response.body().string()).contains("https://youtube.com");
//        });
//    }
////???
//    @Test
//    public void testUrlsPage3() throws SQLException {
//        var url = new Url("https://www.youtube.com/watch?v=jfKfPfyJRdk&ab_channel=LofiGirl");
//        UrlRepository.save(url);
//        JavalinTest.test(app, (server, client) -> {
//            var response = client.get(NamedRoutes.urlsPath());
//            assertThat(response.code()).isEqualTo(200);
//            assertThat(response.body().string()).contains("https://www.youtube.com");
//        });
//    }
//
//    @Test
//    public void testUrlPage() throws SQLException {
//        var url = new Url("https://youtube.com");
//        UrlRepository.save(url);
//        JavalinTest.test(app, (server, client) -> {
//            var response = client.get(NamedRoutes.idUrlPath(url.getId()));
//            assertThat(response.code()).isEqualTo(200);
//        });
//    }
//
//    @Test
//    public void testUrlNotFound() {
//        JavalinTest.test(app, (server, client) -> {
//            var response = client.get(NamedRoutes.idUrlPath(9999));
//            assertThat(response.code()).isEqualTo(404);
//        });
//    }

}
