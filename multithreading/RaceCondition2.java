class RaceCondition2{
	public static void main(String[] args){
			TwoSums sums = new TwoSums(1,5);
			MyRunnable my1 = new MyRunnable(1,5);
			MyRunnable my2 = new MyRunnable(1,5);
			Thread myThread1 = new Thread(my1, "MyThread");
			Thread myThread2 = new Thread(my2, "MyThread2");
			myThread1.start();
			myThread2.start();
			try{
			myThread1.join();
			myThread2.join();

			System.out.println("Sum1:"+my1.sum+"--Sum2:"+my2.sum);
			}
			catch(Exception ex){
				System.out.println("Exception caught"+ex);
			}
		}
	}

class TwoSums{
	 int sum2=0;
	int x,y;
	public TwoSums(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void add(){
		for(int i=this.x; i<=this.y; i++){
			this.sum2+=i;
			}
	}

}

class MyRunnable implements Runnable{
	TwoSums obj;
	int x,y;
	int sum;
	public MyRunnable(TwoSums obj){
		this.obj = obj;
	}
	public MyRunnable(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void run(){
	//	this.obj.add();
		for(int i=x; i<=y; i++)
			this.sum+=i;
	}
}

