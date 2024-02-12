package com.project.backend.Character_Recognition.service;

import com.project.backend.Character_Recognition.dto.LoginDTO;
import com.project.backend.Character_Recognition.dto.RegisterDTO;
import com.project.backend.Character_Recognition.entity.Login;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;

import java.io.IOException;
import java.util.Map;

public interface ProjectService {

     String uploadImage(String fileName,String studentName,String usn) throws IOException, InterruptedException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException;

     Login login(LoginDTO login);

     Map register(RegisterDTO register);

    void createFile() throws IOException;

    void copy() throws IOException;

}
