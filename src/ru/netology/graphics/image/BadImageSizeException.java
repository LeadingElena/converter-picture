package ru.netology.graphics.image;

public class BadImageSizeException extends Exception {
    public BadImageSizeException(double maxRatio, double ratio) {
        super("Максимальное соотношение сторон изображения " + maxRatio + ", а у этой " + ratio);
    }
}
