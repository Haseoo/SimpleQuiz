import gamecore.Game;
import gamecore.players.PlayerInternal;
import gamecore.questions.Question;

import java.util.Scanner;

public class TextMode {
    public static void main(String[] args) {
        try {
            Game game;
            Scanner scanner = new Scanner(System.in);
            int gameMode = 0;
            do {
                System.out.println("Wybierz tryb gry:\n1.WPBO");
                String buffer = scanner.nextLine();
                try {
                    gameMode = Integer.parseInt(buffer);
                } catch (Exception e) {
                    gameMode = 0;
                }
            } while (gameMode != 1);

            Game.GameModes choicedGameMode = null;

            switch (gameMode) {
                case 1:
                    choicedGameMode = Game.GameModes.ALL_QUESTIONS_WFO;
            }

            int numberOfPlayers = 0;
            do {
                System.out.println("Podaj ilość graczy");
                String buffer = scanner.nextLine();
                try {
                    numberOfPlayers = Integer.parseInt(buffer);
                } catch (Exception e) {
                    numberOfPlayers = 0;
                }
            } while (numberOfPlayers <= 0);

            String[] playersNickNames = new String[numberOfPlayers];

            for(int i = 0; i < numberOfPlayers; i++) {
                System.out.printf("Podaj nick %d gracza: ", i + 1);
                String buffer = scanner.nextLine();
                playersNickNames[i] = buffer;
            }

            game = Game.getInstance(choicedGameMode, playersNickNames);

            PlayerInternal.Player[] players = game.getPlayerList();

            while(!game.gameHasEnded()) {
                Question newQuestion = game.getNewQuestion();
                System.out.printf("Pytanie: %s\n", newQuestion.questionContent);
                for (PlayerInternal.Player it : players) {
                    System.out.printf("Teraz odpowiada %s\n", it.getPlayerName());
                    int odp = 0;
                    while (odp <= 0) {
                        try {
                            System.out.print("Twoja odpowiedź: ");
                            scanner.reset();
                            String buffer = scanner.nextLine();
                            odp = Integer.parseInt(buffer);
                        } catch (Exception e) {
                            odp = 0;
                        }
                    }
                    System.out.println(((game.setAnswerFromPlayer(it, newQuestion, odp) ? "Poprawna odpowiedź" : "Niepoprawna odpowiedź")));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
