package com.assignment.deldup;

import org.apache.log4j.Logger;

import com.assignment.service.DuplicateDeleteService;

/**
 * Java Program to remove duplicate with Three implementations
 * 
 * @author Deepa Ramchandani
 *
 */
public class DeDup {

	public static int[] randomintegers = { 1, 2, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16, 19,
			1, 18, 4, 9, 3, 20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16, 3, 6,
			19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	static final Logger LOGGER = Logger.getLogger(DeDup.class.getName());

	public static void main(String[] args) {
		try {

			DuplicateDeleteService service = new DuplicateDeleteService();
			service.rmvDuplicatesOne(randomintegers);
			service.rmvDuplicatesTwo(randomintegers);
			service.rmvDuplicatesThree(randomintegers);

		} catch (Exception ex) {
			LOGGER.error("Exception in main method" + ex);
		}

	}
}
