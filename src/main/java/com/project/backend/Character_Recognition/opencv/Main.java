package com.project.backend.Character_Recognition.opencv;


import org.opencv.core.Core;
//import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class Main {

    static{
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        System.out.println("Welcome"+Core.VERSION);

//        HighGui.imshow("Test",Imgcodecs.imread("/mnt/DEV/Workspace/IntellijWorkspace/Anspro-2019/Anspro Projects/AI-6/AI6-BE/src/main/resources/dataset/shirts/images (4).jpg"));


    }
}
