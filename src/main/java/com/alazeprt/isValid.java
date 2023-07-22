package com.alazeprt;

import org.bukkit.Particle;

public class isValid {
    public static boolean isInteger(String... nums) {
        if (nums == null) {
            return false;
        }
        for (String str : nums) {
            // 匹配可选正负号后接一个或多个数字
            if (!str.matches("[-+]?\\d+")) {
                return false;
            }
        }
        return true;
    }

    public static boolean particle(String particle) {
        for(Particle particle1 : Particle.values()) {
            if(particle.toUpperCase().equals(particle1.toString())) {
                return true;
            }
        }
        return false;
    }
}
