import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    void whenHorseConstructorHasFirstParameterNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    void checkExceptionTextWhenHorseConstructorHasFirstParameterNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", " " })
    void checkSpaceTabSignsInHorseFirstParameter(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2,3));
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", " " })
    void checkExceptionTextWithSpaceTabSignsInHorseFirstParameter(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 2, 3));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void checkNegativeNumberAtHorseSecondParameter() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("k", -2, 1));
    }

    @Test
    void checkExceptionTextAtHorseSecondNegativeParameter() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("k", -2, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkNegativeNumberAtHorseThirdParameter() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("k", 2, -1));
    }

    @Test
    void checkExceptionTextAtHorseThirdNegativeParameter() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("k", 2, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }



    @Test
    void getHorseNameTest() {
        Horse horse = new Horse("h", 2, 3);
        assertEquals("h", horse.getName());
    }

    @Test
    void getHorseSpeedTest() {
        Horse horse = new Horse("h", 2, 3);
        assertEquals(2, horse.getSpeed());
    }

    @Test
    void getHorseDistanceTest() {
        Horse horse = new Horse("h", 2, 3);
        assertEquals(3, horse.getDistance());
    }

    @Test
    void hasHorseMoveMethodCalledGetRandomDouble() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse1 = new Horse("h", 1, 4);
            horse1.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    //distance + speed * getRandomDouble(0.2, 0.9)
    @Test
    void checkHorseMoveSetRightDistanceValue() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse1 = new Horse("h", 2, 3);
            horse1.move();
            assertEquals(4, horse1.getDistance());
        }
    }


}
