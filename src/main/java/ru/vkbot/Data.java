package ru.vkbot;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    public int test = 0;
    public String gotovo = " ";
    public int indexRowGroup;
    public int indexCellGroup;
    public StringBuilder otvet = new StringBuilder();
    public String[] integu = {"ГДБО","ГЭБО","ГСБО","ГУБО","ГИБО","ГМБО","ГДБЗ","ГЭМО","ГУМО","ГИМО","ГСМО","ГММО","ГДМО"};
    public String[] iit = {"ИНБО","ИМБО","ИАБО","ИВБО","ИКБО","ИВМО","ИКМО","ИНМО","ИММО","ИАМО"};
    public String[] ik = {"КМБО","КББО","КУБО","КСБО","КРБО","КТСО","ККСО"};
    public String[] ikib = {"КММО","КСМО","КРМО","КУМО","КБМО"};
    public String[] irts = {"РССО","РРБО","РИБО","РКБО","РГБО","РСБО","РКМО","РРМО","РПМО","РГМО"};
    public String[] itxt = {"ХЕБО","ХББО","ХХБО","ХТБО","ТЛБО","ЭСБО","ХЕМО","ХБМО","ХХМО","ХТМО","ХФМО"};
    public String[] iap = {"УПБО","УУБО","УМБО","УЭБО","УКБО","УНБО","УЮБО","УХБО","УКМО","УОМО","УПМО","УММО","УЮМО","УФМО"};
    public String[] kbisp = {"БАСО","БББО","БИСО","БСБО","ББСО","БФБО","БЭСО","БОСО","БИСО","БСМО","ББМО","РКМО","БПМО","БМБО","РКМО","БПБО","БТСО"};
    public String[] fti = {"ЭЛБО","ЭОСО","ЭЭБО","ЭНБО","ЭЭМО","ЭСМО","ЭОМО","ЭНМО","ТДМО","ТЛМО","ТОМО","ТХМО","ТММО","ТСБО","ТЛБО","ТОБО","ТШБО","ТХБО","ТТБО"};

    public String find (String group) throws IOException {
        File myFolder = new File("C:\\расписание");
        File[] files = myFolder.listFiles();
        if (useList(iit,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИИТ") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(integu,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИИНТЕГУ") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(ik,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИК") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(ikib,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИКиб") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(irts,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИРТС") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(itxt,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИТХТ") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(iap,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ИЭП") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(kbisp,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("КБиСП") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (useList(fti,group)) {
            for (int i = 0; i< files.length;i++) {
                if (files[i].toString().indexOf("ФТИ") != -1 && SearchGroup(group, files[i].toString()) == 1) {
                    gotovo = printExcel(files[i].toString());
                    break;
                }
            }
        }
        if (gotovo.equals(" ")){
            gotovo="не найдена";
        }
        return gotovo;

    }

    public static boolean useList(String[] arr, String group) {
        return Arrays.asList(arr).contains(group.substring(0,4));
    }


    public int SearchGroup(String group, String puti) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(puti);
        Workbook wb = new XSSFWorkbook(fileInputStream);
        for (Row row : wb.getSheetAt(0)) {
            for (Cell cell : row) {
                Cell r = cell;
                if (getCellText(cell).indexOf(group) != -1){
                    indexRowGroup = r.getRowIndex();
                    indexCellGroup = r.getColumnIndex();
                    test = 1;
                    break;
                }
            }
        }
        return test;
    }

    public String find1 (String group) throws IOException {
        File myFolder = new File("C:\\расписание");
        File[] files = myFolder.listFiles();
        for (int i = 0; i< files.length;i++){
            if (SearchGroup(group, files[i].toString())==1){
                gotovo = printExcel(files[i].toString());
                break;
            }
        }
        if (gotovo.equals(" ")){
            gotovo="не найдена";
        }
        return gotovo;
    }

    public String printExcel(String puti) throws IOException{
        FileInputStream fis = new FileInputStream(puti);
        Workbook wb1 = new XSSFWorkbook(fis);
        int addNewPredmet=0;
        int indexRowPredmet = indexRowGroup + 2;
        int indexRowPredmetChet = indexRowGroup + 3;
        if(TimeTest.StudyWeek()!=-1) {
            for (int i = 0; i < 6; i++) {
                TimeTest.PrintDay(i, otvet);
                for (int i1 = 0; i1 < 6; i1++) {
                    if (TimeTest.StudyWeek()==1) {
                        String predmet = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup)) + "\n";
                        if (!predmet.equals("\n")) {
                            if (!Character.isDigit(predmet.charAt(0))) {
                                String type = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 1)) + "\n";
                                String teacher = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 2)) + "\n";
                                String location = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 3)) + "\n";
                                TimeTest.PrintTime(i1, otvet);
                                otvet.append(predmet);
                                otvet.append(type);
                                otvet.append(teacher);
                                otvet.append(location);
                                otvet.append("\n");
                            }
                            else {
                                Pattern p1 = Pattern.compile("[1-9]{1,2}");
                                Matcher m1 = p1.matcher(predmet);
                                while (m1.find()){
                                    String thisWeek = m1.group();
                                    int thisWeekInt = Integer.parseInt(thisWeek);
                                    if (thisWeekInt==TimeTest.SemesterWeek()){
                                        String type = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 1)) + "\n";
                                        String teacher = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 2)) + "\n";
                                        String location = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 3)) + "\n";
                                        TimeTest.PrintTime(i1, otvet);
                                        otvet.append(predmet);
                                        otvet.append(type);
                                        otvet.append(teacher);
                                        otvet.append(location);
                                        otvet.append("\n");
                                    }
                                }
                            }
                        }
                        addNewPredmet = addNewPredmet + 2;
                    }
                    if (TimeTest.StudyWeek()==2) {
                        String predmet = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmetChet + addNewPredmet).getCell(indexCellGroup)) + "\n";
                        if (!predmet.equals("\n")) {
                            if (!Character.isDigit(predmet.charAt(0))) {
                                String type = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmetChet + addNewPredmet).getCell(indexCellGroup + 1)) + "\n";
                                String teacher = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmetChet + addNewPredmet).getCell(indexCellGroup + 2)) + "\n";
                                String location = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmetChet + addNewPredmet).getCell(indexCellGroup + 3)) + "\n";
                                TimeTest.PrintTime(i1, otvet);
                                otvet.append(predmet);
                                otvet.append(type);
                                otvet.append(teacher);
                                otvet.append(location);
                                otvet.append("\n");
                            }
                            else {
                                Pattern p1 = Pattern.compile("[1-9]{1,2}");
                                Matcher m1 = p1.matcher(predmet);
                                while (m1.find()){
                                    String thisWeek = m1.group();
                                    int thisWeekInt = Integer.parseInt(thisWeek);
                                    if (thisWeekInt==TimeTest.SemesterWeek()){
                                        String type = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 1)) + "\n";
                                        String teacher = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 2)) + "\n";
                                        String location = getCellText(wb1.getSheetAt(0).getRow(indexRowPredmet + addNewPredmet).getCell(indexCellGroup + 3)) + "\n";
                                        TimeTest.PrintTime(i1, otvet);
                                        otvet.append(predmet);
                                        otvet.append(type);
                                        otvet.append(teacher);
                                        otvet.append(location);
                                        otvet.append("\n");
                                    }
                                }
                            }
                        }
                        addNewPredmet = addNewPredmet + 2;
                    }
                }
            }
        }
        else {
            otvet.append("Сейчас каникулы!");
        }
        return otvet.toString();
    }

    public String getCellText(Cell cell){
        String res="";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                res = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    res = cell.getDateCellValue().toString() ;
                }
                else {
                    res = Double.toString(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                res = Boolean.toString(cell.getBooleanCellValue()) ;
                break;
            case Cell.CELL_TYPE_FORMULA:
                res = cell.getCellFormula().toString() ;
                break;
            default:
                break;
        }
        return res;
    }
}
