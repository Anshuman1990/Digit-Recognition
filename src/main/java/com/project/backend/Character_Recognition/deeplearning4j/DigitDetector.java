package com.project.backend.Character_Recognition.deeplearning4j;


import com.project.backend.Character_Recognition.deeplearning4j.ui.ImageUtils;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DigitDetector {

    private final MultiLayerNetwork mnistModel;

    public DigitDetector() throws IOException,
            InvalidKerasConfigurationException,
            UnsupportedKerasConfigurationException {
        String systemPath = System.getProperty("user.dir");
        System.out.println("__________"+systemPath);
        final File file = new File(systemPath+"\\src\\main\\resources\\deeplearning-model\\mnist.h5f");
        if (!file.exists()) {
            throw new IOException("The Keras model file does not exits. Run the mnist-trainer.py"
                                  + " python script first");
        }

        mnistModel = KerasModelImport.importKerasSequentialModelAndWeights(file.getAbsolutePath());
    }

    public int recognize(final BufferedImage image) {
        final double[] pixels = ImageUtils.getPixelsAndConvertToBlackAndWhite(image);


        final int[] inputDataShape = new int[]{1,
                                               1,
                                               ImageUtils.MNIST_IMAGE_SIZE,
                                               ImageUtils.MNIST_IMAGE_SIZE};
        // 'K' means memory layout order as defined in
        // https://docs.scipy.org/doc/numpy/reference/generated/numpy.array.html#numpy.array
        final NDArray drawnDigit = new NDArray(pixels, inputDataShape, 'K');
        final INDArray output = mnistModel.output(drawnDigit);

        return findLargestIndex(output);
    }

    private int findLargestIndex(INDArray array) {
        int largestIndex = 0;
        double largestValue = Double.MIN_VALUE;

        for (int i = 0; i < array.rows(); i++) {
            if (array.getDouble(i) > largestValue) {
                largestIndex = i;
                largestValue = array.getDouble(i);
            }
        }

        return largestIndex;
    }
}
