import com.vanca.jan.mastermind.core.Color;
import com.vanca.jan.mastermind.core.ColorValidator;
import com.vanca.jan.mastermind.core.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorValidatorTest {

    private ColorValidator colorValidator;

    @BeforeEach
    void setUp(){
        colorValidator = new ColorValidator();
    }

    @Test
    void validate_allgood() {
        List<Color> expected = List.of(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK);
        List<Color> given = List.of(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK);

        ValidationResult validationResult = colorValidator.validate(expected, given);

        assertEquals(expected.size(), validationResult.getCorrect());
        assertEquals(0, validationResult.getWrong());
        assertEquals(0, validationResult.getCorrectOnWrongPlace());
    }

    @Test
    void validate_goodOnWrongPlace() {
        List<Color> expected = List.of(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK);
        List<Color> given = List.of(Color.RED, Color.GREEN, Color.BLACK, Color.BLUE);

        ValidationResult validationResult = colorValidator.validate(expected, given);

        assertEquals(2, validationResult.getCorrect());
        assertEquals(0, validationResult.getWrong());
        assertEquals(2, validationResult.getCorrectOnWrongPlace());
    }

    @Test
    void validate_wrong() {
        List<Color> excepted = List.of(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK);
        List<Color> given = List.of(Color.YELLOW, Color.WHITE, Color.BLUE, Color.BLACK);

        ValidationResult validationResult = colorValidator.validate(excepted, given);

        assertEquals(2, validationResult.getCorrect());
        assertEquals(0, validationResult.getCorrectOnWrongPlace());
        assertEquals(2, validationResult.getWrong());
    }

}