package de.bdh.ks;


public class KSTimer implements Runnable {
	long time = 0;
	int timer = 1000*60*60;
	Main m;
	
	public KSTimer(Main m)
    {
		this.m = m;
    }
	
	public void run()
	{
		try
		{
			if (Math.abs(System.currentTimeMillis() - time) < this.timer)
			{
				return;
			}
			
			time = System.currentTimeMillis();
			this.m.Prune();
		} catch (Exception e) {}
		
	}
}
