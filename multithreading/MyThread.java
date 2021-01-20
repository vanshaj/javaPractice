class Print10Thread{
	public static void main(String[] args){
		for(int i=0; i<10; i++){
			new Thread("Thread"+i){
				public void run(){
					System.out.println("Thread name:"+Thread.currentThread().getName());	
					}
				}.start();
			}
		}
	}
