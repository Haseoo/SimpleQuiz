package com.github.haseoo.simplequizjava.gamecore.services;

import com.github.haseoo.simplequizjava.gamecore.projections.players.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.haseoo.simplequizjava.testutils.PlayerServiceTestDataGenerator.generatePlayerNicknames;
import static com.github.haseoo.simplequizjava.testutils.Constants.*;

class PlayerServiceTest {

    private PlayerService sut;
    @BeforeEach
    void beforeEach() {
        sut = new PlayerService(generatePlayerNicknames());
    }
    @Test
    void should_return_players_list_with_three_element() {
        //given & then
        List<Player.PlayerInfo> playerInfos = sut.getPlayerList();
        //then
        Assertions.assertThat(playerInfos).hasSize(PLAYERS_QTY);
    }

    @Test
    void should_return_player_list_with_increasing_id() {
        //given
        AtomicInteger atomicInteger = new AtomicInteger();
        // then
        List<Player.PlayerInfo> playerInfos = sut.getPlayerList();
        //then
        playerInfos
                .forEach(playerInfo -> Assertions
                                        .assertThat(playerInfo.getId())
                                        .isEqualTo(atomicInteger.getAndIncrement()));
    }

    @Test
    void should_return_players_list_with_nicknames() {
        //given & then
        List<Player.PlayerInfo> playerInfos = sut.getPlayerList();
        //then
        Assertions.assertThat(playerInfos.get(0).getName()).isEqualTo(PLAYER_NICKNAME1);
    }

    @Test
    void should_return_false_when_all_players_is_not_lost() {
        //given & when & then
        Assertions.assertThat(sut.isAllPlayerLost()).isFalse();
    }

    @Test
    void should_return_true_when_all_players_is_lost() {
        //given
        sut.getPlayerList().forEach(sut::setPlayerLost);
        //when & given
        Assertions.assertThat(sut.isAllPlayerLost()).isTrue();
    }

    @Test
    void should_add_points_to_player_score() {
        //given
        Player.PlayerInfo player = sut.getPlayerList().get(0);
        //when
        sut.addPointsToPlayerScore(player, PLAYER_POINTS);
        //then
        Assertions.assertThat(player.getScore()).isEqualTo(PLAYER_POINTS);
    }

    @Test
    void should_mark_player_as_lost() {
        //given
        Player.PlayerInfo player = sut.getPlayerList().get(0);
        //when
        sut.setPlayerLost(player);
        //then
        Assertions.assertThat(player.isPlaying()).isFalse();
    }
}