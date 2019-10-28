package edu.ktu.ds.lab2.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Klasė, skirta duomenų išvedimui į GUI
 *
 * @author darius
 */
public class KsGui {

    private static int lineNr;
    private static boolean formatStartOfLine = true;

    public static void setFormatStartOfLine(boolean formatStartOfLine) {
        KsGui.formatStartOfLine = formatStartOfLine;
    }

    private static String getStartOfLine() {
        return (formatStartOfLine) ? ++lineNr + "| " : "";
    }

    public static void ou(TextArea ta, Object o) {
        StringBuilder sb = new StringBuilder();
        if (o instanceof Iterable) {
            for (Object iter : (Iterable) o) {
                sb.append(iter.toString()).append(System.lineSeparator());
            }
        } else {
            sb.append(o.toString());
        }
        Platform.runLater(() -> ta.appendText(sb.toString()));
    }

    public static void oun(TextArea ta, Object o) {
        StringBuilder sb = new StringBuilder();
        if (o instanceof Iterable) {
            for (Object iter : (Iterable) o) {
                sb.append(iter.toString()).append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
        } else {
            sb.append(o.toString()).append(System.lineSeparator());
        }
        Platform.runLater(() -> ta.appendText(sb.toString()));
    }

    public static void ou(TextArea ta, Object o, String msg) {
        String startOfLine = getStartOfLine();
        Platform.runLater(() -> ta.appendText(startOfLine + msg + ": "));
        oun(ta, o);
    }

    public static void oun(TextArea ta, Object o, String msg) {
        String startOfLine = getStartOfLine();
        Platform.runLater(() -> ta.appendText(startOfLine + msg + ": " + System.lineSeparator()));
        oun(ta, o);
    }

    public static void ounerr(TextArea ta, Exception e) {
        Region region = (Region) ta.lookup(".content");
        region.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        String startOfLine = getStartOfLine();
        Platform.runLater(() -> ta.appendText(startOfLine +
                e.getLocalizedMessage() +
                System.lineSeparator()));
    }

    public static void ounerr(TextArea ta, String msg) {
        Region region = (Region) ta.lookup(".content");
        region.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        String startOfLine = getStartOfLine();
        Platform.runLater(() -> ta.appendText(startOfLine + msg + ". " + System.lineSeparator()));
    }

    public static void ounerr(TextArea ta, String msg, String parameter) {
        Region region = (Region) ta.lookup(".content");
        region.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        String startOfLine = getStartOfLine();
        Platform.runLater(() -> ta.appendText(startOfLine + msg + ": " + parameter + System.lineSeparator()));
    }
}
