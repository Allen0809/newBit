package cn.tedu.ttms.product.entity;



public class t {
	public static void main(String[] args) {
		Gril g = Gril.getGril();
		Gril gg = Gril.getGril();
		System.out.println();
		
	}
 	
}
class Gril{
	private static Gril g = new Gril();
	private Gril(){
		
	}
	public static Gril getGril(){
		return g;
	}
}











