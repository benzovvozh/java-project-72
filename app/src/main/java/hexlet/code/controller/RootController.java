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
            var port = url.getPort();
            var host = url.getHost();
            String stringBuilder = new StringBuilder(protocol)
                    .append("://").append(host).append((port == -1) ? "" : ":" + port).toString();
            Url result = new Url(stringBuilder);
            if (!UrlRepository.findMatchesByName(stringBuilder)) {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.redirect(NamedRoutes.urlsPath());
            } else {
                UrlRepository.save(result);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
                ctx.redirect(NamedRoutes.urlsPath());
            }
        } catch (MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный Url");
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
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls.jte", model("page", page));
    }
}
