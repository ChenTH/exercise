package spring.aop.spring.annotate;

import org.springframework.stereotype.Component;

@Component(value = "subject")
public class SubjectImpl implements Subject {

    public void login() {

        System.err.println("借书中...");

    }

    public void download() {

        System.err.println("下载中...");

    }
}