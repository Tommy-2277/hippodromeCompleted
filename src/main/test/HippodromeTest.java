import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    void whenHippodromeConstructorHasFirstParameterNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void checkExceptionTextWhenHippodromeConstructorHasFirstParameterNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }


    @Test
    void whenHippodromeConstructorHasEmptyList() {
        List<Horse> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
    }

    @Test
    void checkExceptionTextWhenHippodromeConstructorHasEmptyList() {
        List<Horse> list = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void checkHorsesListAtConstructor() {
        List<Horse> list = Arrays.asList(
                new Horse("q", 2 ,3),
                new Horse("w", 3, 2),
                new Horse("e", 1, 2),
                new Horse("r", 2, 1),
                new Horse("t", 1, 3),
                new Horse("y", 3, 1),
                new Horse("u", 1, 4),
                new Horse("i", 4, 1),
                new Horse("o", 1, 5),
                new Horse("p", 5, 1));
        assertEquals(list, new Hippodrome(list).getHorses());
    }

    @Test
    void moveAllHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for(Horse h: horses){
            verify(h).move();
        }
    }

    @Test
    void checkGetWinnerMethod() {
        List<Horse> horses = new ArrayList<>();
        Horse h1 = new Horse("h", 1, 2);
        Horse h2 = new Horse("k", 2, 3);
        Horse h3 = new Horse("j", 3, 4);
        horses.add(h1);
        horses.add(h2);
        horses.add(h3);
        assertEquals(h3, new Hippodrome(horses).getWinner());
    }




}
