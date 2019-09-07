package testutils;

import java.util.Arrays;
import java.util.List;

import static testutils.Constants.*;


public class PlayerServiceTestDataGenerator {
    public static List<String> generatePlayerNickames() {
        return Arrays.asList(PLAYER_NICKNAME1, PLAYER_NICKNAME2, PLAYER_NICKNAME3);
    }
}
