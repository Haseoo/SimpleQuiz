package gamecore.projections.players;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Player {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private Integer score = 0;
    private boolean isPlaying = true;

    @NoArgsConstructor
    public class PlayerInfo {
        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getScore() {
            return score;
        }

        public boolean isPlaying() {
            return isPlaying;
        }
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public void playerLost() {
        isPlaying = false;
    }
}
