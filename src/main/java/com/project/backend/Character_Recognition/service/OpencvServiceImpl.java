package com.project.backend.Character_Recognition.service;

import com.project.backend.Character_Recognition.deeplearning4j.DigitDetector;
import com.project.backend.Character_Recognition.deeplearning4j.ui.ImageUtils;
import com.project.backend.Character_Recognition.opencv.FeatureExtraction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

@Service
public class OpencvServiceImpl implements OpencvService {

    @Value("classpath:dataset/digit/*")
    private Resource[] resoucesDataset;


    private DigitDetector digitDetector;

    @Override
    public Map<String, Object> processDataset(File file) throws IOException, InterruptedException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        this.digitDetector = new DigitDetector();
        Map output = new HashMap();
        Mat source = Imgcodecs.imread(file.getPath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Size size = new Size(2480, 3508);
//            Rect source_rect = new Rect(0, 0, 2480, 3508);
        Mat source_resize = new Mat();
        Imgproc.resize(source, source_resize, size);
        Mat grey = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());
        Mat blur = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());
        Mat unsharp = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());
        Mat binary = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());
        Mat invert = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());
        Mat dilate = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());
        Mat hierarchy = new Mat(source_resize.rows(), source_resize.cols(), source_resize.type());


        Imgproc.cvtColor(source_resize, grey, Imgproc.COLOR_RGB2GRAY, 0);
        Imgproc.GaussianBlur(grey, blur, new Size(3, 3), 10);
//        Core.addWeighted(blur, 1.5, unsharp, -0.5, 0, unsharp);
        Imgproc.threshold(blur, binary, 0, 255, Imgproc.THRESH_OTSU);
        Imgproc.threshold(binary, invert, 0, 255, Imgproc.THRESH_BINARY_INV);
        int kernelSize = 1;
        Mat verti_kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 160));
        Mat hori_kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(130, 1));

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));
        Imgproc.dilate(invert, dilate, element);


        String binarizedImageName = file.getName().substring(0, file.getName().indexOf(".")) + "_ocr.jpg";
        String binarizedImagePath = file.getPath().substring(0, file.getPath().lastIndexOf(File.separator));

        Imgcodecs.imwrite(binarizedImagePath + File.separator + binarizedImageName, dilate);
        int height = dilate.height();
        int width = dilate.width();
        System.out.println("height= " + height + "width= " + width);
        Rect test1 = new Rect(600, 350, 1700, 800);
        Rect test2 = new Rect(600, 1400, 1700, 800);
        Rect test3 = new Rect(600, 2550, 1700, 800);


        Mat test_1 = new Mat(dilate, test1);
        Mat test_2 = new Mat(dilate, test2);
        Mat test_3 = new Mat(dilate, test3);

        Mat horizontal_erode_1 = new Mat(test_1.rows(), test_1.cols(), test_1.type());
        Mat vertical_erode_1 = new Mat(test_1.rows(), test_1.cols(), test_1.type());
        Mat horizontal_dilate_1 = new Mat(test_1.rows(), test_1.cols(), test_1.type());
        Mat vertical_dilate_1 = new Mat(test_1.rows(), test_1.cols(), test_1.type());
        Mat finalImage_1 = new Mat(test_1.rows(), test_1.cols(), test_1.type());

        Mat horizontal_erode_2 = new Mat(test_2.rows(), test_2.cols(), test_2.type());
        Mat vertical_erode_2 = new Mat(test_2.rows(), test_2.cols(), test_2.type());
        Mat horizontal_dilate_2 = new Mat(test_2.rows(), test_2.cols(), test_2.type());
        Mat vertical_dilate_2 = new Mat(test_2.rows(), test_2.cols(), test_2.type());
        Mat finalImage_2 = new Mat(test_1.rows(), test_1.cols(), test_1.type());

        Mat horizontal_erode_3 = new Mat(test_3.rows(), test_3.cols(), test_3.type());
        Mat vertical_erode_3 = new Mat(test_3.rows(), test_3.cols(), test_3.type());
        Mat horizontal_dilate_3 = new Mat(test_3.rows(), test_3.cols(), test_3.type());
        Mat vertical_dilate_3 = new Mat(test_3.rows(), test_3.cols(), test_3.type());
        Mat finalImage_3 = new Mat(test_1.rows(), test_1.cols(), test_1.type());


        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        double alpha = 0.5;
        double beta = 1.0 - alpha;
//Image 1
        Imgproc.erode(test_1, vertical_erode_1, verti_kernel);
        Imgproc.dilate(vertical_erode_1, vertical_dilate_1, verti_kernel);


        Imgproc.erode(test_1, horizontal_erode_1, hori_kernel);
        Imgproc.dilate(horizontal_erode_1, horizontal_dilate_1, hori_kernel);

        Core.addWeighted(vertical_dilate_1, alpha, horizontal_dilate_1, beta, 0.0, finalImage_1);
//Image 2
        Imgproc.erode(test_2, vertical_erode_2, verti_kernel);
        Imgproc.dilate(vertical_erode_2, vertical_dilate_2, verti_kernel);

        Imgproc.erode(test_2, horizontal_erode_2, hori_kernel);
        Imgproc.dilate(horizontal_erode_2, horizontal_dilate_2, hori_kernel);

        Core.addWeighted(vertical_dilate_2, alpha, horizontal_dilate_2, beta, 0.0, finalImage_2);
//Image 3
        Imgproc.erode(test_3, vertical_erode_3, verti_kernel);
        Imgproc.dilate(vertical_erode_3, vertical_dilate_3, verti_kernel);

        Imgproc.erode(test_3, horizontal_erode_3, hori_kernel);
        Imgproc.dilate(horizontal_erode_3, horizontal_dilate_3, hori_kernel);

        Core.addWeighted(vertical_dilate_3, alpha, horizontal_dilate_3, beta, 0.0, finalImage_3);


        Imgcodecs.imwrite(binarizedImagePath + File.separator + "cropped_1.jpg", test_1);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "horizontal_1.jpg", horizontal_dilate_1);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "vertical_1.jpg", vertical_dilate_1);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "final_1.jpg", finalImage_1);


        Imgcodecs.imwrite(binarizedImagePath + File.separator + "cropped_2.jpg", test_2);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "horizontal_2.jpg", horizontal_dilate_2);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "vertical_2.jpg", vertical_dilate_2);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "final_2.jpg", finalImage_2);

        Imgcodecs.imwrite(binarizedImagePath + File.separator + "cropped_3.jpg", test_3);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "horizontal_3.jpg", horizontal_dilate_3);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "vertical_3.jpg", vertical_dilate_3);
        Imgcodecs.imwrite(binarizedImagePath + File.separator + "final_3.jpg", finalImage_3);


        List<String> result_1 = extractAndMatch(test_1, finalImage_1, hierarchy, Path.test1_folder);
        List<String> result_2 = extractAndMatch(test_2, finalImage_2, hierarchy, Path.test2_folder);
        List<String> result_3 = extractAndMatch(test_3, finalImage_3, hierarchy, Path.test3_folder);
//        extractAndMatch(test_2,hierarchy,result_2);
//        extractAndMatch(test_3,hierarchy,result_3);


//        result_1 = format(result_1);
//        result_2 = format(result_2);
//        result_3 = format(result_3);
//        FileUtils.cleanDirectory(new File("D:\\images\\test1\\"));
//        FileUtils.cleanDirectory(new File("D:\\images\\test2\\"));
//        FileUtils.cleanDirectory(new File("D:\\images\\test3\\"));
        output.put("test1", result_1);
        output.put("test2", result_2);
        output.put("test3", result_3);

        return output;
    }


    private List<String> extractAndMatch(Mat test, Mat finalImg, Mat hierarchy, final String path) throws IOException {
        System.out.println("###############################" + path + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<String> result = new ArrayList<>();
        final StringBuilder sbuild = new StringBuilder();
        List<MatOfPoint> contours = new LinkedList<MatOfPoint>();
        Imgproc.findContours(finalImg, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        contours.forEach(matOfPoint -> {
            sbuild.append("\n");
            Rect rect = Imgproc.boundingRect(matOfPoint);
//            if((rect.width > 160 && rect.height > 130) && rect.width > 3 * rect.height){
//            System.out.println(rect.width + "," + rect.height);
            int x = rect.x;
            int y = rect.y;
            int height = rect.height;
            int width = rect.width;
            if (x > 0 && y > 40) {
                Mat test_result = test.submat(rect);
                Mat detectedEdges = new Mat();
                Imgproc.Canny(test_result, detectedEdges, 0, 10, 3, false);
                int totalWhitePixel = countBlackPixel(detectedEdges);
                int totalNumberOfPixels = 160 * 140;
                int percentage = percentCalculation(totalWhitePixel, totalNumberOfPixels);
//                System.out.println("PERCENT " + x + "_" + y + ".jpg__________" + percentage);
                if (percentage > 3) {
                    List<MatOfPoint> contours_inner = new LinkedList<>();
                    Mat hierarchy_inner = new Mat();
                    Imgcodecs.imwrite(path + x + "_" + y + ".jpg", test_result);
                    Imgproc.findContours(test_result, contours_inner, hierarchy_inner, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE,new Point(3,3));
                    contours_inner.forEach(matOfPoint1 -> {
                        double area = Imgproc.contourArea(matOfPoint1);
                        Rect rect_test = Imgproc.boundingRect(matOfPoint1);
                        if (area > 400 && area < 5000 && rect_test.height > 50 && rect_test.width > 50) {
                            Mat digit = test_result.submat(rect_test);
                            Mat inverse = new Mat();
                            Core.bitwise_not(digit, inverse);
                            Imgcodecs.imwrite(path + "contours" + File.separator + area + "_" + digit.width() + "_" + digit.height() + ".jpg", inverse);
                            BufferedImage img = (BufferedImage) HighGui.toBufferedImage(inverse);
                            try {
                                int predictedValue = this.deepLearningDigit(img);
                                sbuild.append(predictedValue);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
//            }
            }
        });
        String[] s = sbuild.toString().split("\n");
        for (String s1 : s) {
            if (!s1.isEmpty()) {
                System.out.println(s1);
            }
        }
        return result;
    }

    private String readPdf(String pdfPath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {

            document.getClass();


            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            PDFTextStripper tStripper = new PDFTextStripper();

            String pdfFileInText = tStripper.getText(document);
            //System.out.println("Text:" + st);

            // split by whitespace
            String[] lines = pdfFileInText.split("\\r?\\n");
            for (String line : lines) {
                stringBuilder.append(line);
            }

        }
        return stringBuilder.toString();
    }


    public void training() throws IOException {
        FeatureExtraction extraction = new FeatureExtraction();
        for (Resource resource : resoucesDataset) {
            if (resource.getFile().isDirectory()) {
                File[] files = resource.getFile().listFiles();
                for (File file : files) {
                    Mat _input = Imgcodecs.imread(file.getPath());
                    Mat _grey = new Mat(_input.rows(), _input.cols(), _input.type());
                    Mat _blur = new Mat(_input.rows(), _input.cols(), _input.type());
                    Mat _unsharp = new Mat(_input.rows(), _input.cols(), _input.type());
                    Mat _binary = new Mat(_input.rows(), _input.cols(), _input.type());
                    Mat _invert = new Mat(_input.rows(), _input.cols(), _input.type());
                    Mat _dilate = new Mat(_input.rows(), _input.cols(), _input.type());
                    Mat cannyOutput = new Mat();
                    Mat greyImage = new Mat();
                    Imgproc.cvtColor(_input, _grey, Imgproc.COLOR_RGB2GRAY, 0);
                    Imgproc.GaussianBlur(_grey, _blur, new Size(3, 3), 10);
                    Imgproc.threshold(_blur, _binary, 0, 255, Imgproc.THRESH_OTSU);
                    Imgproc.threshold(_binary, _invert, 0, 255, Imgproc.THRESH_BINARY_INV);
                    int _kernelSize = 1;
                    Mat _element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(2 * _kernelSize + 1, 2 * _kernelSize + 1),
                            new Point(_kernelSize, _kernelSize));
                    Imgproc.dilate(_invert, _dilate, _element);
                    Imgproc.Canny(_dilate, cannyOutput, 100, 200);

                    String imgName = file.getName().substring(0, file.getName().indexOf(".")) + "_denoise.jpg";
                    String imgPath = file.getPath().substring(0, file.getPath().lastIndexOf(File.separator));
                    File outputFile = new File(imgPath + File.separator + imgName);
                    Imgcodecs.imwrite(imgPath + File.separator + imgName, cannyOutput);

                    MatOfKeyPoint keypoints = extraction.ExtractORBFeatures(cannyOutput);
                    String binName = file.getName().substring(0, file.getName().indexOf(".")) + "_bin";
                    new File(imgPath + File.separator + binName).createNewFile();
                    extraction.saveObject(imgPath, keypoints, binName);
                }
            }
        }
    }

    public int deepLearningDigit(final BufferedImage image) throws IOException {
//        BufferedImage img =  ImageIO.read(new File("D:\\images\\test3\\94_109.jpg"));

        final Rectangle bounds = ImageUtils.findBoundsOfBlackShape(image);
        final Dimension newDim = getScaledMnistDigitDimensions(bounds);

        // Get only the digit out from the rest of the image
        final BufferedImage cropped = image.getSubimage(bounds.x,
                bounds.y,
                bounds.width,
                bounds.height);


        // Scale the digit to 20x20 box as required by MNIST
        final BufferedImage scaled = ImageUtils.scale(cropped, newDim.width, newDim.height);

        // Put the 20x20 image to 28x28 background and center it by the
        // center of mass.
        final BufferedImage mnistInputImage
                = ImageUtils.addImageToCenter(scaled,
                ImageUtils.MNIST_IMAGE_SIZE,
                ImageUtils.MNIST_IMAGE_SIZE);

        // Add images to the UI
//        croppedImage.setIcon(new ImageIcon(cropped));
//        scaledImage.setIcon(new ImageIcon(scaled));
//        finalImage.setIcon(new ImageIcon(mnistInputImage));

        final int result = digitDetector.recognize(mnistInputImage);
//        resultText.setText("You wrote: " + result);
        return result;
    }

    private static Dimension getScaledMnistDigitDimensions(final Rectangle bounds) {

        final int originalWidth = bounds.width;
        final int originalHeight = bounds.height;
        final int boundWidth = ImageUtils.MNIST_DIGIT_BOUNDS_SIZE;
        final int boundHeight = ImageUtils.MNIST_DIGIT_BOUNDS_SIZE;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        // first check if we need to scale width
        if (originalWidth > boundWidth) {
            //scale width to fit
            newWidth = boundWidth;
            //scale height to maintain aspect ratio
            newHeight = (newWidth * originalHeight) / originalWidth;
        }

        // then check if we need to scale even with the new height
        if (newHeight > boundHeight) {
            //scale height to fit instead
            newHeight = boundHeight;
            //scale width to maintain aspect ratio
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        return new Dimension(newWidth, newHeight);
    }

    private int countBlackPixel(Mat src) {
        int totalNumberOfPixels = 160 * 140;
        return (totalNumberOfPixels - Core.countNonZero(src));
    }

    private int percentCalculation(int newNumber, int oldNumber) {
        return 100 - ((newNumber * 100) / oldNumber);
    }
}
