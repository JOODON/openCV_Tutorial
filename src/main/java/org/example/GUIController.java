package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUIController {
    private JFrame frame;
    private JPanel panel;

    public void createGUI(BufferedImage bufferedImage) {
        createFrame();
        createPanel(bufferedImage);
        displayGUI(bufferedImage);
    }

    private void createFrame() {
        frame = new JFrame("Image Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createPanel(BufferedImage bufferedImage) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawImage(g, bufferedImage);
            }
        };
        frame.add(panel);
    }

    private void drawImage(Graphics g, BufferedImage bufferedImage) {
        g.drawImage(bufferedImage, 0, 0, null);
    }

    private void displayGUI(BufferedImage bufferedImage) {
        int frameWidth = bufferedImage.getWidth();
        int frameHeight = bufferedImage.getHeight();
        frame.setSize(frameWidth,frameHeight);
        //자동으로 이미지 크기 조절
        frame.setVisible(true);
    }
}
