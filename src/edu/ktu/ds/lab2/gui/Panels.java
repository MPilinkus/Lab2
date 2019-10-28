package edu.ktu.ds.lab2.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author darius
 */
public class Panels extends GridPane {

    private final static int SPACING = 4;
    private final List<TextField> tfs = new ArrayList<>();
    private final List<Button> btns = new ArrayList<>();

    /**
     * Sukuriama parametrų lentelė (GridPane išdėstymo dėsnis)
     * <pre>
     * |-------------------------------|
     * |                |------------| |
     * |   lblTexts[0]  | tfTexts[0] | |
     * |                |------------| |
     * |                               |
     * |                |------------| |
     * |   lblTexts[1]  | tfTexts[1] | |
     * |                |------------| |
     * |      ...             ...      |
     * |-------------------------------|
     * </pre>
     *
     * @param lblTexts
     * @param tfTexts
     * @param columnWidth
     */
    public Panels(String[] lblTexts, String[] tfTexts, int columnWidth) {
        super();

        if (lblTexts == null || tfTexts == null) {
            throw new IllegalArgumentException("Arguments for table of parameters are incorrect");
        }

        if (lblTexts.length > tfTexts.length) {
            tfTexts = Arrays.copyOf(tfTexts, lblTexts.length);
            Arrays.fill(tfTexts, "");
        }

        paneLayout();
        initTableOfParameters(columnWidth, lblTexts, tfTexts);
    }

    /**
     * Sukuriamas mygtukų tinklelis (GridPane išdėstymo dėsnis)
     * <pre>
     * |-------------------------------------|
     * | |-------------| |-------------|     |
     * | | btnNames[0] | | btnNames[1] | ... |
     * | |-------------| |-------------|     |
     * |                                     |
     * | |-------------| |-------------|     |
     * | | btnNames[2] | | btnNames[3] | ... |
     * | |-------------| |-------------|     |
     * |       ...              ...          |
     * |-------------------------------------|
     * </pre>
     *
     * @param btnNames
     * @param gridX
     * @param gridY
     */
    public Panels(String[] btnNames, int gridX, int gridY) {
        super();

        if (btnNames == null || gridX < 1 || gridY < 1) {
            throw new IllegalArgumentException("Arguments for buttons grid are incorrect");
        }

        paneLayout();
        initGridOfButtons(gridX, gridY, btnNames);
    }

    private void paneLayout() {
        setAlignment(Pos.CENTER);
        setHgap(SPACING);
        setVgap(SPACING);
        setPadding(new Insets(SPACING));
    }

    private void initTableOfParameters(int columnWidth, String[] lblTexts, String[] tfTexts) {
        for (int i = 0; i < lblTexts.length; i++) {
            add(new Label(lblTexts[i]), 0, i);
        }

        for (int i = 0; i < tfTexts.length; i++) {
            TextField tf = new TextField(tfTexts[i]);
            tf.setPrefWidth(columnWidth);
            tf.setAlignment(Pos.CENTER);
            tfs.add(tf);
            add(tf, 1, i);
        }
    }

    private void initGridOfButtons(int gridX, int gridY, String[] btnNames) {
        Queue<String> btnNamesQueue = new LinkedList<>(Arrays.asList(btnNames));
        for (int i = 0; i < gridY; i++) {
            for (int j = 0; j < gridX; j++) {
                if (btnNamesQueue.isEmpty()) {
                    break;
                }
                Button button = new Button(btnNamesQueue.poll());
                btns.add(button);
                button.setMaxWidth(Double.MAX_VALUE);
                add(button, j, i);
            }
        }
    }

    /**
     * Gražinamas lentelės laukelių reikšmių sąrašas
     *
     * @return Gražinamas lentelės laukelių reikšmių sąrašas
     */
    public List<String> getParametersOfTable() {
        return tfs.stream().map(TextInputControl::getText).collect(Collectors.toList());
    }

    /**
     * Gražinamas parametrų lentelės TextField objektų sąrašas
     *
     * @return Gražinamas parametrų lentelės TextField objektų sąrašas
     */
    public List<TextField> getTfOfTable() {
        return tfs;
    }

    /**
     * Gražinamas mygtukų tinklelio Button objektų sąrašas
     *
     * @return Gražinamas mygtukų tinklelio Button objektų sąrašas
     */
    public List<Button> getButtons() {
        return btns;
    }
}
