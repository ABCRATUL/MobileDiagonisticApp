package com.example.pronoymukherjee.hyperxchangediagnosticnew.Helper;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * This is the class to make the excel files for the test Report.
 */
public class ExcelCreator {
    Context context;

    public ExcelCreator(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean createExcel() {
        DeviceInformation deviceInformation = new DeviceInformation(context);
        File path = new File(Environment.getExternalStorageDirectory() + File.separator +
                Constants.HX_FOLDER_NAME + File.separator + Constants.HX_REPORT_FOLDER_NAME);
        if (!path.exists())
            path.mkdirs();
        try {
            WritableWorkbook workbook = Workbook
                    .createWorkbook(new File(path, Constants.HX_REPORT_FILE_NAME));
            WritableSheet sheet = workbook.createSheet(Constants.HX_REPORT_SHEET, 0);
            WritableFont font15 = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(font15);
            Label label = new Label(3, 2, Constants.LEGAL_COMPANY_NAME, cellFormat);
            sheet.addCell(label);
            WritableFont font17 = new WritableFont(WritableFont.ARIAL, 17, WritableFont.BOLD, true);
            cellFormat = new WritableCellFormat(font17);
            Label logoLabel = new Label(5, 4, Constants.COMPANY_NAME, cellFormat);
            sheet.addCell(logoLabel);
            logoLabel = new Label(2, 4, "Hardware Report", cellFormat);
            sheet.addCell(logoLabel);
            font17 = new WritableFont(WritableFont.TIMES, 15, WritableFont.BOLD);
            WritableCellFormat cellFormatMidHeading = new WritableCellFormat(font17);
            Label midHeadingLabel = new Label(2, 6, "Hardware Details",
                    cellFormatMidHeading);
            sheet.addCell(midHeadingLabel);
            WritableFont font13 = new WritableFont(WritableFont.TIMES, 13);
            cellFormat = new WritableCellFormat(font13);

            //Adding the Manufacturer.
            Label headingLabel = new Label(2, 6, "Manufacturer:", cellFormat);
            sheet.addCell(headingLabel);
            Label valueLabel = new Label(3, 6, deviceInformation.getDeviceManufacturer(),
                    cellFormat);
            sheet.addCell(valueLabel);

            //Adding the Model Name.
            headingLabel = new Label(2, 7, "Model:", cellFormat);
            valueLabel = new Label(3, 7, deviceInformation.getDeviceModelName(), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //adding Serial Number
            headingLabel = new Label(2, 8, "Serial:", cellFormat);
            valueLabel = new Label(3, 8, deviceInformation.getSerialNumber(), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the IMEI Number.
            headingLabel = new Label(2, 9, "IMEI:", cellFormat);
            valueLabel = new Label(3, 9, deviceInformation.getIMEINumber(true),
                    cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the BSSID.
            headingLabel = new Label(2, 10, "Wifi BSSID:", cellFormat);
            valueLabel = new Label(3, 10, deviceInformation.getBSSID(), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the Region.
            headingLabel = new Label(2, 11, "Region:", cellFormat);
            valueLabel = new Label(3, 11, deviceInformation.getRegion(), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the UUID.
            headingLabel = new Label(2, 12, "UUID:", cellFormat);
            valueLabel = new Label(3, 12, deviceInformation.getUUID(), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the Battery Information Heading.
            midHeadingLabel = new Label(2, 14, "Battery Information", cellFormatMidHeading);
            sheet.addCell(midHeadingLabel);

            double actualCapacity = deviceInformation.getBatteryActualCapacity();
            double wearCapacity = 0.0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                wearCapacity = deviceInformation.getBatteryWearCapacity();
            }
            //Adding the Battery Actual Capacity.
            headingLabel = new Label(2, 16, "Actual Capacity:", cellFormat);
            valueLabel = new Label(3, 16, String.valueOf(actualCapacity) + "mAh", cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the Battery Wear Capacity.
            headingLabel = new Label(2, 17, "Current Capacity", cellFormat);
            valueLabel = new Label(3, 17, String.valueOf(wearCapacity), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            //Adding the Wear Level Percentage.
            double wearLevel = 100 - ((wearCapacity / actualCapacity) * 100);
            headingLabel = new Label(2, 18, "Wear Level:", cellFormat);
            valueLabel = new Label(3, 18, String.valueOf(wearLevel), cellFormat);
            sheet.addCell(headingLabel);
            sheet.addCell(valueLabel);

            workbook.write();
            workbook.close();
            return true;
        } catch (IOException | WriteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
