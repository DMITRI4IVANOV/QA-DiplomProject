import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static com.codeborne.selenide.Selenide.open;

public class MainTest {
    @Test
    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
    }
}
