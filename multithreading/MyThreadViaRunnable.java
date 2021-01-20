class Print10Thread{
	public static void main(String[] args){
		for(int i=0; i<10; i++){
			Runnable runn = new MyRunnable();
			Thread myThread = new Thread(runn, ""+i);
			myThread.start();
			}
		}
	}

class MyRunnable implements Runnable{
	public void run(){
		System.out.println("Thread: "+Thread.currentThread().getName());
	}
}

