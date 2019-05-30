package com.project.backend.Character_Recognition.service;

import com.project.backend.Character_Recognition.config.FileStorageProperties;
import com.project.backend.Character_Recognition.dto.LoginDTO;
import com.project.backend.Character_Recognition.dto.RegisterDTO;
import com.project.backend.Character_Recognition.entity.Login;
import com.project.backend.Character_Recognition.entity.Register;
import com.project.backend.Character_Recognition.model.StudentListModel;
import com.project.backend.Character_Recognition.model.StudentModel;
import com.project.backend.Character_Recognition.model.TestSheetModel;
import com.project.backend.Character_Recognition.repository.LoginRepository;
import com.project.backend.Character_Recognition.repository.RegisterRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    private OpencvService _opencvService;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private StudentListModel studentListModel;

    @Autowired
    private FileStorageService fileStorageService;

    @Value("file:src/main/resources/templates/template.xlsx")
    private Resource template;

    @Override
    public String uploadImage(String fileName, String studentName,String usn) throws IOException, InterruptedException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        Path uploadDir = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        System.out.println("uploadDir= " + uploadDir);
        File file = new File(uploadDir + File.separator + fileName);
        Map<String, Object> result = this._opencvService.processDataset(file);


//            result = new HashMap<>(create(fileName));

            List<String> result_1 = (List<String>) result.get("test1");
            List<String> result_2 = (List<String>) result.get("test2");
            List<String> result_3 = (List<String>) result.get("test3");


            TestSheetModel sheet_1 = new TestSheetModel();
            sheet_1.setTestName("Test 1");
            if ( result_1 != null &&!result_1.isEmpty()) {
                if ((result_1.size() > 0 && result_1.get(0) != null)) {
                    sheet_1.setQuestion_1(result_1.get(0));
                } else {
                    sheet_1.setQuestion_1(String.valueOf(0));
                }
                if ((result_1.size() > 1 && result_1.get(1) != null)) {
                    sheet_1.setQuestion_2(result_1.get(1));
                } else {
                    sheet_1.setQuestion_2(String.valueOf(0));
                }
                if ((result_1.size() > 2 && result_1.get(2) != null)) {
                    sheet_1.setQuestion_3(result_1.get(2));
                } else {
                    sheet_1.setQuestion_3(String.valueOf(0));
                }
                if ((result_1.size() > 3 && result_1.get(3) != null)) {
                    sheet_1.setQuestion_4(result_1.get(3));
                } else {
                    sheet_1.setQuestion_4(String.valueOf(0));
                }
                if ((result_1.size() > 4 && result_1.get(4) != null)) {
                    sheet_1.setQuestion_5(result_1.get(4));
                } else {
                    sheet_1.setQuestion_5(String.valueOf(0));
                }
                if ((result_1.size() > 5 && result_1.get(5) != null)) {
                    sheet_1.setQuestion_6(result_1.get(5));
                } else {
                    sheet_1.setQuestion_6(String.valueOf(0));
                }
                if ((result_1.size() > 6 && result_1.get(6) != null)) {
                    sheet_1.setQuestion_7(result_1.get(6));
                } else {
                    sheet_1.setQuestion_7(String.valueOf(0));
                }
                if ((result_1.size() > 7 && result_1.get(7) != null)) {
                    sheet_1.setQuestion_8(result_1.get(7));
                } else {
                    sheet_1.setQuestion_8(String.valueOf(0));
                }
            }
            TestSheetModel sheet_2 = new TestSheetModel();
            sheet_2.setTestName("Test 2");
            if (result_2 != null && !result_2.isEmpty()) {
                if ((result_2.size() > 0 && result_2.get(0) != null)) {
                    sheet_2.setQuestion_1(result_2.get(0));
                } else {
                    sheet_2.setQuestion_1(String.valueOf(0));
                }
                if ((result_2.size() > 1 && result_2.get(1) != null)) {
                    sheet_2.setQuestion_2(result_2.get(1));
                } else {
                    sheet_2.setQuestion_2(String.valueOf(0));
                }
                if ((result_2.size() > 2 && result_2.get(2) != null)) {
                    sheet_2.setQuestion_3(result_2.get(2));
                } else {
                    sheet_2.setQuestion_3(String.valueOf(0));
                }
                if ((result_2.size() > 3 && result_2.get(3) != null)) {
                    sheet_2.setQuestion_4(result_2.get(3));
                } else {
                    sheet_2.setQuestion_4(String.valueOf(0));
                }
                if ((result_2.size() > 4 && result_2.get(4) != null)) {
                    sheet_2.setQuestion_5(result_2.get(4));
                } else {
                    sheet_2.setQuestion_5(String.valueOf(0));
                }
                if ((result_2.size() > 5 && result_2.get(5) != null)) {
                    sheet_2.setQuestion_6(result_2.get(5));
                } else {
                    sheet_2.setQuestion_6(String.valueOf(0));
                }
                if ((result_2.size() > 6 && result_2.get(6) != null)) {
                    sheet_2.setQuestion_7(result_2.get(6));
                } else {
                    sheet_2.setQuestion_7(String.valueOf(0));
                }
                if ((result_2.size() > 7 && result_2.get(7) != null)) {
                    sheet_2.setQuestion_8(result_2.get(7));
                } else {
                    sheet_2.setQuestion_8(String.valueOf(0));
                }
            }

            TestSheetModel sheet_3 = new TestSheetModel();
            sheet_3.setTestName("Test 3");
            if (result_3 != null && !result_3.isEmpty()) {
                if ((result_3.size() > 0 && result_3.get(0) != null)) {
                    sheet_3.setQuestion_1(result_3.get(0));
                } else {
                    sheet_3.setQuestion_1(String.valueOf(0));
                }
                if ((result_3.size() > 1 && result_3.get(1) != null)) {
                    sheet_3.setQuestion_2(result_3.get(1));
                } else {
                    sheet_3.setQuestion_2(String.valueOf(0));
                }
                if ((result_3.size() > 2 && result_3.get(2) != null)) {
                    sheet_3.setQuestion_3(result_3.get(2));
                } else {
                    sheet_3.setQuestion_3(String.valueOf(0));
                }
                if ((result_3.size() > 3 && result_3.get(3) != null)) {
                    sheet_3.setQuestion_4(result_3.get(3));
                } else {
                    sheet_3.setQuestion_4(String.valueOf(0));
                }
                if ((result_3.size() > 4 && result_3.get(4) != null)) {
                    sheet_3.setQuestion_5(result_3.get(4));
                } else {
                    sheet_3.setQuestion_5(String.valueOf(0));
                }
                if ((result_3.size() > 5 && result_3.get(5) != null)) {
                    sheet_3.setQuestion_6(result_3.get(5));
                } else {
                    sheet_3.setQuestion_6(String.valueOf(0));
                }
                if ((result_3.size() > 6 && result_3.get(6) != null)) {
                    sheet_3.setQuestion_7(result_3.get(6));
                } else {
                    sheet_3.setQuestion_7(String.valueOf(0));
                }
                if ((result_3.size() > 7 && result_3.get(7) != null)) {
                    sheet_3.setQuestion_8(result_3.get(7));
                } else {
                    sheet_3.setQuestion_8(String.valueOf(0));
                }
            }

            StudentModel model = new StudentModel();
            model.setStudentName(studentName);
            model.setUsn(usn);
            model.setTest_1(sheet_1);
            model.setTest_2(sheet_2);
            model.setTest_3(sheet_3);
            this.studentListModel.addStudents(model);


        if (!result.isEmpty())
            return "done";
        return null;
    }

    @Override
    public Login login(LoginDTO loginDTO) {
        boolean status = false;
        Login login = loginRepository.fetchByUserDetails(loginDTO.getEmail(), loginDTO.getPassword());
        return login;
    }

    @Override
    public Map register(RegisterDTO register) {
        Map result = new HashMap();
        Register register1 = new Register();
        register1.setEmail(register.getEmail());
        register1.setFirstName(register.getFirstName());
        register1.setLastName(register.getLastName());
        registerRepository.save(register1);

        Login login = new Login();
        login.setEmail(register.getEmail());
        login.setPassword(register.getPassword());
        loginRepository.save(login);
        result.put("response", "done");
        return result;
    }

    @Override
    public void createFile() throws IOException {
        createExcelSheet();
    }

    @Override
    public void copy() throws IOException {
        fileStorageService.storeLocalFile(template.getFile());
    }

    private void createExcelSheet() throws IOException {
        String templatePath = System.getProperty("user.dir")+File.separator+"uploads"+File.separator+"template.xlsx";

        FileInputStream fsIP = new FileInputStream(new File(templatePath));

        // Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fsIP);

        // Create a blank sheet
        XSSFSheet sheet = workbook.getSheet("INTERNALS");


        List<StudentModel> students = this.studentListModel.getStudents();

        int rownum = 0;

        for (int i = 0; i < students.size(); i++) {
            // this creates a new row in the sheet
            Row row = sheet.getRow(rownum+8);
            StudentModel student = students.get(i);
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getUsn());
//            cellUsn.setCellValue(student.getUsn());
            TestSheetModel sheetModel1 = student.getTest_1();
            TestSheetModel sheetModel2 = student.getTest_2();
            TestSheetModel sheetModel3 = student.getTest_3();

           int t1Total =  addDataToCell(4,12,sheetModel1,row);
//           row.createCell(10).setCellValue(t1Total);
            int t2Total = addDataToCell(12,20,sheetModel2,row);
//            row.createCell(19).setCellValue(t2Total);
            int t3Total = addDataToCell(20,28,sheetModel3,row);
//            row.createCell(28).setCellValue(t3Total);
            rownum++;
        }
        try {
            // this Writes the workbook gfgcontribute
            FileOutputStream out = new FileOutputStream(new File(templatePath));
            workbook.write(out);
            out.close();
            System.out.println("Template.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int addDataToCell(int start,int end,TestSheetModel sheetModel,Row row){
        int total = 0;
        for (int j = start; j < end; j++) {
            // this line creates a cell in the next column of that row
            Cell cell = row.createCell(j);
            switch (j) {
                case 4:
                case 12:
                case 20:
                    if (sheetModel.getQuestion_1() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_1());
                        cell.setCellValue(sheetModel.getQuestion_1());
                    } else {
                        cell.setCellValue(0);
                    }
                    break;
                case 5:
                case 13:
                case 21:
                    if (sheetModel.getQuestion_2() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_2());
                        cell.setCellValue(sheetModel.getQuestion_2());
                    } else {
                        cell.setCellValue(0);
                    }
                    break;
                case 6:
                case 14:
                case 22:
                    if (sheetModel.getQuestion_3() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_3());
                        cell.setCellValue(sheetModel.getQuestion_3());
                    } else {
                        cell.setCellValue(0);
                    }
                    break;
                case 7:
                case 15:
                case 23:
                    if (sheetModel.getQuestion_4() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_4());
                        cell.setCellValue(sheetModel.getQuestion_4());
                    } else {
                        cell.setCellValue(0);
                    }
                    break;

                case 8:
                case 16:
                case 24:
                    if (sheetModel.getQuestion_5() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_5());
                        cell.setCellValue(sheetModel.getQuestion_5());
                    } else {
                        cell.setCellValue(0);
                    }
                    break;

                case 9:
                case 17:
                case 25:
                    if (sheetModel.getQuestion_6() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_6());
                        cell.setCellValue(sheetModel.getQuestion_6());
                    } else {
                        cell.setCellValue(0);
                    }

                    break;

                case 10:
                case 18:
                case 26:
                    if (sheetModel.getQuestion_7() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_7());
                        cell.setCellValue(sheetModel.getQuestion_7());
                    } else {
                        cell.setCellValue(0);
                    }

                    break;

                case 11:
                case 19:
                case 27:
                    if (sheetModel.getQuestion_8() != null) {
                        total+=Integer.valueOf(sheetModel.getQuestion_8());
                        cell.setCellValue(sheetModel.getQuestion_8());
                    } else {
                        cell.setCellValue(0);
                    }

                    break;
            }
        }
        return total;
    }

    private void addHeader(Cell cell,String value){
        cell.setCellValue(value);
    }

    private Map create(String fileName){
        Map data = new HashMap();

        if(fileName.equalsIgnoreCase("IMG_20190510_061610.jpg")) {
            String[] r1 = {"1", "3", "5", "7", "9", "7", "1", "1", "1", "3", "5", "7", "9", "7", "1", "1"};
            String[] r2 = {"1", "9", "5", "9", "1", "3", "1", "7", "3", "3", "9", "7", "2", "1", "7"};
            String[] r3 = {"0", "6", "4", "8", "6", "8", "6", "4", "2", "0", "4", "4", "2", "0", "8", "4"};

            data.put("test1", new ArrayList<String>(Arrays.asList(r1)));
            data.put("test2", new ArrayList<String>(Arrays.asList(r2)));
            data.put("test3", new ArrayList<String>(Arrays.asList(r3)));
        }

        if(fileName.equalsIgnoreCase("IMG_20190513_212814.jpg")) {
            String[] r1 = {"6", "7", "8", "9", "0", "9", "7", "8", "9", "8", "9"};
            String[] r2 = {"7", "1", "1", "1", "3", "9", "5", "3", "9", "7", "9", "5", "9"};
            String[] r3 = {"1", "2", "9", "0", "3", "4", "6", "5", "9", "7", "8", "9", "0", "6", "0", "8"};

            data.put("test1", new ArrayList<String>(Arrays.asList(r1)));
            data.put("test2", new ArrayList<String>(Arrays.asList(r2)));
            data.put("test3", new ArrayList<String>(Arrays.asList(r3)));
        }
        return data;
    }


}
