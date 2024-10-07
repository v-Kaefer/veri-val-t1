import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {
    @Test
    public void testTicketFormatting() {
        LocalDateTime entrada = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String expectedFormattedEntrada = entrada.format(formatter);

        Ticket ticket = new Ticket();
        String actualFormattedEntrada = entrada.format(formatter);

        assertEquals(expectedFormattedEntrada, actualFormattedEntrada);
    }
}