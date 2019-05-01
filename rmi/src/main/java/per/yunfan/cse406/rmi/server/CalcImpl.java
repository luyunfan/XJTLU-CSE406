package per.yunfan.cse406.rmi.server;

import per.yunfan.cse406.rmi.Calc;
import per.yunfan.cse406.rmi.beans.Group;

/**
 * RMI服务端对于Calc接口的实现类
 */
public class CalcImpl implements Calc {

    /**
     * 测试的add方法，输入两个integer值，返回它们的和
     *
     * @param a 第一个integer参数
     * @param b 第二个integer参数
     * @return 两个integer的和
     */
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 测试的compare方法，输入两个integer值，如果第一个大于第二个，返回正数；如果小于则返回负数；相等返回0
     *
     * @param a 第一个integer参数
     * @param b 第二个integer参数
     * @return 两个integer的比较结果
     */
    @Override
    public int compare(int a, int b) {
        return Integer.compare(a, b);
    }

    /**
     * 测试的equal方法，比较两个Group对象是否相同
     *
     * @param a 第一个Group对象
     * @param b 第二个Group对象
     * @return 两个对象是否相同
     */
    @Override
    public boolean equal(Group a, Group b) {
        if (a == b) {
            return true;
        }
        if (b == null || a.getClass() != b.getClass()) {
            return false;
        }
        return a.getX() == b.getX() &&
                a.getY() == b.getY() &&
                a.getZ() == b.getZ();
    }
}
