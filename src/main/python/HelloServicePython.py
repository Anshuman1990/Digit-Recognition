# from Lib import os
from com.project.backend.Character_Recognition.service import HelloService

class HelloServicePython(HelloService):
    def __init__(self):
        self.value="Hello from python"

    def getHello(self):
        # os.system("svm.py 1")
        return self.value