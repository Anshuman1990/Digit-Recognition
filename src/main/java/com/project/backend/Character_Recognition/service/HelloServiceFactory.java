package com.project.backend.Character_Recognition.service;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;

public class HelloServiceFactory implements FactoryBean<HelloService> {
    @Override
    public HelloService getObject() throws Exception {
        //Here is the actual code that interprets our python file.
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src\\main\\python\\HelloServicePython.py");
        PyObject buildingObject = interpreter.get("HelloServicePython").__call__();

//Cast the created object to our Java interface
        return (HelloService) buildingObject.__tojava__(HelloService.class);
    }

    @Override
    public Class<?> getObjectType() {
        return HelloService.class;
    }
}
