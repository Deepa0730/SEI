package com.assignment.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Service class providing implementation of three methods to remove duplicates
 * 
 * @author deepa
 *
 */
public class DuplicateDeleteService {

	static final Logger LOGGER = Logger.getLogger(DuplicateDeleteService.class.getName());

	/**
	 * 
	 * rmvDuplicatesOne() Removes duplicates from given array using Set Pros
	 * :Simplest way to remove duplicates , Effective in large data Cons : Does
	 * not maintain order of the elements Use case : When only duplicates are
	 * removed and order is not maintained
	 * 
	 * @param givenArr
	 * @return HashSet with unique elements
	 */
	public HashSet rmvDuplicatesOne(int givenArr[]) {
		HashSet<Integer> noDuplicatesOutput = null;
		try {
			noDuplicatesOutput = IntStream.of(givenArr).boxed().collect(Collectors.toCollection(HashSet::new));
			displayFormat(noDuplicatesOutput);

		} catch (IndexOutOfBoundsException iex) {
			LOGGER.error("Exception in method rmvDuplicatesOne()" + iex);
		} catch (Exception lex) {
			LOGGER.error("Exception thrown" + lex);
		}
		return noDuplicatesOutput;

	}

	/**
	 * rmvDuplicatesTwo() Removes duplicates from given array Maintains order
	 * Uses LinkedHashSet Pros : Maintains Order Effective and simple to apply
	 * Use Case : When order of insertion has to match order of iteration
	 * 
	 * @param given
	 *            Array
	 * @return LinkedHashSet with no duplicates and order
	 */
	public LinkedHashSet<Integer> rmvDuplicatesTwo(int giveArr[]) {
		LinkedHashSet<Integer> noDuplicatesOrdered = new LinkedHashSet<Integer>();
		try {
			for (int i = 0; i < giveArr.length; i++) {
				noDuplicatesOrdered.add(giveArr[i]);
			}
			LOGGER.debug("Duplicates removed with order using LinkedHashSet ::" + noDuplicatesOrdered);

		} catch (Exception ex) {
			LOGGER.error("Exception thrown in rmvDuplicatesTwo() " + ex);
		}
		return noDuplicatesOrdered;
	}

	/**
	 * rmvDuplicatesThree(int[] a Removes duplicates from given array Not using
	 * any collection utility Pros : None Cons : Issue in removing duplicates ,
	 * Array being fixed length needs new Array Leads to lot of temporary arrays
	 * leading to memory and CPU requirements. Use case : When random access of
	 * elements are needed
	 * 
	 * @param input
	 *            Array
	 * @return unique values
	 */
	public ArrayList rmvDuplicatesThree(int[] givenArr) {
		String toprint = null;
		ArrayList<Integer> noDuplicatesOutput = new ArrayList<>();
		try {
			int[] result = new int[givenArr.length];

			int j = 0;
			int count = 0;
			for (int i : givenArr) {
				if (!isExist(result, i)) {
					result[j++] = i;
					count++;
				}
			}
			for (int i = 0; i < count; i++) {
				noDuplicatesOutput.add(result[i]);
			}
			displayFormat(noDuplicatesOutput);
		} catch (Exception ex) {
			LOGGER.error("Exception in rmvDuplicatesThree " + ex);
		}
		return noDuplicatesOutput;

	}

	/**
	 * Method called from remvDuplicateThree to check for duplicates
	 * 
	 * @param result
	 * @param i
	 * @return
	 */
	private static boolean isExist(int[] result, int i) {
		for (int j : result) {
			if (j == i) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param input
	 *            Method using String Utils for display purpose
	 * @return
	 */
	public String displayFormat(Collection input) {

		String displayFormat = new String();
		displayFormat = StringUtils.join(input, ',');
		LOGGER.debug("Duplicates removed printing output ::" + displayFormat);
		return displayFormat;

	}

}
