package com.github.greenyears.core.geo.definition;

import com.github.greenyears.core.utils.GeoUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * zmq point.
 * <p/>
 * ZPoint
 *
 * @author zhoumeiqin
 * @date 2021/1/4
 */
@Data
@Accessors(chain = true)
public class ZPoint implements Serializable {
    private static final long serialVersionUID = 4421322082281377494L;
    /**
     * 对应经度(longitude).
     */
    private double x;
    /**
     * 对应纬度(latitude).
     */
    private double y;

    /**
     * 格式化.
     *
     * @return 122.21, 21.111
     */
    public String format() {
        return x + "," + y;
    }

    /**
     * 获取直线距离.
     *
     * @param point 目标地址的坐标
     * @return 直接距离
     */
    public double getDistance(ZPoint point) {
        return GeoUtil.getDistance(this.y, this.x, point.y, point.x);
    }


}
