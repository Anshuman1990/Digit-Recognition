package com.project.backend.Character_Recognition.deeplearning4j;


import com.project.backend.Character_Recognition.deeplearning4j.ui.MainPanel;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Mika on 25/03/17.
 */
public class Main {

    public static final int UI_SIZE = 600;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                createFrame().setVisible(true);
            } catch (InvalidKerasConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedKerasConfigurationException e) {
                e.printStackTrace();
            }
        });
    }

    private static JFrame createFrame() throws InvalidKerasConfigurationException,
            IOException,
            UnsupportedKerasConfigurationException {
        final JFrame frame = new JFrame("Draw a number");
        frame.setSize(UI_SIZE, UI_SIZE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final MainPanel mainUi = new MainPanel();
        frame.add(mainUi);

        return frame;
    }
}
