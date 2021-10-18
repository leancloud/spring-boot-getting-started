package cn.leancloud.demo.todo;


import java.util.Collections;

import cn.leancloud.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import cn.leancloud.LCCloud;
import cn.leancloud.LCObject;
import cn.leancloud.core.GeneralRequestSignature;

import cn.leancloud.LeanEngine;

@ServletComponentScan(basePackages = {"cn.leancloud"})
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

  private static final Logger logger = LogManager.getLogger(Application.class);

  private static String port = System.getenv("LEANCLOUD_APP_PORT");
  private static String appId = System.getenv("LEANCLOUD_APP_ID");
  private static String appKey = System.getenv("LEANCLOUD_APP_KEY");
  private static String appMasterKey = System.getenv("LEANCLOUD_APP_MASTER_KEY");
  private static String hookKey = System.getenv("LEANCLOUD_APP_HOOK_KEY");
  private static String apiServerUrl = System.getenv("LEANCLOUD_API_SERVER");
  private static String appEnv = System.getenv("LEANCLOUD_APP_ENV");
  private static String haveStaging = System.getenv("LEAN_CLI_HAVE_STAGING");

  public static void main(String[] args) throws Exception {
    logger.info("LeanEngine app init.");
    // Registers subclass.
    LCObject.registerSubclass(Todo.class);

    if ("development".equals(appEnv) && "true".equals(haveStaging) || "stage".equals(appEnv)) {
      LCCloud.setProductionMode(false);
    }
    // Initializes application.
    // Ensure that you only perform one initialization in the whole project.
    if (StringUtil.isEmpty(apiServerUrl)) {
      LeanEngine.initialize(appId, appKey, appMasterKey, hookKey);
    } else {
      LeanEngine.initializeWithServerUrl(appId, appKey, appMasterKey, hookKey, apiServerUrl);
    }
    // Uses masterKey for the whole project.
    GeneralRequestSignature.setMasterKey(appMasterKey);
    // Enables debug logging.
    // AVOSCloud.setDebugLogEnabled(true);
    // Registers cloud functions.
    LeanEngine.register(Cloud.class);

    SpringApplication app = new SpringApplication(Application.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", port));
    app.run(args);
  }

}
