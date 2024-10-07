import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstacionamentoTest {
    private Estacionamento estacionamento = new Estacionamento();

    @ParameterizedTest
    @CsvSource({
        "2024-10-08T08:00, 2024-10-08T08:10, false, 0.0",
        "2024-10-08T08:00, 2024-10-08T08:30, false, 5.90",
        "2024-10-08T08:00, 2024-10-08T08:30, true, 2.95",
        "2024-10-08T08:00, 2024-10-08T10:30, false, 10.90",
        "2024-10-08T23:00, 2024-10-09T09:00, false, 50.00"
    })
    public void testCalcularTarifa(String entrada, String saida, boolean isVip, double esperado) {
        LocalDateTime dataEntrada = LocalDateTime.parse(entrada);
        LocalDateTime dataSaida = LocalDateTime.parse(saida);
        double resultado = estacionamento.calcularTarifa(dataEntrada, dataSaida, isVip);
        assertEquals(esperado, resultado, 0.01);
    }
}
