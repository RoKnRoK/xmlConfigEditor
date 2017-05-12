package com.rok.xml.retriever;

import com.rok.xml.api.ConfigRetriever;

import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
public class FSConfigRetriever implements ConfigRetriever {


    @Override
    public File retrieveConfig(@NotNull String configPath) {
        if (configPath == null) {
            throw new IllegalArgumentException("Configuration file path cannot be null");
        }
        return new File(configPath);
    }

}
