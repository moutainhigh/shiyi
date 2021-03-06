package com.baibei.shiyi.content.feign.bean.dto;

import com.baibei.shiyi.common.tool.bean.CustomerBaseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PublicNoticeDto extends CustomerBaseDto {

    private Long id;

    @NotNull(message = "标题不能为空")
    private String title;

    private Integer seq;

    private String link;

    private String content;

    private String hidden;

    private String image;

    /**
     * 类型(mall:商场公告，trade:交易公告)
     */
    private String publicType;
}
