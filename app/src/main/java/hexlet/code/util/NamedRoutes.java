package hexlet.code.util;

public class NamedRoutes {
    public static String mainPath() {
        return "/";
    }

    public static String urlsPath() {
        return "/urls";
    }

    public static String idUrlPath(int id) {
        return idUrlPath(String.valueOf(id));
    }

    public static String idUrlPath(String id) {
        return "/urls/" + id;
    }

    public static String urlChecks(String id) {
        return idUrlPath(id) + "/checks";
    }

    public static String urlChecks(int id) {
        return urlChecks(String.valueOf(id));
    }
}
