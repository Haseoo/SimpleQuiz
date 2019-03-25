class Node {
    public Player player;
    public Node next;
}
public class PlayerList {
    private Node currentNode = null;
    private Node head = null;
    private int numberOfPlayers = 0;

    public void addPlayer(Player player) {
        if (head == null) {
            head = new Node();
            head.player = player;
            head.next = head;
            currentNode = head;
        } else {
            Node tmp =  head;
            while (tmp.next != head) {
                tmp = tmp.next;
            }
            tmp.next = new Node();
            currentNode = tmp.next;
            tmp.next.player = player;
            tmp.next.next = head;
        }
        numberOfPlayers++;
    }

    public Player getCurrentPlayer() {
        return currentNode.player;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void nextPlayer() {
        currentNode = currentNode.next;
    }

    public void resetCurrent() {
        currentNode = head;
    }
    

    static public void main(String[] args) {
        PlayerList test = new PlayerList();

        test.addPlayer(new Player("Anna"));
        test.getCurrentPlayer().addScore(5);
        test.addPlayer(new Player("Wanna"));
        test.getCurrentPlayer().addScore(10);
        test.getCurrentPlayer().playerIsLost();
        test.addPlayer(new Player("Hanna"));
        test.getCurrentPlayer().addScore(15);

        test.resetCurrent();

        for (int i = 0; i < test.getNumberOfPlayers(); i++) {
            if (test.getCurrentPlayer().isPlayerPlaying())
                System.out.println(test.getCurrentPlayer());
            test.nextPlayer();
        }

    }

}