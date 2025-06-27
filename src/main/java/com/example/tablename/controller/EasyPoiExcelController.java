package com.example.tablename.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.tablename.model.AttendanceBo;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.StringUtils;

/**
 * @author admin
 */
public class EasyPoiExcelController {

    @FXML private TextArea resultTextArea;
    @FXML private Label fileNameLabel;

    @FXML
    private void handleFileSelect() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "Excel文件", "*.xlsx", "*.xls"));
        File file = fc.showOpenDialog(null);

        if (file != null) {
            fileNameLabel.setText(file.getName());
            List<AttendanceBo> attendanceBos = parseWithEasyPoi(file);
            if (attendanceBos != null && !attendanceBos.isEmpty()){
                resultTextArea.clear();
                Map<String,String> temp = new HashMap<>();
                if (attendanceBos.stream().anyMatch(a-> !a.isEmpty() && a.hasBadParam())){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("系统异常");
                    alert.setContentText("必要参数数据异常");
                    alert.showAndWait();
                }else {
                    attendanceBos.forEach(attendanceBo ->{
                        if (!attendanceBo.isEmpty()){
                            resultTextArea.appendText(attendanceBo.toRecordSql());
                            if (!temp.containsKey(attendanceBo.getEmployeeId())
                                    && "01".equals(StringUtils.substring(attendanceBo.getAttendanceDatetime(), 8,10))){
                                resultTextArea.appendText(attendanceBo.toStatisticSql());
                                temp.put(attendanceBo.getEmployeeId(),"1");
                            }
                        }
                    });
                }
            }
        }
    }

    private List<AttendanceBo> parseWithEasyPoi(File file) {
        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            return ExcelImportUtil.importExcel(
                    file,
                    AttendanceBo.class,
                    params
            );
        } catch (Exception e) {
            Platform.runLater(() ->
                    fileNameLabel.setText("解析失败: " + e.getMessage()));
        }
        return null;
    }
}
