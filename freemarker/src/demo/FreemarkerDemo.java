package demo;

import java.io.*;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerDemo {
    private static final String TEMPLATE_PATH = "src/templates";
    private static final String CLASS_PATH    = "src/demo";

    public static void main(String[] args) throws IOException, TemplateException {
        test2();
    }

    public static void test1() {

        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer        out           = null;

        try {

            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();

            dataMap.put("packageName", "demo");
            dataMap.put("className", "AutoCodeDemo");
            dataMap.put("text", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");

            // step4 加载模版文件
            Template template = configuration.getTemplate("hello.ftl");

            // step5 生成数据
            File docFile = new File(CLASS_PATH + "/AutoCodeDemo.java");

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void test2() throws IOException, TemplateException {
        Configuration configuration = new Configuration();

        configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

        Template template  = configuration.getTemplate("hello.ftl");
        Map      dataModel = new HashMap();

        dataModel.put("packageName", "demo");
        dataModel.put("className", "HelloDemo");
        dataModel.put("text", "随便输出一句话");

        File   destFile = new File(CLASS_PATH + "/HelloDemo.java");
        Writer writer   = new FileWriter(destFile);

        template.process(dataModel, writer);
        System.out.println("success...");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
