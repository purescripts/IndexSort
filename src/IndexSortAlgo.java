import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of the INDEX SORT, my own creation
 * Due to heap limitations very large sized arrays with many duplicates cause OutOfMemoryErrors
 * @author Michael Mariano
 * @version 2.0 June 22, 2013
 *
 */
public class IndexSortAlgo{

	/**
	 * Sort an array by using the Integer values that need to be sorted 
	 * as the index numbers in an array, which in turn orders the list
	 * @param arr - array to sort
	 * @return sorted array
	 */
	@SuppressWarnings("unchecked")
	public int [] indexSort(int[]arr,int maxNumInArr){
		//Uses an array of ArrayList, of size of the largest element in your array.
		ArrayList<Integer>[] indexArr=new ArrayList[maxNumInArr+1];

		int[]finalArr=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			if(indexArr[arr[i]]==null){
				ArrayList<Integer> temp=new ArrayList<Integer>();
				temp.add(arr[i]);
				indexArr[arr[i]]=temp;
			}
			else
				indexArr[arr[i]].add(arr[i]);
		}
		int inCount=0;
		//handles duplicate Integers
		int duplicateCount=0;
		for(int i=0;i<finalArr.length;i++){
			int temp=0;
			for(int j=inCount;j<indexArr.length;j++){
				if(indexArr[j]!=null){
					duplicateCount=indexArr[j].size();
					temp=indexArr[j].get(0);
					inCount=j+1;
					break;
				}
			}
			for(int k=0;k<duplicateCount;k++){
				finalArr[i]=temp;
				if(k!=duplicateCount-1)
					i++;
			}
		}
		return finalArr;
	}

	/**
	 * Method to check if the array is sorted
	 * @param arr - array to sort
	 * @return true: sorted; false: not sorted
	 */
	public boolean isSorted(int [] arr){
		for(int i=0;i<arr.length-1;i++)
			if(arr[i]>arr[i+1])
				return false;
		return true;		
	}
	/********************************************************
	 * Run algorithm Index Sort versus Java QuickSort
	 ********************************************************/
	public static void main(String [] args)
	{
		IndexSortAlgo IndexSort = new IndexSortAlgo();
		int[]testArr=new int[100000];
		int[]sortArr=new int[testArr.length];
		int maxNum=testArr[0];
		Random rand= new Random();
		for(int i=0;i<testArr.length;i++){
			testArr[i]=rand.nextInt(10000);
			if(testArr[i]>maxNum)
				maxNum=testArr[i];
		}
		
		System.out.println("Max Number in Array:"+maxNum+"\n");

		long start,duration=0;
		start=System.nanoTime();
		sortArr=IndexSort.indexSort(testArr,maxNum);
		duration=System.nanoTime()-start;
		System.out.println("Index Sort"+"\nArray Length:"+testArr.length+"\nExecution Time:"+ duration/1000000000.0 +" secs");
		System.out.println(IndexSort.isSorted(sortArr)?"Sort Successful\n":"Sort Unsucessful\n");
		
		start=System.nanoTime();
		Arrays.sort(testArr);
		duration=System.nanoTime()-start;
		System.out.println("Java QuickSort"+"\nArray Length:"+testArr.length+"\nExecution Time:"+ duration/1000000000.0 +" secs");
		System.out.println(IndexSort.isSorted(testArr)?"Sort Successful":"Sort Unsucessful");
	}
}
