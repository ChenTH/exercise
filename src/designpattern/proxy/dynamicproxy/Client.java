package designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String args[]) {
        IGamePlayer player = new GamePlayer();
        InvocationHandler handler = new GamePlayIH(player);
        ClassLoader cl = player.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(player.getClass().getClassLoader(), player.getClass().getInterfaces(), handler);
        proxy.login("zhangsan", "password");
        proxy.killBoss();
        proxy.upgrade();
    }
}
