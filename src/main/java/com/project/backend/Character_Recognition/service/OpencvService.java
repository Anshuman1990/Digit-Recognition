package com.project.backend.Character_Recognition.service;

import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface OpencvService {
 Map<String,Object> processDataset(File file) throws IOException, InterruptedException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException;


    void training() throws IOException;
}
