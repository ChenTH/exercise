package spring.aop.spring.xml;

public class SubjectImpl implements Subject {

    public void login() {

        System.err.println("借书中...");

    }

    public void download() {

        System.err.println("下载中...");

    }
}