## About this project


* spring cloud learning 
* jdk 17+
* spring cloud 2.2.x


由于是jdk17进行编译的，在使用到Eureka的时候会出现问题，需要在启动服务的时候添加JVM 参数 <br>
``--add-opens java.base/java.util=ALL-UNNAMED 
--add-opens java.base/java.lang.reflect=ALL-UNNAMED 
--add-opens java.base/java.text=ALL-UNNAMED 
--add-opens java.desktop/java.awt.font=ALL-UNNAMED``