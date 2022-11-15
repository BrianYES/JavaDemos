package demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HTMLBuilder {

    private static final String HTML_DIR = "web/html";
    private static final String TEMPLATE_DIR = "src/templates";

    public static void main(String[] args) throws IOException, TemplateException {
        demo();

    }

    public static void demo1() {
        Map dataModel = new HashMap(16);
        List animals = new ArrayList();

        Map animal1 = new HashMap(16);
        animal1.put("name", "miaomiao");
        animal1.put("price", 150);
        animals.add(animal1);

        Map animal2 = new HashMap(16);
        animal2.put("name", "gougou");
        animal2.put("price", 180);
        animals.add(animal2);

        dataModel.put("animals", animals);

        build("demo1.flt", "demo1.html", dataModel);
    }

    public static void demo() {
        build("demo.ftl", "demo.html", null);
    }

    public static void build(String templateName, String destFileName, Object dataModel) {
        try {
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
            Template template = configuration.getTemplate(templateName);
            Writer out = new FileWriter(new File(HTML_DIR + File.separator + destFileName));
            template.process(dataModel, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
