package com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import com.hyperxchange.pronoymukherjee.hyperxchangediagnosticnew.Objects.Phone;

import java.util.HashMap;
import java.util.Map;

public class ParamsCreator {
    private String TAG_CLASS = ParamsCreator.class.getSimpleName();

    /**
     * Method to Create Params for Insert of Phones.
     *
     * @param phone: The Phone Object which is to be inserted.
     * @return params: For the insertion of the Phone.
     */
    public Map<String, String> createParamsForInsertPhone(Phone phone) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put(Constants.JSON_TYPE, Constants.JSON_TYPE_INSERT);
            params.put(Constants.JSON_TABLE_NAME, Constants.PHONE_TABLE);
            String values = "'" + phone.getManufacturer() + ",'" +
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
}
