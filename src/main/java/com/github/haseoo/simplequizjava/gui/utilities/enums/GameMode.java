package com.github.haseoo.simplequizjava.gui.utilities.enums;

import com.github.haseoo.simplequizjava.gamecore.utility.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum GameMode {
    WITHOUT_CHOOSING_CATEGORY,
    WITH_CHOOSING_CATEGORY;

    private static Map<Integer, GameMode> ofMap;

    static {
        ofMap = new HashMap<>();
        Arrays.stream(Constants.getGAME_MODE_WITH_CHOOSING_CATEGORY_INDEXES())
                .forEach(index -> ofMap.put(index, WITH_CHOOSING_CATEGORY));
        Arrays.stream(Constants.getGAME_MODE_WITHOUT_CHOOSING_CATEGORY_INDEXES())
                .forEach(index -> ofMap.put(index, WITHOUT_CHOOSING_CATEGORY));
    }

    public static GameMode of(Integer index) {
        return ofMap.get(index);
    }
}
