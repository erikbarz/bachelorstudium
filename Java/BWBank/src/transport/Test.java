package transport;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Transportation> list=new ArrayList();
		list.add(new Car(4));
		list.add(new Boat());
		list.add(new Plane());
		list.add(new Amphibium(false));
		list.add(new Amphibium(true));
		
		System.out.println("starting...");
		for(Transportation t : list){
			System.out.println(t);
			t.start();
			System.out.println(t);
		}
		
		System.out.println("stopping...");
		for(Transportation t : list){
			System.out.println(t);
			t.stop();
			System.out.println(t);
		}
	}

}
