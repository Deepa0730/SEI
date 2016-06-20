package com.fileupload.servicestest;

import org.junit.Assert;
import org.junit.Test;

import com.fileupload.services.UploadFileService;

public class UploadFileServiceTest {

	UploadFileService service = new UploadFileService();

	@Test
	public void formatDatetest() {

		String input = "03/31/2007T03:32:12Z";
		String expectedResult = "3/31/07 3:32 AM EDT";
		String result = service.formatDate(input);
		Assert.assertEquals(expectedResult, result);

	}

	@Test
	public void formatValueTest() {
		String inputvalues = ".23";
		String expectedResult = "$0.23";
		String result = service.formatValue(inputvalues);
		Assert.assertEquals(expectedResult, result);

	}

}
