package com.project.backend.Character_Recognition.controller;


import com.project.backend.Character_Recognition.dto.UploadDTO;
import com.project.backend.Character_Recognition.service.FileStorageService;
import com.project.backend.Character_Recognition.service.HelloService;
import com.project.backend.Character_Recognition.service.ProjectService;
import com.project.backend.Character_Recognition.utils.BuildResponse;
import com.project.backend.Character_Recognition.utils.UploadFileResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/characterRecognition")
@EnableAutoConfiguration
@Api(value = "AI6 File Handling", description = "File Controller for the application")
public class FileController {

    @Autowired
    private BuildResponse response;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    @Qualifier("helloServicePython")
    private HelloService helloService;

    private String fileName = "";

    @ApiOperation(value = "Upload File", notes = "REST api for uploading file", produces = "application/json", httpMethod = "POST")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = fileStorageService.storeFile(file);
        this.fileName = fileName;
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        Map response = new HashMap();

        response.put("status",true);
        return this.response.createResponse(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Student Details", notes = "Login REST Call", produces = "application/json", httpMethod = "POST")
    @RequestMapping(value = "/studentFileUpload", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity login(@RequestBody UploadDTO uploadDTO) throws IOException, InterruptedException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        this.response.clean();
        Map response = new HashMap<>();
        String resp = projectService.uploadImage(this.fileName,uploadDTO.getStudentName(),uploadDTO.getUsn());
        this.helloService.getHello();
        response.put("status",resp);
        return this.response.createResponse(response, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {

        this.projectService.copy();

            this.projectService.createFile();


        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
