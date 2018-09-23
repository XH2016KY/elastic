package com.elastic.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/*
 * swagger配置信息
 */
@Component
@Getter@Setter
public class SwaggerInfo {

    private String groupName = "controller";

    private String basePackage;

    private String antPath;

    private String title = "HTTP API";

    private String description = "管理接口";

    private String license = "Apache License Version 2.0";


}
