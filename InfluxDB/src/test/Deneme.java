package test;


public class Deneme {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread() {
			public void run() {
				for(int i=0;i<10;i++) {
					System.out.print("T1- ");
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				for(int j=0;j<10;j++) {
					System.out.print("T2- ");
				}
			}
		};
		
		t1.start();
		t2.start();
		
	}
}



/*
new Thread(new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("Deneme");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}).start();


System.out.println("Test");
*/
