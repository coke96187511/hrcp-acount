//package hrcp.gateway.run;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@EnableEurekaClient
//@SpringBootApplication
//@RestController
//public class ServiceRun {
//	public static void main(String[] args) {
//        SpringApplication.run(ServiceRun.class, args);
//    }
//
//	@Value("${server.port}")
//    private String port;
//	
//    @RequestMapping("/template")
//    public String home(@RequestParam String name) {
//        return "服务模板 "+ name + "正在运行,服务端口:" + ":" + port;
//    }
//}
