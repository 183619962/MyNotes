package com.lpf.mynotes.net;

public interface FutureI
{
	public String get();
	
	public String get(Long timeout);
	
	public void recall(String rsp);

}
