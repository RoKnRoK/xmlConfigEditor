package com.rok.xml.retriever;

import com.rok.xml.api.FileRetriever;

import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
public class FSFileRetriever implements FileRetriever {

    File configFile;


    @Override
    public File retrieveFile(@NotNull String fileName) {
//        Preconditions.checkNotNull(fileName, "Имя файла для открытия не может быть пустым!");
        if (fileName == null) {
            throw new IllegalArgumentException("Configuration file name cannot be null");
        }
        configFile = new File(fileName);
        return configFile;
    }

}
