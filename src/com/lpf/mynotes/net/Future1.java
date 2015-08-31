package com.lpf.mynotes.net;

public class Future1 implements FutureI
{
	private byte[] param;
	
	private String rsp;
	
	TimeClient task;
	
	private Thread currentThread;
	
	private boolean block = false;

	public Future1(byte[] param)
	{
		this.param = param;
		
		task = new TimeClient();
		
		exec(task);
	}
	
	//
	public void exec(TimeClient task)
	{
		try
		{
			task.connect(10004, "203.86.1.141",this, param);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Thread thread = new Thread(task);
		//thread.start();
	}
	
	public String get()
	{
		if(rsp == null)
		{
			block = true;
			
			currentThread = Thread.currentThread();
			
			synchronized(currentThread)
			{
				try
				{
					currentThread.wait();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

			
		}
		return rsp;

	}
	
	
	public String get(Long timeout)
	{
		if(rsp == null)
		{
			block = true;
			
			currentThread = Thread.currentThread();
			
			synchronized(currentThread)
			{
				try
				{
					currentThread.wait(timeout);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

			
		}
		return rsp;

	}
	
	public void recall(String rsp)
	{
		task = null;
		
		this.rsp = rsp;
		
		if(block)
		{
			synchronized (currentThread)
			{
				currentThread.notify();
			}
		}
	}
}
