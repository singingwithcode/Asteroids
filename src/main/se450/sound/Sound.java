package main.se450.sound;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import main.se450.factories.NamedThreadFactory;
import main.se450.interfaces.ISound;

public class Sound implements LineListener, ISound, Runnable
{
	private String fileName = "";
    private Clip   clip     = null;
    private File   file     = null;
    
    private CountDownLatch latch = null;
    private volatile boolean playSound = false;
    
    public Sound(String soundFile)
	{
		fileName = soundFile;
	    
        file = new File(fileName);

        try
	    {
	        clip = AudioSystem.getClip();
	        clip.addLineListener(this);	        
	        clip.open(AudioSystem.getAudioInputStream(file));
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
    }
	
    public synchronized void play()
    {
    	if (playSound == false)
    	{
    		playSound = true;
    		
	    	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory(fileName));
			if (scheduler != null)
			{
				scheduler.schedule(this, 0, TimeUnit.MILLISECONDS);
			}
    	}
    }
	
	@Override
	public void update(LineEvent myLineEvent) 
	{
		if (myLineEvent.getType() == LineEvent.Type.STOP)
		{
			latch.countDown();
		}
    }

	@Override
	public void run() 
	{
		if (playSound)
		{
	        try
		    {
	        	latch = new CountDownLatch(1);
	        	clip.setFramePosition(0);
	        	clip.setMicrosecondPosition(0);
				clip.start();
				latch.await();
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
	        playSound = false;
		}
    }
}
