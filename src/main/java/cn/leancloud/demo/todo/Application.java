package cn.leancloud.demo.todo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.internal.impl.JavaRequestSignImplementation;

import cn.leancloud.LeanEngine;

@ServletComponentScan(basePackages = {"cn.leancloud"})
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

  private static final Logger logger = LogManager.getLogger(Application.class);

  private static String appId = System.getenv("LEANCLOUD_APP_ID");
  private static String appKey = System.getenv("LEANCLOUD_APP_KEY");
  private static String appMasterKey = System.getenv("LEANCLOUD_APP_MASTER_KEY");

  public static void main(String[] args) throws Exception {
    logger.info("LeanEngine app init.");
    // 注册子类化
    AVObject.registerSubclass(Todo.class);
    // 初始化AVOSCloud，请保证在整个项目中间只初始化一次
    LeanEngine.initialize(appId, appKey, appMasterKey);
    // 在请求签名中使用masterKey以激活云代码的最高权限
    JavaRequestSignImplementation.instance().setUseMasterKey(true);
    // 打开 debug 日志
    // AVOSCloud.setDebugLogEnabled(true);
    // 向云引擎注册云函数
    LeanEngine.register(Cloud.class);
    if (System.getenv("LEANCLOUD_APP_ENV").equals("development")) {
      // 如果是开发环境，则设置 AVCloud.callFunction 和 AVCloud.rpcFunction 调用本地云函数实现
      // 如果需要本地开发时调用云端云函数实现，则注释掉下面语句。
      LeanEngine.setLocalEngineCallEnabled(true);
    }
    SpringApplication.run(Application.class, args);
  }

}
