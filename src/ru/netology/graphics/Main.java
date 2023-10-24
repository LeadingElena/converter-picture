package ru.netology.graphics;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverterImg;
import ru.netology.graphics.server.GServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;


public class Main {
    public static void main (String[] args) throws Exception {
        TextGraphicsConverterImg converter = new TextGraphicsConverterImg();

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
//        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}
