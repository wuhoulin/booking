package com.microservice.skeleton.user.domain.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "API响应对象")
public class ApiResponse<T> {

    @ApiModelProperty("状态码：0-成功，非0-失败")
    private Integer code;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("数据")
    private T data;

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(0);
        response.setMessage("操作成功");
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
