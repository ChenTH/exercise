package spring.aop.spring.xml;


import org.aspectj.lang.ProceedingJoinPoint;

public class PermissionVerification {
    /**
     * 权限校验
     */
    public void canLogin() {

        //做一些登陆校验
        System.err.println("我正在校验啦！！！！");

    }

    /**
     * 校验之后做一些处理（无论是否成功都做处理）
     */
    public void saveMessage() {

        //做一些后置处理
        System.err.println("我正在处理啦！！！！");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        joinPoint.proceed();
        System.out.println("环绕后");
    }
}
