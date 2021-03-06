package com.project.backend.Character_Recognition.opencv.face_detection;

import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


import com.project.backend.Character_Recognition.opencv.ImageCropper;
import com.project.backend.Character_Recognition.opencv.showResult;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


import java.awt.image.DataBufferByte;


public class Main{
	private CascadeClassifier faceCascade;
	private int absoluteFaceSize;
	static
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

	}

	public static void main(String[] args) throws IOException
	{
		Main mn = new Main();
        String path = mn.browse();
        BufferedImage image = ImageIO.read(new File(path));
        System.out.println("path="+path);
        byte[] b = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
         mat.put(0,0, b);
      //   new showResult(mat, "Original Image");
      mn.init(mat,"C:/Users/sumit/eclipse-workspace1/smart_glass/resources/haarcascades/haarcascade_frontalface_alt.xml",path);
	}
	public void init(Mat frame,String path,String fpath)
	{
		this.faceCascade = new CascadeClassifier();
		this.absoluteFaceSize = 0;
		this.faceCascade.load(path);
		detectAndDisplay(frame,fpath);
		new showResult(frame, "Detected");
	}

	private void detectAndDisplay(Mat frame,String fpath)
	{
		MatOfRect faces = new MatOfRect();
		Mat grayFrame = new Mat();

		// convert the frame in gray scale
		Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(grayFrame, grayFrame);

		// compute minimum face size (20% of the frame height, in our case)
		if (this.absoluteFaceSize == 0)
		{
			int height = grayFrame.rows();
			if (Math.round(height * 0.2f) > 0)
			{
				this.absoluteFaceSize = Math.round(height * 0.2f);
			}
		}
		System.out.println(">>>>"+this.absoluteFaceSize);
		// detect faces
//		this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
//				new Size(this.absoluteFaceSize, this.absoluteFaceSize), new Size());
		this.faceCascade.detectMultiScale(grayFrame, faces);

		// each rectangle in faces is a face: draw them!
		Rect[] facesArray = faces.toArray();
		System.out.println("number of heads= "+facesArray.length);
//		for (int i = 0; i < facesArray.length; i++)
//			Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);
		ImageCropper ic=new ImageCropper();
		int i=0;
		for(Rect R:facesArray){

			Imgproc.rectangle(frame, new Point(R.x,R.y),new Point(R.x+R.width,R.y+R.height), new Scalar(0, 255, 0));
			System.out.println("******"+i);
			System.out.println("R.x.."+R.x);
			System.out.println("R.y.."+R.y);
			System.out.println("R.x+R.width="+R.width);
			System.out.println("R.y+R.height="+R.height);
			ic.crop(fpath, "C:/Users/sumit/Desktop/"+i+".jpg", R.x, R.y, R.width, R.height);
			i++;

		}
}

	private String  browse()
    {
        JFrame frame = new JFrame("Select Image");
        frame.setBounds(0,0,500,500);
        FileDialog fd = new FileDialog(frame,"Select Image",FileDialog.LOAD);
        fd.show();
        String path = fd.getDirectory()+fd.getFile();

        return path;
    }
}