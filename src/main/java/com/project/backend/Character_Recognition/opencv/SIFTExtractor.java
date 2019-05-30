package com.project.backend.Character_Recognition.opencv;

import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
//import org.opencv.features2d.FastFeatureDetector;
//import org.opencv.features2d.Feature2D;
//import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;


public class SIFTExtractor {
    private Mat inputImage;
    private Rect histoRect = new Rect(new Point(0, 0), new Point(100, 100));

    public Mat captureHistogram(Mat image){//captures the histogram
        Mat hisArea = image.submat(histoRect);
        Imgproc.cvtColor(hisArea, hisArea, Imgproc.COLOR_BGR2HSV);
        ArrayList<Mat> matList = new ArrayList<>();
        matList.add(hisArea);
        Mat histogram = new Mat();
        Imgproc.calcHist(matList, new MatOfInt(0, 1), new Mat(), histogram, new MatOfInt(12, 150), new MatOfFloat(0, 180, 0, 256));
        Core.normalize(histogram, histogram, 0, 255, Core.NORM_MINMAX);
        return histogram;
    }

    public void ExtractSIFT(Mat inputImage){
//        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
//
//
//        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SURF);
//        System.out.println("Detecting key points...");
//        featureDetector.detect(inputImage, objectKeyPoints);
//        KeyPoint[] keypoints = objectKeyPoints.toArray();
//        System.out.println(keypoints);
//
//        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
//        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);
//        System.out.println("Computing descriptors...");
//        descriptorExtractor.compute(inputImage, objectKeyPoints, objectDescriptors);
    }
}
