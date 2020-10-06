package com.zeng;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-09-10
 **/

public class GeneratorTemplate {

    public static void main(String[] args) throws Exception {
       generator();
    }

    private static void generator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        //加载配置文件
        InputStream in = GeneratorTemplate.class.getClassLoader().getResourceAsStream("gennerator-config.xml");
        //解析generator配置文件
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(in);
        //生成文件
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);
        in.close();
    }
}
