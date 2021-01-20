class RaceCondition{
	public static void main(String[] args){
			MyRunnable my1 = new MyRunnable(new TwoSums(),1,5);
			MyRunnable my2 = new MyRunnable(new TwoSums(),6,10);
			Thread myThread1 = new Thread(my1, "MyThread");
			Thread myThread2 = new Thread(my2, "MyThread2");
			myThread1.start();
			myThread2.start();
			try{
			myThread1.join();
			myThread2.join();

			System.out.println("Sum1:"+my1.obj.sum2+"--Sum2:"+my2.obj.sum2);
			}
			catch(Exception ex){
				System.out.println("Exception caught"+ex);
			}
			int total = sums1.sum2+sums2.sum2;
		}
	}

class TwoSums{
	 int sum2=0;

	public void add(int val1, int val2){
		synchronized(this){
		for(int i=val1; i<=val2; i++){
			this.sum2+=i;
			}
			}
	}

}

class MyRunnable implements Runnable{
	TwoSums obj;
	int x,y;
	public MyRunnable(TwoSums obj,int x, int y){
		this.obj = obj;
		this.x = x;
		this.y=y;
	}

	public void run(){
		this.obj.add(this.x,this.y);
	}
}

