package com.zhizhao.jwgl.jiaowuguanli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOssSignature {
     String accessid;
     String policy;
     String signature;
     String dir;
     String host;
     String expire;
}
