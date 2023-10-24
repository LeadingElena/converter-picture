package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImg implements TextGraphicsConverter {

    public double maxRatio;
    public int maxWidth;
    public int maxHeight;
    public int intermediateWidth;
    public int intermediateHeight;
    public int newWidth;
    public int newHeight;
    TextColorSchema schema = new TextColorSchemaImg();
    public int color;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        double ratio = img.getWidth() / img.getHeight();

        if (Double.compare(ratio, maxRatio) == 1) {
            throw new BadImageSizeException(maxRatio, ratio);
        }
        if (maxWidth == 0) {
            newWidth = img.getWidth();
        }
        if (maxHeight == 0) {
            newHeight = img.getHeight();
        }
        if (maxWidth != 0 && img.getWidth() > maxWidth) {
            intermediateWidth = maxWidth;
            intermediateHeight = img.getHeight() / (img.getWidth() / maxWidth);
        }

        if (maxHeight != 0 && intermediateHeight > maxHeight) {
            newHeight = maxHeight;
            newWidth = intermediateWidth / (intermediateHeight / maxHeight);

        } else {
            newWidth = intermediateWidth;
            newHeight = intermediateHeight;
        }

         //Проверка расчетов размеров картинки
        System.out.println(img.getWidth() + " " + img.getHeight()); // первоначальные размеры картинки
        System.out.println(intermediateWidth + " " + intermediateHeight); // размеры картинки после проверки maxWidth
        System.out.println(newWidth + " " + newHeight); // размеры картинки, приведенные к заданным условиями

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();
        StringBuilder imgTextBuilder = new StringBuilder();

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                color = bwRaster.getPixel(w, h, new int[3])[0];
                imgTextBuilder.append(schema.convert(color));
                imgTextBuilder.append(schema.convert(color));
            }
            imgTextBuilder.append("\n"); //перенос строчки
        }

        return imgTextBuilder.toString(); // собираем в текст
    }


    @Override
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
