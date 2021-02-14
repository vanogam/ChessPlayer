package ge.chessplayer.model.chess;

public enum PieceColor {
    WHITE("white"), BLACK("black");

    private String name;

    PieceColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
