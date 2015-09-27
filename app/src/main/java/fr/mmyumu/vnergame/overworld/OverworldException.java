package fr.mmyumu.vnergame.overworld;

/**
 * Exception about the Overworld
 * Created by mmyumu on 26/09/2015.
 */
public class OverworldException extends RuntimeException {
    public OverworldException() {
    }

    public OverworldException(String detailMessage) {
        super(detailMessage);
    }

    public OverworldException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public OverworldException(Throwable throwable) {
        super(throwable);
    }

}
