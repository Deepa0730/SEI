package com.assignment.servicetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.assignment.service.DuplicateDeleteService;

@RunWith(Parameterized.class)
public class DuplicateDeleteServiceTest {

	private int[] inputlist;

	public DuplicateDeleteServiceTest(int[] l1) {
		this.inputlist = l1;
	}

	@Parameters
	public static Collection<int[]> testCase() {
		return Arrays.asList(new int[] { 1, 1, 2, 3, 4, 5, 6, 89, 7, 4, 6, 100 });

	}

	/**
	 * This implementation first checks if the specified object is this set; if
	 * so it returns true. Then, it checks if the specified object is a set
	 * whose size is identical to the size of this set; if not, it returns
	 * false. If so, it returns containsAll((Collection) o).
	 */
	@Test
	public void testRmvDuplicatesOne() {

		HashSet<Integer> expectedResult = new HashSet<>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(4);
		expectedResult.add(5);
		expectedResult.add(6);
		expectedResult.add(89);
		expectedResult.add(7);
		expectedResult.add(100);
		HashSet<Integer> result = new HashSet<Integer>();

		result = new DuplicateDeleteService().rmvDuplicatesOne(inputlist);
		result.equals(expectedResult);

	}

	/**
	 * This Method checks duplicates along with order using Order maintained
	 * 
	 */
	@Test
	public void testRmvDuplicatesTwo() {
		LinkedHashSet<Integer> expectedResult = new LinkedHashSet<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(4);
		expectedResult.add(5);
		expectedResult.add(6);
		expectedResult.add(89);
		expectedResult.add(7);
		expectedResult.add(100);
		LinkedHashSet<Integer> result = new LinkedHashSet<>();
		result = new DuplicateDeleteService().rmvDuplicatesTwo(inputlist);
		Assert.assertThat(result, IsIterableContainingInOrder.contains(expectedResult.toArray()));

	}

	@Test
	public void testrmvDuplicatesThree() {

		ArrayList<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(1);
		expectedResult.add(2);
		expectedResult.add(3);
		expectedResult.add(4);
		expectedResult.add(5);
		expectedResult.add(6);
		expectedResult.add(89);
		expectedResult.add(7);
		expectedResult.add(100);

		ArrayList<Integer> result1 = new ArrayList<Integer>();
		result1 = new DuplicateDeleteService().rmvDuplicatesThree(inputlist);
		Assert.assertEquals(expectedResult, result1);

	}

}
