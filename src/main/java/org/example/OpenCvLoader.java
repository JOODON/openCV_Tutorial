package org.example;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;


public class OpenCvLoader {
    public static void main(String[] args) {
        OpenCV.loadLocally(); // OpenCV 초기화

        String sourceImagePath = "C:\\SpringBoot\\openCV_Tutorial\\src\\main\\java\\loadLibrary\\img_1.png";
        String targetPath = "C:\\SpringBoot\\openCV_Tutorial\\src\\main\\java\\loadLibrary\\copy.png";

        // 얼굴을 검출하기 위한 CascadeClassifier 객체 생성 및 초기화
        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        cascadeClassifier.load("C:\\SpringBoot\\openCV_Tutorial\\src\\main\\resources\\haarcascade_frontalface_default.xml");

        // 이미지 로드
        Mat loadedImage = loadImage(sourceImagePath);

        // 그레이스케일로 변환
        Mat grayImage = new Mat();
        Imgproc.cvtColor(loadedImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // 얼굴 검출
        MatOfRect facesDetected = new MatOfRect();
        cascadeClassifier.detectMultiScale(grayImage, facesDetected);

        // 검출된 얼굴 주위에 사각형 그리기
        Rect[] faceArray = facesDetected.toArray();
        if (faceArray.length > 0) {
            // 얼굴이 검출된 경우
            for (Rect face : faceArray) {
                System.out.println(face);
                Imgproc.rectangle(loadedImage, face.tl(), face.br(), new Scalar(0, 0, 255), 2);
                JLabel label = new JLabel("사람입니다");
            }
        } else {
            // 얼굴이 검출되지 않은 경우
            System.out.println("얼굴이 검출되지 않았습니다.");
        }

        // 이미지 크기 조정
        int desiredWidth = 400;
        int desiredHeight = 400;
        Mat resizedImage = new Mat();
        Size newSize = new Size(desiredWidth, desiredHeight);
        Imgproc.resize(loadedImage, resizedImage, newSize);

        // 조정된 이미지 저장
        saveImage(resizedImage, targetPath);

        // 이미지 로드

        Mat image = Imgcodecs.imread(targetPath);

        OpenCVUtils openCVUtils = new OpenCVUtils();

        BufferedImage bufferedImage = openCVUtils.matToBufferedImage(image);

        // 이미지를 표시할 Swing 창 생성 및 설정
        GUIController guiController = new GUIController();
        guiController.createGUI(bufferedImage);
    }

    // Mat 객체에 이미지를 저장하는 메서드
    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }

    // Mat 객체를 BufferedImage로 변환하는 메서드


    // Mat 객체에 이미지를 로드하는 메서드
    public static Mat loadImage(String imagePath) {
        Imgcodecs imgCodecs = new Imgcodecs();
        return imgCodecs.imread(imagePath);
    }
}