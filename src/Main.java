import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String headUrl = "http://localhost:8080/";
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTimeRequestUrl = urlBuilder(headUrl, localDateTime);
        System.out.println(dateTimeRequestUrl);

    }

    public static String urlBuilder(String beginUrl, LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(beginUrl);
        stringBuilder.append("?");
        String dateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        stringBuilder.append("date=")
                .append(dateTime);
        return stringBuilder.toString();
    }
}
