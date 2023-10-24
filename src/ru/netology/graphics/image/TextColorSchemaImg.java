package ru.netology.graphics.image;

public class TextColorSchemaImg implements TextColorSchema {
    @Override
    public char convert(int color) {
        if (color < 37){
            return '#';
        } else if (color < 74) {
            return '$';
        } else if (color < 110) {
            return '@';
        } else if (color < 147) {
            return '%';
        } else if (color < 184) {
            return '*';
        } else if (color < 220) {
            return '+';
        } else {
            return '-';
        }
    }


}

