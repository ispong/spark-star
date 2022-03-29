package com.isxcode.star.plugin.service;

import com.isxcode.star.common.pojo.dto.StarData;
import com.isxcode.star.common.response.StarRequest;
import org.springframework.stereotype.Service;

@Service
public interface StarService {

    StarData queryData(StarRequest starRequest);
}
