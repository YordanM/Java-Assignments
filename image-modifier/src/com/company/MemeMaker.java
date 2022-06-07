package com.company;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

public class MemeMaker {

    static void fibFill(int[] fibo) {

        fibo[0] = 1;

        if (fibo.length != 1){
            fibo[1] = 1;
        }

        for (int i = 2; i < fibo.length; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
    }

    public BufferedImage MakeMeme(BufferedImage image)
    {
        //Min value for fibonacci
        int min = 1;
        //Max value for fibonacci
        int max = 7;

        //X and Y positions for the images
        int positionX;
        int positionY;

        //text for adding to image
        String text;

        //Generate random int value from 1 to 7
        int randomInt = (int)Math.floor(Math.random()*(max-min+1)+min);

        int[] fib = new int[randomInt];

        //fill the array with fibonacci numbers
        fibFill(fib);

        //get the Graphics object
        Graphics g = image.getGraphics();

        //set font
        g.setFont(g.getFont().deriveFont(25f));


        Font font = new Font("Arial", Font.BOLD, 18);

        text = "ФибоНачи";

        //set the position for the text
        FontMetrics metrics = g.getFontMetrics(font);
        //center
        positionX = (image.getWidth() - metrics.stringWidth(text)) / 2;
        //top
        positionY = metrics.getAscent();

        //adding text
        g.drawString(text, positionX, positionY);
        g.dispose();

        //get the count of fib numbers
        int imagesCount = fib.length;

        //creating array with all images and adding the default image
        BufferedImage images[] = new BufferedImage[imagesCount + 1];
        images[0] = image;

        //creating images for fib numbers and adding text to them
        for(int j = 1; j < images.length; j++) {
            images[j] = new BufferedImage(image.getWidth(), 30, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = images[j].createGraphics();

            g = images[j].getGraphics();
            metrics = g.getFontMetrics(font);

            if (fib[j - 1] == 1) {
                text = "Като зема " + fib[j - 1] + " дърво...";
            }
            else {
                text = "Като зема " + fib[j - 1] + " дървета...";
            }

            positionX = 10;
            positionY = metrics.getAscent();

            g2d.drawString(text,positionX, positionY);

            g2d.dispose();
        }

        //get the total height for the result image
        int heightTotal = 0;
        for(int j = 0; j < images.length; j++) {
            heightTotal += images[j].getHeight();
        }

        //appending all images
        int heightCurr = 0;
        BufferedImage concatImage = new BufferedImage(image.getWidth(), heightTotal, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for(int j = 0; j < images.length; j++) {
            g2d.drawImage(images[j], 0, heightCurr, null);
            heightCurr += images[j].getHeight();
        }
        g2d.dispose();

        return  concatImage;
    }

}
