package ge.chessplayer.exception;

public class ChessPlayerException extends Exception {

    private String message;

    public ChessPlayerException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
