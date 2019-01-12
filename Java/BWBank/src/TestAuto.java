/**
 * 
 */

/**
 * @author Erik
 *
 */
public class TestAuto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		auto z4=new auto("bmw", 1400);
		z4.start();
		
		
		
		System.out.println(z4);
		
		z4.setSpeedTo(210);
		System.out.println(z4);
		z4.setSpeedTo(120);
		System.out.println(z4);
		z4.setSpeedTo(0);
		System.out.println(z4);
		z4.stop();
		System.out.println(z4);
	}

}
