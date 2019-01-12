package exercises;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circle a=new Circle(0, 0, 1);
		Circle b=new Circle(1, 0, 1);
		Circle c=new Circle(5, 5, 1);
		Circle d=new Circle(6, 6, 1);
		
		System.out.println(a.overlapsWith(b));
		System.out.println(a.overlapsWith(c));
		System.out.println(a.overlapsWith(d));
		System.out.println(c.overlapsWith(d));
		System.out.println(c.overlapsWith(a));
		
		List<Circle> list=new ArrayList();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		
		double umfang=0;
		double area=0;
		
		for(Circle circle:list){
			umfang+=circle.calculatePerimeter();
			area+=circle.calculateArea();
		}
		System.out.println("area="+area+",umfang="+umfang);
	}

}
