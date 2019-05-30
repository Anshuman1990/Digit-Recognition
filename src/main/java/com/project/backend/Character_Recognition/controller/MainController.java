package com.project.backend.Character_Recognition.controller;


import com.project.backend.Character_Recognition.entity.Login;
import com.project.backend.Character_Recognition.service.HelloService;
import com.project.backend.Character_Recognition.service.OpencvService;
import com.project.backend.Character_Recognition.service.ProjectService;
import com.project.backend.Character_Recognition.utils.BuildResponse;
import com.project.backend.Character_Recognition.dto.LoginDTO;
import com.project.backend.Character_Recognition.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/characterRecognition")
@EnableAutoConfiguration
@Api(value = "Character Recognition", description = "Get Reports Data for the Character Recognition")
public class MainController {
    @Autowired
    private BuildResponse response;

    @Autowired
    private OpencvService opencvServices;

    @Autowired
    private ProjectService projectService;

    @Autowired
    @Qualifier("helloServicePython")
    private HelloService helloService;

    @ApiOperation(value = "Login", notes = "Login REST Call", produces = "application/json", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity login(@RequestBody LoginDTO loginDTO) throws IOException {
        this.response.clean();
        Map response = new HashMap<>();
        Login login = this.projectService.login(loginDTO);
        boolean status = false;
        if(login != null){
            status = true;
        }
        response.put("status",status);
        return this.response.createResponse(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Register", notes = "Register REST Call", produces = "application/json", httpMethod = "POST")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity register(@RequestBody RegisterDTO registerDTO) throws IOException {
        this.response.clean();
        this.projectService.register(registerDTO);
        Map response = new HashMap<>();
        response.put("status",true);
        return this.response.createResponse(response, HttpStatus.OK);
    }

    @ApiOperation(value = "training", notes = "Register REST Call", produces = "application/json", httpMethod = "POST")
    @RequestMapping(value = "/training", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity training() throws IOException {
        this.response.clean();
        this.opencvServices.training();

        return this.response.createResponse("", HttpStatus.OK);
    }


    @ApiOperation(value = "python", notes = "Python REST Call", produces = "application/json", httpMethod = "GET")
    @RequestMapping(value = "/python", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity python() throws IOException {
        this.response.clean();
        Map response = new HashMap();
        response.put("data",this.helloService.getHello());
        return this.response.createResponse(response, HttpStatus.OK);
    }

}
