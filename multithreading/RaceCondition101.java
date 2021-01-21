class RaceCondition101{
	public static void main(String[] args){
			//SyncTwoSums two = new SyncTwoSums();
			TwoSums two = new TwoSums();
			
			MyRunnable my1 = new MyRunnable(two,1,100000);
			MyRunnable my2 = new MyRunnable(two,1,100000); // as we have passed same object to runnable both threads will have different reference variables in their thread stack but each 
															// reference variable will point to same object in heap
			//MyRunnable my1 = new MyRunnable(1,100000); // No Sharing of resources
			//MyRunnable my2 = new MyRunnable(1,100000); // NO sharing
			Thread myThread1 = new Thread(my1, "MyThread");
			Thread myThread2 = new Thread(my2, "MyThread2");
			myThread1.start();
			myThread2.start();
			try{
			myThread1.join();
			myThread2.join();

			}
			catch(Exception ex){
				System.out.println("Exception caught"+ex);
			}
		}
	}

/*
 * Non thread safe implementation 
 * CRITICAL section is susceptible to Race condition
 */

class TwoSums{
	 long sum2=0;

	public void add(long val1, long val2){
		for( long  i=val1; i<=val2; i++){
			this.sum2++;					//CRITICAL SECTION
			}
		System.out.println("Async"+this.sum2);
	}
	
	public long addition(long val1, long val2){
		for( long  i=val1; i<=val2; i++){
			this.sum2++;					
			}
		return this.sum2;
	}

}

/*
 * Thread safe implementation of the read modify write condition
 * Added atomicity only one thread can enter CRITICAL SECTION at a time
 * Hence maintaining sync
 */

class SyncTwoSums{ 
	long sum2=0;
	
	public void add(long x, long y){
		for(long i = x; i<=y; i++){
			synchronized (this) {
				this.sum2++;		//CRITICAL SECTION
			}
		}
		System.out.println("Sync"+this.sum2);
	}
	
}

class MyRunnable implements Runnable{
	SyncTwoSums objS; // Each Thread will have their separate Reference variable for SyncTwoSums in respective ThreadStack
	TwoSums obj;
	long x,y;
	
	public MyRunnable(SyncTwoSums obj,long x, long y){ 	// USES THREAD SAFE CLASS
		this.objS = obj; //Here the reference variable will point to the object in heap
		this.x = x;
		this.y = y;
	}

	public MyRunnable(TwoSums obj, long x, long y){  // USES NON THREAD SAFE CLASS
		this.obj = obj;
		this.x = x;
		this.y = y;
	}
	
	public MyRunnable(long x, long y){ // WILL MAKE NON THREAD SAFE CLASS THREAD SAFE BY MEMORY MANAGEMENT
		this.obj = new TwoSums(); // HERE A NEW OBJECT WILL BE CREATED FOR EACH SEPARATE THREAD AND NOTHING WILL BE SHARED BETWEEN THREADS
		this.x = x;
		this.y = y;
	}
	
	public void run(){
			System.out.println(this.obj.addition(this.x,this.y));
	}
}

