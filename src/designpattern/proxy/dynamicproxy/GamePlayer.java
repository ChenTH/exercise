package designpattern.proxy.dynamicproxy;

public class GamePlayer implements IGamePlayer {
    @Override
    public void login(String user, String password) {
        System.out.println("登陆了，用户：" + user + "，密码：" + password);
    }

    @Override
    public void killBoss() {
        System.out.println("杀死了boss");
    }

    @Override
    public void upgrade() {
        System.out.println("升级了");
    }
}
