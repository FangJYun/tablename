package com.example.tablename.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.io.Serializable;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 * 考勤数据
 *
 * @author fangjy
 * @date 2025-06-24 13:25
 **/
public class AttendanceBo implements Serializable {
    @Excel(name = "员工id", isImportField = "true")
    private String employeeId;
    @Excel(name = "上下班状态(1上班，-1下班)", isImportField = "true")
    private String dutyType;
    @Excel(name = "打卡时间（yyyy-MM-dd HH:mm:ss）",importFormat = "yyyy-MM-dd HH:mm:ss", isImportField = "true")
    private String attendanceDatetime;
    @Excel(name = "状态（1 正常、2 迟到、3早退）", isImportField = "true")
    private String status = "1";
    @Excel(name = "考勤地点地址", isImportField = "true")
    private String attendanceSite;
    @Excel(name = "经度", isImportField = "true")
    private String longitude = "120";
    @Excel(name = "纬度", isImportField = "true")
    private String latitude="30";
    @Excel(name = "手机设备号", isImportField = "true")
    private String deviceCode="123456";

    public AttendanceBo() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType;
    }

    public String getAttendanceDatetime() {
        return attendanceDatetime;
    }

    public void setAttendanceDatetime(String attendanceDatetime) {
        this.attendanceDatetime = attendanceDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttendanceSite() {
        return attendanceSite;
    }

    public void setAttendanceSite(String attendanceSite) {
        this.attendanceSite = attendanceSite;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public boolean isEmpty() {
        return Stream.of(employeeId, dutyType, attendanceDatetime,attendanceSite)
                .allMatch(StringUtils::isBlank);
    }
    public boolean hasBadParam() {
        return Stream.of(employeeId, dutyType, attendanceDatetime,attendanceSite)
                .anyMatch(StringUtils::isBlank);
    }
    @Override
    public String toString() {
        return "AttendanceBo{" +
                "employeeId='" + employeeId + '\'' +
                ", dutyType='" + dutyType + '\'' +
                ", attendanceDatetime='" + attendanceDatetime + '\'' +
                ", status='" + status + '\'' +
                ", attendanceSite='" + attendanceSite + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", deviceCode='" + deviceCode + '\''+
                "}";
    }
    public String toRecordSql() {
        String sql = "INSERT INTO `oa_attendance_record_9` (`c_id`, `c_company_id`, `c_employee_id`, `dt_attendance_date`, `c_duty_type`, `dt_standard_time`, `dt_attendance_time`, `i_status`, `c_attendance_site`, `c_longitude`, `c_latitude`, `c_device_code`, `i_device_error`, `i_opsition_error`, `c_remark`, `c_form_id`, `dt_add_time`, `i_attendance_times`, `c_flextime`, `i_type`, `c_rule_id`, `c_timestamp`, `c_wifi_name`, `c_mac_address`, `dt_attendance_datetime`) "
                + "VALUES (replace(UUID(),'-',''), 'cafb003e02cb4afabe17d908b70f0859', '%s', '%s', %s, '17:30:00', NULL, %s, '%s', '%s', '%s', '%s', 1, 1, NULL, NULL, '%s', 0, 35, 0, '8FF13258E7244220A7DD05AE047331CC', '1738891156797', NULL, NULL, '%s');\r\n";
        return String.format(sql, employeeId, StringUtils.substringBefore(attendanceDatetime, " "),dutyType,status,attendanceSite,longitude,latitude,deviceCode,attendanceDatetime,attendanceDatetime);
    }
    public String toStatisticSql() {
        String sql = "INSERT INTO `oa_attendance_statistic` (`c_id`, `c_company_id`, `c_employee_id`, `dt_statistic_period`)"
                + " VALUES (replace(UUID(),'-',''), 'cafb003e02cb4afabe17d908b70f0859', '%s','%s');\r\n";
        return String.format(sql, employeeId, StringUtils.substring(attendanceDatetime, 0,7));
    }
}
