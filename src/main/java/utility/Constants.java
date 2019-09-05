package utility;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String CATEGORIES_LIST_FILE_PATH = "src\\main\\resources\\questions\\categoriesList.json";
    public static final boolean ALL_PLAYER_LOST_INITIAL_VALUE = false;
    public static final Integer BEGIN_INDEX = 0;
}
