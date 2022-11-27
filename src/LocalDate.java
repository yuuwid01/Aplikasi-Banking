
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDate {

    public static String now() {
        Locale locale = Locale.forLanguageTag("id");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m:s", locale);
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);
    }
}
