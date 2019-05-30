package com.project.backend.Character_Recognition.deeplearning4j.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface NumberDrawnListener {

    void drawingFinished(final BufferedImage image) throws IOException;
}
