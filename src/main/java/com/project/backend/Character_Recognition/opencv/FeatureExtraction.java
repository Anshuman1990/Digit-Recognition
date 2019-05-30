package com.project.backend.Character_Recognition.opencv;

import org.aspectj.asm.internal.NameConvertor;

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class FeatureExtraction {
    private Rect histoRect = new Rect(new Point(295, 215), new Point(345, 265));
    private static int counter = 0;

    public MatOfKeyPoint ExtractORBFeatures(Mat inputImage) {

        FeatureDetector features = FeatureDetector.create(FeatureDetector.ORB);
        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
        features.detect(inputImage, objectKeyPoints);
        KeyPoint[] keypoints = objectKeyPoints.toArray();
//        System.out.println(keypoints);

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
//        System.out.println("Computing descriptors...");
        descriptorExtractor.compute(inputImage, objectKeyPoints, objectDescriptors);

        Mat outputImage = new Mat();
        Scalar newKeypointColor = new Scalar(255, 0, 0);

//        System.out.println("Drawing key points on object image...");
        Features2d.drawKeypoints(inputImage, objectKeyPoints, outputImage, newKeypointColor, 0);

        Imgcodecs.imwrite("D:\\images\\Dataset Features\\" + counter + ".jpg", outputImage);
//        outputImage = captureHistogram(outputImage);
        counter++;
        return objectDescriptors;
    }

    public LinkedList<DMatch> DetectORBFeature(Mat img1, Mat keypoints1) {
        //        List<ObjectDescriptors> listObjectDescriptors = MyVideoCapture.getObjectList();
        FeatureDetector features = FeatureDetector.create(FeatureDetector.ORB);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        for (ObjectDescriptors obj : listObjectDescriptors) {
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();
//        System.out.println("Detecting key points in background image..." + img1);

        features.detect(img1, sceneKeyPoints);
//        System.out.println("Computing descriptors in background image...");

        descriptorExtractor.compute(img1, sceneKeyPoints, sceneDescriptors);
        Mat outputImage = new Mat();

        Scalar newKeypointColor = new Scalar(255, 0, 0);

//        System.out.println("Drawing key points on object image...");
        Features2d.drawKeypoints(img1, sceneKeyPoints, outputImage, newKeypointColor, 0);

        Imgcodecs.imwrite("D:\\images\\input Features\\" + counter + ".jpg", outputImage);
        counter++;

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
//        System.out.println("Matching object and scene images..." + keypoints1);
        LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();

        if (!sceneDescriptors.empty()) {


            descriptorMatcher.knnMatch(keypoints1, sceneDescriptors, matches, 2);

//        System.out.println("Calculating good match list...");

            System.out.println("matches= " + matches.size());
            float nndrRatio = 0.7f;

            for (int i = 0; i < matches.size(); i++) {
                MatOfDMatch matofDMatch = matches.get(i);
                DMatch[] dmatcharray = matofDMatch.toArray();
                if (dmatcharray.length == 2) {
                    DMatch m1 = dmatcharray[0];
                    DMatch m2 = dmatcharray[1];
                    System.out.println("m1= " + m1.distance + "m2= " + m2.distance);
                    if (m1.distance <= m2.distance * nndrRatio) {
                        goodMatchesList.addLast(m1);

                    }
                }
            }
        }

        return goodMatchesList;
    }


    public LinkedList<DMatch> extractAndDetectORB(Mat object,Mat scene){

        Mat homography = new Mat(3, 3, CvType.CV_64F);
        LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();
        FeatureDetector features = FeatureDetector.create(FeatureDetector.AKAZE);
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.AKAZE);
        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
        features.detect(object, objectKeyPoints);
        KeyPoint[] keypoints = objectKeyPoints.toArray();
//        System.out.println(keypoints);

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();

//        System.out.println("Computing descriptors...");
        descriptorExtractor.compute(object, objectKeyPoints, objectDescriptors);

        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();

        features.detect(scene, sceneKeyPoints);
//        System.out.println("Computing descriptors in background image...");

        descriptorExtractor.compute(scene, sceneKeyPoints, sceneDescriptors);

        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        if(!sceneDescriptors.empty()){

//            List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
            List<MatOfDMatch> knnMatches = new ArrayList<>();
            descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, knnMatches,2);

            Mat object_output = new Mat();
            Scalar Color = new Scalar(255, 0, 0);
//        System.out.println("Drawing key points on object image...");
            Features2d.drawKeypoints(object, objectKeyPoints, object_output, Color, 0);
            Imgcodecs.imwrite("D:\\images\\Dataset Features\\" + counter + ".jpg", object_output);

            Mat scene_output = new Mat();
//        System.out.println("Drawing key points on object image...");
            Features2d.drawKeypoints(scene, sceneKeyPoints, scene_output, Color, 0);
            Imgcodecs.imwrite("D:\\images\\input Features\\" + counter + ".jpg", scene_output);
            counter++;

            float ratioThreshold = 0.8f; // Nearest neighbor matching ratio
            List<KeyPoint> listOfMatched1 = new ArrayList<>();
            List<KeyPoint> listOfMatched2 = new ArrayList<>();
            List<KeyPoint> listOfKeypoints1 = objectKeyPoints.toList();
            List<KeyPoint> listOfKeypoints2 = sceneKeyPoints.toList();
            System.out.println("KNN= "+knnMatches.size());
            for (int i = 0; i < knnMatches.size(); i++) {
                DMatch[] matches = knnMatches.get(i).toArray();
                float dist1 = matches[0].distance;
                float dist2 = matches[1].distance;
                if (dist1 < ratioThreshold * dist2) {
                    listOfMatched1.add(listOfKeypoints1.get(matches[0].queryIdx));
                    listOfMatched2.add(listOfKeypoints2.get(matches[0].trainIdx));
                }
            }
            double inlierThreshold = 2.5; // Distance threshold to identify inliers with homography check
            List<KeyPoint> listOfInliers1 = new ArrayList<>();
            List<KeyPoint> listOfInliers2 = new ArrayList<>();
            List<DMatch> listOfGoodMatches = new ArrayList<>();
            for (int i = 0; i < listOfMatched1.size(); i++) {
                Mat col = new Mat(3, 1, CvType.CV_64F);
                double[] colData = new double[(int) (col.total() * col.channels())];
                colData[0] = listOfMatched1.get(i).pt.x;
                colData[1] = listOfMatched1.get(i).pt.y;
                colData[2] = 1.0;
                col.put(0, 0, colData);
                Mat colRes = new Mat();
                Core.gemm(homography, col, 1.0, new Mat(), 0.0, colRes);
                colRes.get(0, 0, colData);
                Core.multiply(colRes, new Scalar(1.0 / colData[2]), col);
                col.get(0, 0, colData);
                double dist = Math.sqrt(Math.pow(colData[0] - listOfMatched2.get(i).pt.x, 2) +
                        Math.pow(colData[1] - listOfMatched2.get(i).pt.y, 2));
                if (dist < inlierThreshold) {


                    listOfGoodMatches.add(new DMatch(listOfInliers1.size(), listOfInliers2.size(), 0));
                    listOfInliers1.add(listOfMatched1.get(i));
                    listOfInliers2.add(listOfMatched2.get(i));
                }
            }
        }



        return goodMatchesList;
    }




    private Mat captureHistogram(Mat image) {//captures the histogram
        Mat hisArea = image.submat(histoRect);
        Imgproc.cvtColor(hisArea, hisArea, Imgproc.COLOR_BGR2HSV);
        ArrayList<Mat> matList = new ArrayList<>();
        matList.add(hisArea);
        Mat histogram = new Mat();
        Imgproc.calcHist(matList, new MatOfInt(0, 1), new Mat(), histogram, new MatOfInt(12, 150), new MatOfFloat(0, 180, 0, 256));
        Core.normalize(histogram, histogram, 0, 255, Core.NORM_MINMAX);

        return histogram;
    }

    public final void saveObject(String path, MatOfKeyPoint mat, String fileName) {
        File file = new File(path + File.separator + fileName).getAbsoluteFile();
        if (file.isFile()) {
            try {
                int cols = mat.cols();
                byte[] data = new byte[(int) mat.total() * mat.channels()];
                mat.get(0, 0, data);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + fileName))) {
                    oos.writeObject(cols);
                    oos.writeObject(data);
                    oos.close();
                }
            } catch (IOException | ClassCastException ex) {
                System.err.println("ERROR: Could not save mat to file: " + path);
            }
        }
    }

    public final Mat loadMObject(String path) {
        try {
            int cols;
            byte[] data;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
                cols = (int) ois.readObject();
                data = (byte[]) ois.readObject();
            }

            Mat mat = new Mat(data.length / cols, cols, CvType.CV_8UC1);
            mat.put(0, 0, data);
            return mat;
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            System.err.println("ERROR: Could not load mat from file: " + path);
        }
        return null;
    }
}
