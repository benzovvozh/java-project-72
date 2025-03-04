package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    .append("://").append(host)
                    .append((port == -1) ? "" : ":" + port)
                    .toString();
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
            ctx.sessionAttribute("flash", "Некорректный Url");
            throw new RuntimeException("Некорректный URL");
        } catch (SQLException e) {
            ctx.sessionAttribute("flash", "Некорректный Url");
            throw new RuntimeException("???");
        }
    }

    public static void check(Context ctx) throws SQLException {
        // получаем id сайта
        var id = ctx.pathParamAsClass("id", int.class).get();
        // находим его в базе данных urls
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("URL with id = " + id + " not found"));
        // получаем его имя
        var urlName = url.getName();
        // делаем запрос
        var response = Unirest.get(urlName).asString();
        // получаем нужные поля
        int statusCode = response.getStatus();
        var jsonResponse = response.getBody();
        Document doc = Jsoup.parse(jsonResponse);
        String title = doc.title();
        String h1 = String.valueOf(doc.selectFirst("h1"));
        if (h1.equals("null")) {
            h1 = "";
        }
        String desc = doc.select("meta[name=description]").attr("content");
        // создаем объект urlCheck
        var urlCheck = new UrlCheck(id, statusCode, title, h1, desc);
        // сохраняем в базу данных url_checks
        UrlCheckRepository.save(urlCheck);
        ctx.redirect(NamedRoutes.idUrlPath(id));
    }

    public static void showURLS(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        List<UrlCheck> urlCheckList = new ArrayList<>();
        for (var url : urls) {
            var urlId = url.getId();
            var urlCheck = UrlCheckRepository.getEntities(urlId);
            urlCheckList.addAll(urlCheck);
        }
        var page = new UrlsPage(urls, urlCheckList);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", int.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("URL with id = " + id + " not found"));
        var urlChecks = UrlCheckRepository.getEntities(id);
        var page = new UrlPage(url, urlChecks);
        ctx.render("url.jte", model("page", page));
    }


}
