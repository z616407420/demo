package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    
	//head参数
	private ParameterBuilder headerParm = null;
	private List<Parameter> headerParms = null;
	//Response
	private List<ResponseMessage> createCommonResponse() {
		List<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder().code(400).message("（错误请求）服务器不理解请求的语法。").responseModel(new ModelRef("ApiError")).build());
		responseMessages.add(new ResponseMessageBuilder().code(401).message("（未授权）请求要求身份验证。对于需要token的接口，服务器可能返回此响应。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(403).message("（禁止）服务器拒绝请求。").responseModel(new ModelRef("ApiError")).build());
		responseMessages.add(new ResponseMessageBuilder().code(404).message("（未找到）服务器找不到请求的接口。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(408).message("（请求超时）服务器等候请求时发生超时。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(413).message("（请求体过大）请求体超过了5kb，拆成更小的请求体重试即可。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(415).message("（请求体的类型不支持。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(429).message("（服务不可用）请求接口超过调用频率限制，即接口被限流。").responseModel(new ModelRef("ApiError")).build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("（服务器内部错误）服务器遇到错误，无法完成请求。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(501).message("（尚未实施）服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(502).message("（错误网关）服务器作为网关或代理，从上游服务器收到无效响应。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(503).message("（服务不可用）请求接口超过调用频率限制，即接口被限流。").responseModel(new ModelRef("ApiError")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(504).message("（网关超时）服务器作为网关或代理，但是没有及时从上游服务器收到请求。").responseModel(new ModelRef("ApiError")).build());
		return responseMessages;
	}
	
	private List<Parameter> createCommonHeaderParameter() {
		headerParms = new ArrayList<Parameter>();
		headerParm = new ParameterBuilder();
    	headerParm.name("appid").description("应用ID").modelRef(new ModelRef("string")).parameterType("header").required(false);
    	headerParms.add(headerParm.build());
    	headerParm = new ParameterBuilder();
    	headerParm.name("time").description("时间戳，指UTC时间1970年01月01日00时00分00秒起至现在的总毫秒数").modelRef(new ModelRef("string")).parameterType("header").required(false);
    	headerParms.add(headerParm.build());
    	headerParm = new ParameterBuilder();
    	headerParm.name("sign").description("接口签名数据，使用约定签名算法").modelRef(new ModelRef("string")).parameterType("header").required(false);
    	headerParms.add(headerParm.build());
    	return headerParms;
	}
	
	private List<Parameter> createFullHeaderParameter() {
		headerParms = this.createCommonHeaderParameter();
		headerParm = new ParameterBuilder();
    	headerParm.name("auid").description("用户唯一识别码").modelRef(new ModelRef("string")).parameterType("header").required(true);
    	headerParms.add(headerParm.build());
    	headerParm = new ParameterBuilder();
    	headerParm.name("x-token").description("用户访问令牌").modelRef(new ModelRef("string")).parameterType("header").required(true);
    	headerParms.add(headerParm.build());
    	return headerParms;
	}

	@Bean
	public Docket createRestBaseApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(" base")
				.useDefaultResponseMessages(false)
				.apiInfo(baseApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(this.createFullHeaderParameter())
				.globalResponseMessage(RequestMethod.GET, this.createCommonResponse())
				.globalResponseMessage(RequestMethod.POST, this.createCommonResponse())
				.globalResponseMessage(RequestMethod.PUT, this.createCommonResponse())
				.globalResponseMessage(RequestMethod.DELETE, this.createCommonResponse())
				.globalResponseMessage(RequestMethod.GET, this.createCommonResponse());
	}

	private ApiInfo baseApiInfo() {
		return new ApiInfoBuilder()
				.title("redis RESTful APIs")
				.description("redis开放api接口文档")
				.version("1.0")
				.build();
	}







}
