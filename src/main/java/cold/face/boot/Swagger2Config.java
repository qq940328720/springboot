package cold.face.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 配置注解
@Configuration
// 自动配置Swagger2
@EnableSwagger2
public class Swagger2Config {

    //配置Swagger2的Docket对象，交给Spring容器
    @Bean
    public Docket createRestApi() {
        //配置加载指定包下的注解
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //测试API可设置不同组，ui界面看到api不一样
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                //.genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                //.pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("cold.face.controller"))
//                .paths(PathSelectors.regex("/coldface/.*"))//过滤的接口
                .paths(PathSelectors.regex("/.*"))//过滤的接口
                //.paths(PathSelectors.any())//任何路径
                .build();
    }


    //apiInfo：API接口相关描述
    private ApiInfo apiInfo() {
      /*
       * ApiInfo apiInfo = new ApiInfo("Cat相关接口",//大标题 "Cat相关接口，主要用于测试.",//小标题
       * "1.0",//版本"http://wyait.blog.51cto.com", "wyait",//作者
       * "上海舞泡科技有限公司",//链接显示文字 "http://www.5pao.com/"//网站链接 );
       */
        return new ApiInfoBuilder()
                .title("cat相关API")
                //大标题
                .description("cat相关接口测试")
                //详细描述
                .version("1.0")
                //版本
                .termsOfServiceUrl("NOterms of service")
                .contact("http://wyait.blog.51cto.com")
                //作者
                .license("Version1.0")
                .licenseUrl("http://www.5pao.com")
                .build();

      /*
       * return new ApiInfoBuilder()
       * .title("Spring Boot中使用Swagger2构建RESTfulAPIs")
       * .description("更多Spring Boot相关资料，百度一下")
       *.termsOfServiceUrl("http://wyait.blog.51cto.com")
       * .version("1.0.0").build();
       */
        //return apiInfo;
    }

   /* 升级版：排除其他方法，比如返回页面的。也可以使用注解：@ApiIgnore
@Bean
    public Docket createRestApi2() {
      // 排除不返回json数据的接口（页面等之类的）
       Predicate<RequestHandler> predicate = newPredicate<RequestHandler>() {
            @Override
            public booleanapply(RequestHandler input) {
                Class<?>declaringClass = input.declaringClass();
                if (declaringClass== BasicErrorController.class)// 排除
                    return false;
               if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
                    return true;
               if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
                    return true;
                return false;
            }
        };

        return newDocket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
               .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

    private ApiInfo apiInfos() {
        return new ApiInfoBuilder()
            .title("包含媒体、咨询、搜索引擎关键字、广告等类型接口的服务")//大标题
           .version("1.0")//版本
            .build();
    }
*/
}