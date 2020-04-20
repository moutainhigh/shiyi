package com.baibei.shiyi.generator;

import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator_wenqing {
    /**
     * JDBC配置
     */
//    private static final String JDBC_URL = "jdbc:mysql://192.168.100.134:15529/shiyi_content";
    private static final String JDBC_URL = "jdbc:mysql://192.168.100.134:15529/shiyi_trade";
//    private static final String JDBC_URL = "jdbc:mysql://192.168.100.134:15529/shiyi_order";
    private static final String JDBC_USERNAME = "admin";
    private static final String JDBC_PASSWORD = "admin2019";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    /**
     * 项目路径相关
     */
//    private static final String PROJECT_PATH = "G:/IdeaProjects/shiyi-backend/shiyi-order/shiyi-order-service"; // 项目基础路径
    private static final String PROJECT_PATH = "G:/IdeaProjects/shiyi-backend/shiyi-trade/shiyi-trade-service"; // 项目基础路径
//    private static final String PROJECT_PATH = "G:/IdeaProjects/shiyi-backend/shiyi-content/shiyi-content-service"; // 项目基础路径
    private static final String TEMPLATE_PATH = "G:/IdeaProjects/shiyi-backend/shiyi-code-generator/src/main/resources/template"; // 代码模板路径
//    public static final String BASE_PACKAGE = "com.baibei.shiyi.order";//项目基础包名称，根据自己公司的项目修改
    public static final String BASE_PACKAGE = "com.baibei.shiyi.trade";//项目基础包名称，根据自己公司的项目修改
//    public static final String BASE_PACKAGE = "com.baibei.shiyi.content";//项目基础包名称，根据自己公司的项目修改
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";//Controller所在包
    public static final String MAPPER_INTERFACE_REFERENCE = "com.baibei.shiyi.common.core.mybatis.MyMapper";//Mapper插件基础接口的完全限定名

    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);//生成的Service实现存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);//生成的Controller存放路径

    private static final String AUTHOR = "wenqing";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());//@date
    //java文件路径
    private static final String JAVA_PATH = "/src/main/java";
    //资源文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";

    /**
     * 运行main方法
     *
     * @param args
     */
    public static void main(String[] args) {

//          genCodeByCustomModelName("tbl_tra_product_20191223", "Product");
          genCodeByCustomModelName("tbl_tra_transaction_transfer_log_record", "TransferLogRecord");
//          genCodeByCustomModelName("tbl_tra_transaction_transfer_log", "TransferLog");
//          genCodeByCustomModelName("tbl_tra_transaction_transfer_details", "TransferDetails");
//          genCodeByCustomModelName("tbl_channel_client", "ChannelClient");
//          genCodeByCustomModelName("tbl_game_config", "GameConfig");
//          genCodeByCustomModelName("tbl_afterSale_goods", "AfterSaleGoods");
//          genCodeByCustomModelName("tbl_tra_product_stock", "ProductStock");
//          genCodeByCustomModelName("tbl_cust_dz_fail", "CustDzFail");
//          genCodeByCustomModelName("tbl_bat_fail_result", "BatFailResult");
//          genCodeByCustomModelName("tbl_clean_data", "CleanData");
//          genCodeByCustomModelName("tbl_withdraw_deposit_diff", "WithdrawDepositDiff");
//          genCodeByCustomModelName("tbl_afterSale_order", "AfterSaleOrder");
//          genCodeByCustomModelName("tbl_afterSale_order_details", "AfterSaleOrderDetails");
//          genCodeByCustomModelName("tbl_feedback", "FeedBack");
//          genCodeByCustomModelName("tbl_configuration", "Configuration");
//        genCodeByCustomModelName("tbl_url_management", "UrlManagement");
//        genCodeByCustomModelName("tbl_home_area", "HomeArea");
//        genCodeByCustomModelName("tbl_home_template", "HomeTemplate");
//        genCodeByCustomModelName("tbl_home_logo", "HomeLogo");
//        genCodeByCustomModelName("tbl_home_banner", "HomeBanner");
//        genCodeByCustomModelName("tbl_home_banner_details", "HomeBannerDetails");
//        genCodeByCustomModelName("tbl_home_navigation", "HomeNavigation");
//        genCodeByCustomModelName("tbl_home_navigation_details", "HomeNavigationDetails");
//        genCodeByCustomModelName("tbl_home_notice", "HomeNotice");
//        genCodeByCustomModelName("tbl_home_notice_detail", "HomeNoticeDetails");
//        genCodeByCustomModelName("tbl_home_activity", "HomeActivity");
//        genCodeByCustomModelName("tbl_home_activity_details", "HomeActivityDetails");
//        genCodeByCustomModelName("tbl_home_product", "HomeProduct");
//        genCodeByCustomModelName("tbl_home_product_details", "HomeProductDetails");
//        genCodeByCustomModelName("tbl_pro_plate", "Plate");
//        genCodeByCustomModelName("tbl_pro_product_market", "ProductMarket");
//        genCodeByCustomModelName("tbl_pro_supplier", "Supplier");
//        genCodeByCustomModelName("tbl_pro_category", "Category");
//        genCodeByCustomModelName("tbl_pro_property_name", "PropertyName");
//        genCodeByCustomModelName("tbl_pro_property_value", "PropertyValue");
//        genCodeByCustomModelName("tbl_pro_product", "Product");
//        genCodeByCustomModelName("tbl_pro_product_content", "ProductContent");
//        genCodeByCustomModelName("tbl_pro_product_img", "ProductImg");
//        genCodeByCustomModelName("tbl_pro_trade_product", "TradeProduct");
//        genCodeByCustomModelName("tbl_pro_shop_product", "ShopProduct");
//        genCodeByCustomModelName("tbl_pro_product_stock_log", "ProductStockLog");
//        genCodeByCustomModelName("tbl_pro_product_stock", "ProductStock");
//        genCodeByCustomModelName("tbl_pro_product_market_log", "ProductMarketLog");
//        genCodeByCustomModelName("tbl_pro_product_plate_ref", "ProductPlateRef");
//        genCodeByCustomModelName("tbl_pro_product_plan", "ProductPlan");
//        genCodeByCustomModelName("tbl_pro_product_plan_details", "ProductPlanDetails");
    }


    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
    }

    /**
     * 生成model&&mapper
     *
     * @param tableName
     * @param modelName
     */
    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtil.isNotEmpty(modelName)) tableConfiguration.setDomainObjectName(modelName);
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }
        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) modelName = tableNameConvertUpperCamel(tableName);
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    /**
     * 生成service
     *
     * @param tableName
     * @param modelName
     */
    public static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + "I" + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}