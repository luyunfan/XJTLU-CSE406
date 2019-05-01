package per.yunfan.cse406.rmi.beans;

import java.io.Serializable;

/**
 * RMI测试的一个Java bean类，因为RMI需要远程调用，因此这个类必须实现Serializable接口
 */
public class Group implements Serializable {

    private final int x;

    private final int y;

    private final int z;

    public Group(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
