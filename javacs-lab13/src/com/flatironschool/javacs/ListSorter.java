/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        //return null;
		
		if(list.size() == 1)
        	return list;

		int s1 = 0;
		int s2 = 0;
		List<T> split1 = list.subList(0, list.size()/2);
        List<T> split2 = list.subList(list.size()/2, list.size());
        List<T> retList = new LinkedList<T>();


        split1 = mergeSort(split1, comparator);
        split2 = mergeSort(split2, comparator);

        T part1 = split1.get(s1);
        T part2 = split2.get(s2);

        while(retList.size() < list.size())
        {

        	if(comparator.compare(part2, part1) > 0)
        	{
        		retList.add(part1);
        		s1 = s1+1;
        		if(s1 == split1.size())
        		{
        			retList.add(part2);
        			s2=s2+1;
        			if(s2 < split2.size())
        				retList.add(split2.get(s2));
        		}
        		else
        		{
        			part1 = split1.get(s1);
        		}
        	}
        	else
        	{
        		retList.add(part2);
        		s2 = s2+1;
        		if(s2 == split2.size())
        		{
        			retList.add(part1);
        			s1=s1+1;
        			if(s1 < split1.size())
        				retList.add(split1.get(s1));
        		}
        		else
        		{
        			part2 = split2.get(s2);
        		}
        	}
        }

        return retList;

	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
         PriorityQueue<T> newHeap = new PriorityQueue<T>();

        for(T val : list)
        {
        	newHeap.offer(val);
        }

        list.clear();

        while(!newHeap.isEmpty())
        {
        	list.add(newHeap.poll());
        }

	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        //return null;
		T var;

   		List<T> retK = new LinkedList<T>();

        PriorityQueue<T> newHeap = new PriorityQueue<T>();
     
    

		for(T val : list)
        {
			if(newHeap.size() < k)
        		newHeap.offer(val);
        	else
        	{
        		var = newHeap.poll();
        		
        		if(comparator.compare(val, var) > 0)
        			newHeap.offer(val);
        		else
        			newHeap.offer(var);
        	}
        }

		while(!newHeap.isEmpty())
		{
			retK.add(newHeap.poll());
		}

		return retK;

	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
