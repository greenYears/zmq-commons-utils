package com.github.greenyears.core.utils;

/**
 * 坐标计算.
 * <p/>
 * GeoUtil
 *
 * @author zhoumeiqin
 * @date 2021/1/4
 */
public class GeoUtil {
    /**
     * 单位千米.
     */
    private static final double EARTH_RADIUS = 6378.137;


    /**
     * 角度弧度计算公式 rad:(). <br/>
     * <p>
     * 360度=2π π=Math.PI
     * <p>
     * x度 = x*π/360 弧度
     *
     * @param degree
     * @return
     * @author chiwei
     * @since JDK 1.6
     */
    private static double getRadian(double degree) {
        return degree * Math.PI / 180.0;
    }

    /**
     * 依据经纬度计算两点之间的距离 GetDistance:(). <br/>
     *
     * @param lat1 1点的纬度
     * @param lng1 1点的经度
     * @param lat2 2点的纬度
     * @param lng2 2点的经度
     * @return 距离 单位 米
     * @author chiwei
     * @since JDK 1.6
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = getRadian(lat1);
        double radLat2 = getRadian(lat2);
        // 两点纬度差
        double a = radLat1 - radLat2;
        // 两点的经度差
        double b = getRadian(lng1) - getRadian(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s * 1000;
    }
}
