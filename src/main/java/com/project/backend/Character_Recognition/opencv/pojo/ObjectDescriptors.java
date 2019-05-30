package com.project.backend.Character_Recognition.opencv.pojo;

import org.opencv.core.MatOfKeyPoint;

public class ObjectDescriptors {
private MatOfKeyPoint objectDesciptors;
private String inputText;

public String getInputText() {
	return inputText;
}

public void setInputText(String inputText) {
	this.inputText = inputText;
}

public MatOfKeyPoint getObjectDesciptors() {
	return objectDesciptors;
}

public void setObjectDesciptors(MatOfKeyPoint objectDesciptors) {
	this.objectDesciptors = objectDesciptors;
}

}
