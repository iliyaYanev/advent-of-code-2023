package util;

public class Ghost {

    private Node position;
    private Long lastMatchIndex = null;

    public Ghost() { }

    public Node getPosition() {
        return position;
    }

    public void setPosition(Node position) {
        this.position = position;
    }

    public Long getLastMatchIndex() {
        return lastMatchIndex;
    }

    public void setLastMatchIndex(Long lastMatchIndex) {
        this.lastMatchIndex = lastMatchIndex;
    }
}
