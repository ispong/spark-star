package com.isxcode.star.plugin.service;

import com.isxcode.star.common.pojo.dto.StarData;
import org.springframework.stereotype.Service;

@Service
public interface StarService {

    StarData queryData(String database, String tableName);
}
