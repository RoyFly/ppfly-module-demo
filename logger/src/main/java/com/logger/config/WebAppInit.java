package com.logger.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.nio.charset.StandardCharsets;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{BeanFactoryConfig.class};
    }

    /**
     * 相当于web.xml中配置applicationContext-*.xml
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    /**
     * 相当于web.xml中配置${servlet-name}-servlet.xml
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 设置Multipart具体细节（必须）  <br>
     * 指定文件存放的临时路径 <br>
     * 上传文件最大容量  <br>
     * 整个请求的最大容量  <br>
     * 0表示将所有上传的文件写入到磁盘中 <br>
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("/Users/Default/temp", 20971520, 41943040, 0));
    }

    /**
     * 配置其他的 servlet 和 filter
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration.Dynamic httpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        httpMethodFilter.setInitParameter("method", "_method");
        httpMethodFilter.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("multipartFilter", MultipartFilter.class);
        multipartFilter.setInitParameter("multipartResolverBeanName", "multipartResolver");
        multipartFilter.addMappingForUrlPatterns(null, false, "/*");

        super.onStartup(servletContext);
    }
}