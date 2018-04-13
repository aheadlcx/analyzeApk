package cn.v6.sixrooms.utils;

import java.util.Random;

public class RandomUtils {
    public static int getLimitRandom(int i, int i2) {
        return (new Random().nextInt(i2) % ((i2 - i) + 1)) + i;
    }
}
