package hexlet.code.controller;

import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context ctx) {
        ctx.render("index.jte");
    }

    public static void save(Context ctx) {
        var url1 = ctx.formParam("url");
        try {
            var url = new URI(url1).toURL();
            var protocol = url.getProtocol();
            var port = String.valueOf(url.getPort());
            var host = url.getHost();
            String resultString = protocol + host + ((port != null) ? port : "");
            Url result = new Url(resultString);
            UrlRepository.save(result);
            ctx.redirect(NamedRoutes.urlsPath());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Некорректный URL");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Некорректный URL");
        } catch (SQLException e) {
            throw new RuntimeException("Чето со временем");
        }
    }
    public static void showURLS(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        ctx.render("urls.jte", model("page", page));
    }
}
