package com.isxcode.star.plugin.service;

import org.springframework.stereotype.Service;

@Service
public class SqlParseService {

    public boolean hasLimit(String sql) {

        return sql.contains("limit");
    }
}
