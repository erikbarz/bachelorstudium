package ship;

public class ShipFactory {
	public static Ship getShip(int size){
		try{
			if(size==5)return new AircraftCarrier(false, -2, -2);
			if(size==4)return new Battleship(false, -2, -2);
			if(size==3)return new Cruiser(false, -2, -2);
			if(size==2)return new Destroyer(false, -2, -2);
			if(size==1)return new Submarine(false, -2, -2);
		}
		catch(Exception e){
			System.out.println("IOException:"+e);
			e.printStackTrace();
		}
		return null;
	}
}
