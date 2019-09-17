package com.github.haseoo.simplequizjava.gui.utilities;

import com.github.haseoo.simplequizjava.exceptions.gui.GameInitializerException;
import com.github.haseoo.simplequizjava.gamecore.game.GameWCC;
import com.github.haseoo.simplequizjava.gamecore.game.GameWOCC;
import com.github.haseoo.simplequizjava.gamecore.game.enums.FallingOutPolicy;
import com.github.haseoo.simplequizjava.gamecore.services.IQuestionService;
import com.github.haseoo.simplequizjava.gamecore.services.PlayerService;
import com.github.haseoo.simplequizjava.gui.utilities.enums.GameMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@NoArgsConstructor
@ToString
public class GameInitializer {
    @Getter
    private GameMode gameMode;
    private IQuestionService questionService;
    private Integer numberOfQuestion;
    private List<String> playerNicknames;
    private FallingOutPolicy fallingOutPolicy;

    public GameWCC buildGameWCC() {
        if (checkRequiredFields()) {
            throw new GameInitializerException();
        }
        return new GameWCC(questionService,
                            new PlayerService(playerNicknames),
                            fallingOutPolicy,
                            numberOfQuestion);
    }

    public GameWOCC buildGameWOCC() {
        if (!checkRequiredFields()) {
            throw new GameInitializerException();
        }
        return new GameWOCC(questionService,
                new PlayerService(playerNicknames),
                fallingOutPolicy,
                numberOfQuestion);
    }

    private boolean checkRequiredFields() {
        return questionService == null ||
                playerNicknames == null ||
                playerNicknames.isEmpty() ||
                fallingOutPolicy == null ||
                numberOfQuestion == null;
    }
}
