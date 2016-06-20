package com.fileupload.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.fileupload.model.UploadFileModel;

/**
 * UploadFileService reads the input file using Scanner and with appropriate
 * formatting sets the values in Model object
 * 
 * @author Deepa Ramchandani
 *
 */
public class UploadFileService {
	private static final Logger log = Logger.getLogger(UploadFileService.class);

	public List<UploadFileModel> Readfile(File fileUpload) {

		List<UploadFileModel> fileList = new ArrayList<UploadFileModel>();

		try {

			log.debug("Inside Readfile Method of UploadFileService");
			BufferedReader reader = new BufferedReader(new FileReader(fileUpload));
			String line = null;
			Scanner scanner = null;
			int index = 0;
			String data = null;
			HashSet<String> checkset = new HashSet<String>();

			while ((line = reader.readLine()) != null) {
				// Number of elements in each line
				String[] fields = line.split(",");
				Integer countFields = fields.length;
				log.debug("Number of elements:::" + fields.length);
				UploadFileModel model = new UploadFileModel();
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				String dated1 = null;
				String rowd2 = null;
				String descd3 = null;
				String valued4 = null;
				String unikey = "";
				boolean checkIdentical = true;
				boolean endofline = false;

				boolean flag1 = false;
				boolean flag2 = false;
				boolean flag3 = false;
				while (scanner.hasNext()) {
					data = scanner.next();
					if (index == 0) {
						flag1 = true;
						if (data.contains("T")) {
							dated1 = data;
						} else {
							rowd2 = data;
						}

					} else if (index == 1) {
						flag2 = true;
						try {
							Integer.parseInt(data);
							rowd2 = data;
						} catch (NumberFormatException ex) {
							descd3 = data;
						}
					} else if (index == 2) {
						flag3 = true;
						try {
							Double.parseDouble(data);
							valued4 = data;
						} catch (NumberFormatException nex) {
							descd3 = data;
						}

					} else if (index == 3) {
						try {
							endofline = true;
							Double.parseDouble(data);
							valued4 = data;
						} catch (NumberFormatException nex) {
							endofline = true;
						}
					} else
						log.debug("Invalid Input");
					if (countFields == 2 && (flag1 && flag2)) {
						if (dated1 == null)
							model.setFileDate("");
						else
							model.setFileDate(formatDate(dated1));
						if (rowd2 == null)
							model.setFileRow(0);
						else
							model.setFileRow(Integer.parseInt(rowd2));
						if (descd3 == null)
							model.setFileDesc("");
						else
							model.setFileDesc(descd3);
						if (valued4 == null)
							model.setFileValue("");
						else
							model.setFileValue(formatValue(valued4));
					}

					if (countFields == 3 && (flag1 && flag2 && flag3)) {
						if (dated1 == null) {
							dated1 = "";
							model.setFileDate("");
						} else
							model.setFileDate(formatDate(dated1));
						if (rowd2 == null) {
							rowd2 = "";
							model.setFileRow(0);
						} else
							model.setFileRow(Integer.parseInt(rowd2));
						if (descd3 == null) {
							descd3 = "";
							model.setFileDesc("");
						} else
							model.setFileDesc(descd3);

						if (valued4 == null) {
							valued4 = "";
							model.setFileValue("");
						} else
							model.setFileValue(formatValue(valued4));

					} else if (endofline) {
						unikey = (dated1.trim() + descd3.trim()).trim();
						if ((checkset.contains(unikey))) {
							checkIdentical = false;
						} else {
							checkset.add(unikey);
							if (!(dated1.equals("")))
								model.setFileDate(formatDate(dated1));
							model.setFileRow(Integer.parseInt(rowd2));
							model.setFileDesc(descd3);
							if (!(valued4.equals("")))
								model.setFileValue(formatValue(valued4));
							dated1 = "";
							rowd2 = "";
							descd3 = "";
							valued4 = "";
						}
					}
					index++;
				}

				if (checkIdentical)
					fileList.add(model);
				index = 0;
			}
			scanner.close();
			reader.close();
		} catch (Exception e) {
			log.error("Exception thrown in Readfile method of UploadFileService ::" + e);
		}
		return fileList;
	}

	/**
	 * Method is used to format date as per requirement
	 * 
	 * @param orgDate
	 * @return finalDateOutput
	 */
	public String formatDate(String orgDate) {

		StringBuffer strbff = new StringBuffer(orgDate);
		String outputTimeZone = null;
		String noleadMnth = null;
		String noleadDate = null;
		String yearlastTwo = null;
		String finalTime = null;
		String finalDateOutput = null;
		try {
			log.debug("Inside formatDate method of UploadFileAction::");
			String onlyMonth = strbff.substring(0, 2);

			Integer month = new Integer(onlyMonth.substring(0, 2));
			if (month < 10) {
				noleadMnth = onlyMonth.substring(1);
			} else {
				noleadMnth = onlyMonth;
			}
			String onlyDate = strbff.substring(3, 5);

			Integer date = new Integer(onlyDate);
			if (date < 10) {
				noleadDate = onlyDate.substring(1);
			} else {
				noleadDate = onlyDate;
			}
			yearlastTwo = strbff.substring(8, 10);

			String time = strbff.substring(11, 19);

			DateFormat timeformat = new SimpleDateFormat("h:mm");
			Date formatteddate = null;

			formatteddate = timeformat.parse(time);
			SimpleDateFormat output = new SimpleDateFormat("h:mm aa");

			finalTime = output.format(formatteddate);

			String timeZone = strbff.substring(19);

			if (timeZone.equals("Z"))
				outputTimeZone = "EDT";
			// Final Date Output
			finalDateOutput = noleadMnth + "/" + noleadDate + "/" + yearlastTwo + " " + finalTime + " "
					+ outputTimeZone;
			log.debug("Date input from file ::::::" + orgDate);
			log.debug("Final Date Output::::::::" + finalDateOutput);
		} catch (Exception e) {
			log.error("Exception in formatting date" + e);
		}
		return finalDateOutput;

	}

	/**
	 * Method converts currency into USD format
	 * 
	 * @param val
	 * @return dollarvalue
	 */
	public String formatValue(String val) {
		String dollarvalue = "";
		try {
			log.debug("Inside formatValue method of UploadFileService");
			DecimalFormat moneyformat = new DecimalFormat("$0.00");
			Double valuedo = Double.parseDouble(val);
			dollarvalue = moneyformat.format(valuedo);
		} catch (Exception ex) {
			log.error("Exception in formatValue method of UploadFileServie ::" + ex);
		}
		return dollarvalue;
	}

}
