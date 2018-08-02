package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Objects.Phone;
import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Objects.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ParamsCreator {
    private static String TAG_CLASS = ParamsCreator.class.getSimpleName();

    /**
     * Method to Create Params for Insert of Phones.
     *
     * @param phone: The Phone Object which is to be inserted.
     * @return params: For the insertion of the Phone.
     */
    public static Map<String, String> createParamsForInsertPhone(Phone phone) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put(Constants.JSON_TYPE, Constants.JSON_TYPE_INSERT);
            params.put(Constants.JSON_TABLE_NAME, Constants.PHONE_TABLE);
            String values = "'" + phone.getManufacturer() + "','" +
                    phone.getModel() + "','" +
                    phone.getSerial() + "','" +
                    phone.getImei() + "','" +
                    phone.getBssid() + "','" +
                    phone.getRegion() + "','" +
                    phone.getUuid() + "','" +
                    phone.getActualCapacity() + "'";
            params.put(Constants.JSON_INSERT_VALUES, values);
        } catch (Exception e) {
            Message.logMessage(TAG_CLASS, e.toString());
        }
        return params;
    }

    public static Map<String, String> createParamsForSelectPhone(String imei) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put(Constants.JSON_TYPE, Constants.JSON_TYPE_SELECT);
            params.put(Constants.JSON_TABLE_NAME, Constants.PHONE_TABLE);
            String where = Constants.IMEI_NUMBER + " LIKE '" + imei + "'";
            params.put(Constants.JSON_WHERE, where);
        } catch (Exception e) {
            Message.logMessage(TAG_CLASS, e.toString());
            return params;
        }
        return params;
    }

    public static Map<String, String> createParamsForInsertReport(DeviceInformation deviceInformation) {
        Map<String, String> params = new HashMap<>();
        boolean overall = true;
        try {
            params.put(Constants.JSON_TYPE, Constants.JSON_TYPE_INSERT);
            params.put(Constants.JSON_TABLE_NAME, Constants.REPORT_TABLE);
            StringBuilder values = new StringBuilder();
            values.append("'").append(deviceInformation.getIMEINumber(true)).append("',");
            //Adding the Automated Test.
            for (Test test : Constants.automatedTestListBackUp) {
                if (ExcelCreator.isPresent(test, false)) {
                    values.append("'yes',");
                } else {
                    values.append("'no',");
                    overall = false;
                }
            }
            for (Test test : Constants.manualTestListBackUP) {
                if (ExcelCreator.isPresent(test, true))
                    values.append("'yes',");
                else {
                    values.append("'no',");
                    overall = false;
                }
            }
            if (overall)
                values.append("'successful',");
            else values.append("'unsuccessful',");

            values.append("'").append(Constants.REPORT_UUID_VALUE).append("',");
            String time=String.valueOf(Calendar.getInstance().getTimeInMillis());
            values.append("'").append(time).append("'");
            params.put(Constants.JSON_INSERT_VALUES, values.toString());
        } catch (Exception e) {
            Message.logMessage(TAG_CLASS, e.toString());
            return params;
        }
        return params;
    }
}
