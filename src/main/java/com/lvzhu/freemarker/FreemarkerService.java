package com.lvzhu.freemarker;

import freemarker.template.Configuration;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;


/**
 * @author lvzhu.
 * Time 2019/7/22 4:13 PM
 * Desc  FreeMarker模板
 */
public class FreemarkerService {

    private String location;

    private String suffix;

    private Configuration cfg = new Configuration(Configuration.getVersion());

    private String encoding = "utf-8";

    public FreemarkerService() throws IOException {
        cfg.setDefaultEncoding(encoding);
        cfg.setDirectoryForTemplateLoading(new File("/Users/lvzhu/myproject/lvzhu-mybatis-generator/src/main/resources/template"));
        cfg.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.getVersion()).build());
        cfg.setNumberFormat("#");
        this.location = "";
        this.suffix = "ftl";
    }

    public String merge(String template, Map<String, Object> model) {
        try {
            Template tpl = cfg.getTemplate(template + "." + suffix);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(bos);
            tpl.process(model, writer);
            writer.flush();
            writer.close();
            byte[] byteArray = bos.toByteArray();
            return new String(byteArray, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
