package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        MemeMaker meme = new MemeMaker();

        //read the image
        BufferedImage image = ImageIO.read(new File("fibonacci.png"));

        //get final image
        BufferedImage finalMeme = meme.MakeMeme(image);

        //write the image
        ImageIO.write(finalMeme, "png", new File("fibonacci1.png"));
    }
}
