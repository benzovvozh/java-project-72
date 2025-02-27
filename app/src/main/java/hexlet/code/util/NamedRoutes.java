package hexlet.code.util;

public class NamedRoutes {
    public static String mainPath() {
        return "/";
    }
    public static String urlsPath(){
        return "/urls";
    }
    public static String idUrlPath(Long id){
        return idUrlPath(String.valueOf(id));
    }
    public static String idUrlPath(String id){
        return "/urls" + id;
    }
}
