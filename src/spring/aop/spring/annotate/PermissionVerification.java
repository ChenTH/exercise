package spring.aop.spring.annotate;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionVerification {
    /**
     * 权限校验
     */
    @Before("execution(* spring.aop.spring.annotate.Subject.* (..))")
    public void canLogin() {

        //做一些登陆校验
        System.err.println("我正在校验啦！！！！");

    }

    /**
     * 校验之后做一些处理（无论是否成功都做处理）
     */
    @After("execution(* spring.aop.spring.annotate.Subject.* (..))")
    public void saveMessage() {

        //做一些后置处理
        System.err.println("我正在处理啦！！！！");
    }

}
