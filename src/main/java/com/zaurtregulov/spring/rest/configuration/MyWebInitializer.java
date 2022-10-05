package com.zaurtregulov.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  //в приложении не будет root config классов, поэтому возвращаем null
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  //возвращает наш конфигурационный класс
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{MyConfig.class};
  }

  //указываем url,связанный с нашим приложением
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}
