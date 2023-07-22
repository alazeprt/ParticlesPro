package com.alazeprt;

import java.util.ArrayList;
import java.util.List;

public class Summon {
    private final double x;
    private final double y;
    private final double z;
    private final String world;
    public one one;
    public two two;
    public Summon(double x, double y, double z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        one = new one();
        two = new two();
    }

    public Summon(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.world = location.getWorld();
        one = new one();
        two = new two();
    }

    public class one {
        private double x;
        private final double y;
        private double z;
        one() {
            this.x = Summon.this.x;
            this.y = Summon.this.y;
            this.z = Summon.this.z;
        }
        List<Location> vertical(int length, int direction, int angle) {

            List<Location> locations = new ArrayList<>();

            double dx = Math.cos(Math.toRadians(direction));
            double dz = Math.sin(Math.toRadians(direction));

            double rad;
            if (angle > 0) {
                rad = Math.toRadians(angle);
            } else {
                rad = Math.toRadians(-angle);
            }
            double dy = Math.tan(rad);

            for (int i = 0; i < length; i++) {

                double px = x + (dx * i);
                double py = y + (dy * i);
                double pz = z + (dz * i);

                Location loc = new Location(world, px, py, pz);
                locations.add(loc);
            }

            return locations;

        }

        List<Location> wavy(int length, int direction, int angle, int height, int group) {

            List<Location> locations = new ArrayList<>();

            // 水平向量
            double dx = Math.cos(Math.toRadians(direction));
            double dz = Math.sin(Math.toRadians(direction));

            // 垂直向量
            double dy = Math.tan(Math.toRadians(angle));

            // 波长
            double wavelength = group * 2;

            double waveHeight = 0;

            for (double i = 0; i <= length; i+=0.2) {

                double cx = x + i * dx;
                double cz = z + i * dz;

                // 计算波浪高度
                waveHeight = Math.sin(2 * Math.PI / wavelength * i) * height;

                // 垂直偏移量
                double offsetY = i * dy;

                // 添加点
                locations.add(new Location(world, cx, y + waveHeight + offsetY, cz));

            }

            return locations;

        }

        List<Location> cross(int length, Direction direction, int height, int group) {
            List<Location> locations = new ArrayList<>();
            if(direction.equals(Direction.EAST)) {
                int groups = length / group;
                double x_change = (double) groups / 2;

                for(int i = 0; i < groups; i++) {

                    // 生成上升曲线
                    double lastX = x + i * x_change;
                    for(double j = 0; j <= height; j+=0.2) {
                        double dx = lastX + j * (x_change / height);
                        double dy = y + j;

                        // 添加点到路径
                        locations.add(new Location(world, dx, dy, z));
                    }

                    // 生成下降曲线
                    for(double j = 0; j <= height; j+=0.2) {
                        double dx = lastX + j * (x_change / height);
                        double dy = y + height - j;

                        // 添加点到路径
                        // 这里x坐标累加上升时候的变化量,实现平滑过渡
                        dx += lastX - x - i * x_change;
                        locations.add(new Location(world, dx, dy, z));
                    }

                }
            } else if(direction.equals(Direction.WEST)) {
                x -= (double) length /2;
                int groups = length / group;
                double x_change = (double) groups / 2;

                for(int i = 0; i < groups; i++) {

                    // 生成上升曲线
                    double lastX = x + i * x_change;
                    for(double j = 0; j <= height; j+=0.2) {
                        double dx = lastX + j * (x_change / height);
                        double dy = y + j;

                        // 添加点到路径
                        locations.add(new Location(world, dx, dy, z));
                    }

                    // 生成下降曲线
                    for(double j = 0; j <= height; j+=0.2) {
                        double dx = lastX + j * (x_change / height);
                        double dy = y + height - j;

                        // 添加点到路径
                        // 这里x坐标累加上升时候的变化量,实现平滑过渡
                        dx += lastX - x - i * x_change;
                        locations.add(new Location(world, dx, dy, z));
                    }

                }

                x += (double) length /2;
            } else if(direction.equals(Direction.SOUTH)) {
                int groups = length / group;
                double z_change = (double) groups / 2;

                for(int i = 0; i < groups; i++) {

                    // 生成上升曲线
                    double lastZ = z + i * z_change;
                    for(double j = 0; j <= height; j+=0.2) {
                        double dz = lastZ + j * (z_change / height);
                        double dy = y + j;

                        // 添加点到路径
                        locations.add(new Location(world, x, dy, dz));
                    }

                    // 生成下降曲线
                    for(double j = 0; j <= height; j+=0.2) {
                        double dz = lastZ + j * (z_change / height);
                        double dy = y + height - j;

                        // 添加点到路径
                        // 这里x坐标累加上升时候的变化量,实现平滑过渡
                        dz += lastZ - z - i * z_change;
                        locations.add(new Location(world, x, dy, dz));
                    }

                }
            } else if(direction.equals(Direction.NORTH)) {
                z -= (double) length /2;
                int groups = length / group;
                double z_change = (double) groups / 2;

                for(int i = 0; i < groups; i++) {

                    // 生成上升曲线
                    double lastZ = z + i * z_change;
                    for(double j = 0; j <= height; j+=0.2) {
                        double dz = lastZ + j * (z_change / height);
                        double dy = y + j;

                        // 添加点到路径
                        locations.add(new Location(world, x, dy, dz));
                    }

                    // 生成下降曲线
                    for(double j = 0; j <= height; j+=0.2) {
                        double dz = lastZ + j * (z_change / height);
                        double dy = y + height - j;

                        // 添加点到路径
                        // 这里x坐标累加上升时候的变化量,实现平滑过渡
                        dz += lastZ - z - i * z_change;
                        locations.add(new Location(world, x, dy, dz));
                    }

                }

                z += (double) length /2;
            }
            return locations;
        }

        List<Location> curve(int length, Direction direction, int height, int group) {
            List<Location> locations = new ArrayList<>();
            if (direction.equals(Direction.EAST)) {
                int groups = length / group;
                double x_change = (double) groups / 2;

                for (int i = 0; i < groups; i++) {

                    double lastX = i * groups;

                    // 上升段
                    for (double j = 0; j <= height; j += 0.2) {
                        double dx = x + lastX + j * (x_change / height);
                        double dy = y + j;
                        Location loc = new Location(world, dx, dy, z);
                        locations.add(loc);
                    }

                    // 下降段
                    for (double j = height; j >= 0; j -= 0.2) {
                        double dx = x + lastX + j * (x_change / height) + x_change;
                        double dy = y + height - j;
                        Location loc = new Location(world, dx, dy, z);
                        locations.add(loc);
                    }
                }
            } else if (direction.equals(Direction.WEST)) {
                int groups = length / group;
                double x_change = (double) groups / 2;

                for (int i = 0; i < groups; i++) {

                    double lastX = i * groups;

                    // 上升段
                    for (double j = 0; j <= height; j += 0.2) {
                        double dx = x - lastX - j * (x_change / height);
                        double dy = y + j;
                        Location loc = new Location(world, dx, dy, z);
                        locations.add(loc);
                    }

                    // 下降段
                    for (double j = height; j >= 0; j -= 0.2) {
                        double dx = x - lastX - j * (x_change / height) - x_change;
                        double dy = y + height - j;
                        Location loc = new Location(world, dx, dy, z);
                        locations.add(loc);
                    }
                }
            } else if (direction.equals(Direction.SOUTH)) {
                int groups = length / group;
                double z_change = (double) groups / 2;

                for (int i = 0; i < groups; i++) {

                    double lastZ = i * groups;

                    // 上升段
                    for (double j = 0; j <= height; j += 0.2) {
                        double dz = z + lastZ + j * (z_change / height);
                        double dy = y + j;
                        Location loc = new Location(world, x, dy, dz);
                        locations.add(loc);
                    }

                    // 下降段
                    for (double j = height; j >= 0; j -= 0.2) {
                        double dz = z + lastZ + j * (z_change / height) + z_change;
                        double dy = y + height - j;
                        Location loc = new Location(world, x, dy, dz);
                        locations.add(loc);
                    }
                }
            } else if (direction.equals(Direction.NORTH)) {
                int groups = length / group;
                double z_change = (double) groups / 2;

                for (int i = 0; i < groups; i++) {

                    double lastZ = i * groups;

                    // 上升段
                    for (double j = 0; j <= height; j += 0.2) {
                        double dz = z - lastZ - j * (z_change / height);
                        double dy = y + j;
                        Location loc = new Location(world, x, dy, dz);
                        locations.add(loc);
                    }

                    // 下降段
                    for (double j = height; j >= 0; j -= 0.2) {
                        double dz = z - lastZ - j * (z_change / height) - z_change;
                        double dy = y + height - j;
                        Location loc = new Location(world, x, dy, dz);
                        locations.add(loc);
                    }
                }
            }
            return locations;
        }

    }

    public class two {
        private final double x;
        private final double y;
        private final double z;

        two() {
            this.x = Summon.this.x;
            this.y = Summon.this.y;
            this.z = Summon.this.z;
        }

        List<Location> circle(int r){

            List<Location> locations = new ArrayList<>();

            for(double a = 0; a < 360; a+=0.5){
                double angle = Math.toRadians(a);
                double x2 = x + r * Math.cos(angle);
                double z2 = z + r * Math.sin(angle);

                Location loc = new Location(world, x2, y, z2);
                locations.add(loc);
            }

            return locations;

        }

        List<Location> square(int r){

            List<Location> locations = new ArrayList<>();

            // 计算正方形的4个顶点
            double x1 = x - r;
            double x2 = x + r;
            double z1 = z - r;
            double z2 = z + r;

            // 生成4条边
            for(double i=x1; i<=x2; i+=0.1){
                Location loc1 = new Location(world, i, y, z1);
                Location loc2 = new Location(world, i, y, z2);
                locations.add(loc1);
                locations.add(loc2);
            }

            for(double j=z1; j<=z2; j+=0.1){
                Location loc1 = new Location(world, x1, y, j);
                Location loc2 = new Location(world, x2, y, j);
                locations.add(loc1);
                locations.add(loc2);
            }

            return locations;

        }

        List<Location> triangle(int r){

            List<Location> locations = new ArrayList<>();

            // 计算等边三角形的三个顶点
            double x1 = x;
            double z1 = z - r;

            double x2 = x - (Math.sqrt(3)/2) * r;
            double z2 = z + 0.5 * r;

            double x3 = x + (Math.sqrt(3)/2) * r;
            double z3 = z + 0.5 * r;

            // 生成第一条边
            for(double i=x1; i>=x2; i-=0.2){
                double j = (i - x1) / (x2 - x1) * (z2 - z1) + z1;
                locations.add(new Location(world, i, y, j));
            }

            // 生成第二条边
            for(double i=x2; i<=x3; i+=0.2){
                double j = (i - x2) / (x3 - x2) * (z3 - z2) + z2;
                locations.add(new Location(world, i, y, j));
            }

            // 生成第三条边
            for(double j=z1; j<=z3; j+=0.2){
                double i = (j - z1) / (z3 - z1) * (x3 - x1) + x1;
                locations.add(new Location(world, i, y, j));
            }

            return locations;

        }
    }

    List<Location> circle_xz(int length, int direction, int angle, int height, int group) {

        List<Location> locations = new ArrayList<>();

        // 水平和垂直增量
        double dx = Math.cos(Math.toRadians(direction));
        double dz = Math.sin(Math.toRadians(direction));
        double dy = Math.tan(Math.toRadians(angle));

        // 波数
        int n = length / group;

        // 幅度系数
        double amp = (double) height / 2;

        for (int i = 0; i < length; i++) {

            // xz平面波动
            double offsetXZ = amp * Math.sin(i * 2 * Math.PI / n);

            // y轴波动
            double offsetY = amp * Math.cos(i * 2 * Math.PI / n);

            double px = x + (dx * i) + offsetXZ;
            double py = y + offsetY + (dy * i);
            double pz = z + (dz * i) + offsetXZ;

            Location loc = new Location(world, px, py, pz);
            locations.add(loc);

        }

        return locations;

    }
}
