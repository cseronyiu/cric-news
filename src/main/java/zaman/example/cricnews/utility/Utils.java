package zaman.example.cricnews.utility;

public class Utils {
    public static long getIdentityFromURL(String url) {
        Long response=0L;
        try {
            String identifire = url.replace(CommonUrl.basURL.toString(), "").replace(".html", "");
            response=Long.parseLong(identifire);
        } catch (Exception ex) {

        }
        return response;
    }
}
