package kapitel5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
	public static void main(String[] args){
		int[] f = { 5,1,8,3,9,2 };
		int[] i = sort(f);
		for(int n:i){
			System.out.println(n);
		}
	}
	
	private static int[] sort(int[] f){
		for(int j=f.length-1;j>=1;j--){
			for(int i=0; i<=j-1;i++){
				if(f[i]>f[i+1]){
					int zwischen=f[i+1];
					f[i+1]=f[i];
					f[i]=zwischen;
				}
			}
		}
		return f;
	}
}
