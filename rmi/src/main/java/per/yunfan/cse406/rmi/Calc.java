package per.yunfan.cse406.rmi;

import per.yunfan.cse406.rmi.beans.Group;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI的计算器接口定义部分，RMI需要远程调用的接口都必须继承Remote接口
 */
public interface Calc extends Remote {

    /**
     * 测试的add方法，输入两个integer值，返回它们的和
     *
     * @param a 第一个integer参数
     * @param b 第二个integer参数
     * @return 两个integer的和
     * @throws RemoteException 可能出现的远程调用异常，每一个方法都必须声明抛出这个异常
     */
    int add(int a, int b) throws RemoteException;

    /**
     * 测试的compare方法，输入两个integer值，如果第一个大于第二个，返回正数；如果小于则返回负数；相等返回0
     *
     * @param a 第一个integer参数
     * @param b 第二个integer参数
     * @return 两个integer的比较结果
     * @throws RemoteException 可能出现的远程调用异常，每一个方法都必须声明抛出这个异常
     */
    int compare(int a, int b) throws RemoteException;

    /**
     * 测试的equal方法，比较两个Group对象是否相同
     *
     * @param a 第一个Group对象
     * @param b 第二个Group对象
     * @return 两个对象是否相同
     * @throws RemoteException 可能出现的远程调用异常，每一个方法都必须声明抛出这个异常
     */
    boolean equal(Group a, Group b) throws RemoteException;
}
