package com.example.tablename.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label userRechargeTable;
    @FXML
    private Label sysUserSub;
    @FXML
    private Label sysReminderData;
    @FXML
    private Label sysAssociateData;
    @FXML
    private TextField taxNumTextField;


    @FXML
    protected void onHelloButtonClick() {
        String taxNum = taxNumTextField.getText();
        userRechargeTable.setText("充值记录表：sys_user_recharge_"+ Math.abs(taxNum.hashCode()) % 32);
        sysUserSub.setText("用户表：sys_user_sub_"+ Math.abs(taxNum.hashCode()) % 16);
        sysReminderData.setText("权益到期提醒表：sys_reminder_data_"+ Math.abs(taxNum.hashCode()) % 32);
        sysAssociateData.setText("批次关联数据表：sys_associate_data_"+ Math.abs(taxNum.hashCode()) % 8);
    }
}