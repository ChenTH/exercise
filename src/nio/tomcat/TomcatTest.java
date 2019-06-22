package nio.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class TomcatTest {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(99);
        tomcat.setConnector(connector);
        tomcat.start();
        tomcat.getServer().await();
    }
}
