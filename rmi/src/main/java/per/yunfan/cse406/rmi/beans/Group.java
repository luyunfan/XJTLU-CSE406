package per.yunfan.cse406.rmi.beans;

import java.io.Serializable;

/**
 * RMI测试的一个Java bean类，因为RMI需要远程调用，因此这个类必须实现Serializable接口
 */
public class Group implements Serializable {

    /**
     * Bean封装的第一个变量
     */
    private final int x;

    /**
     * Bean封装的第二个变量
     */
    private final int y;

    /**
     * Bean封装的第三个变量
     */
    private final int z;

    /**
     * Java bean类的构造方法
     *
     * @param x 封装的第一个变量
     * @param y 封装的第二个变量
     * @param z 封装的第三个变量
     */
    public Group(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return Bean封装的第一个变量
     */
    public int getX() {
        return x;
    }

    /**
     * @return Bean封装的第二个变量
     */
    public int getY() {
        return y;
    }

    /**
     * @return Bean封装的第三个变量
     */
    public int getZ() {
        return z;
    }
}
