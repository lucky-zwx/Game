import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer
{
  private String musicPath;
  private volatile boolean run = true;
  private Thread mainThread;
  private AudioInputStream audioStream;
  private AudioFormat audioFormat;
  private SourceDataLine sourceDataLine;
  
  @SuppressWarnings("deprecation")
public void Stopmusic()
  {
    this.mainThread.stop();
  }
  
  public MusicPlayer(String musicPath)
  {
    this.musicPath = musicPath;
    prefetch();
  }
  
  private void prefetch()
  {
    try
    {
      this.audioStream = AudioSystem.getAudioInputStream(new File(this.musicPath));
      
      this.audioFormat = this.audioStream.getFormat();
      
      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, 
        this.audioFormat, -1);
      
      this.sourceDataLine = ((SourceDataLine)AudioSystem.getLine(dataLineInfo));
      
      this.sourceDataLine.open(this.audioFormat);
      this.sourceDataLine.start();
    }
    catch (UnsupportedAudioFileException ex)
    {
      ex.printStackTrace();
    }
    catch (LineUnavailableException ex)
    {
      ex.printStackTrace();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  
  protected void finalize()
    throws Throwable
  {
    super.finalize();
    this.sourceDataLine.drain();
    this.sourceDataLine.close();
    this.audioStream.close();
  }
  
  private void playMusic(boolean loop)
    throws InterruptedException
  {
    try
    {
      if (loop) {
        for (;;)
        {
          playMusic();
        }
      }
      playMusic();
      
      this.sourceDataLine.drain();
      this.sourceDataLine.close();
      this.audioStream.close();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  
  private void playMusic()
  {
    try
    {
      synchronized (this)
      {
        this.run = true;
      }
      this.audioStream = AudioSystem.getAudioInputStream(new File(this.musicPath));
      
      byte[] tempBuff = new byte['Ѐ'];
      int count;
      while ((count = this.audioStream.read(tempBuff, 0, tempBuff.length)) != -1)
      {
//        int count;
        synchronized (this)
        {
          while (!this.run) {
            wait();
          }
        }
        this.sourceDataLine.write(tempBuff, 0, count);
      }
    }
    catch (UnsupportedAudioFileException ex)
    {
      ex.printStackTrace();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
    catch (InterruptedException ex)
    {
      ex.printStackTrace();
    }
  }
  
  private void stopMusic()
  {
    synchronized (this)
    {
      this.run = false;
      notifyAll();
    }
  }
  
  private void continueMusic()
  {
    synchronized (this)
    {
      this.run = true;
      notifyAll();
    }
  }
  
  public void start(final boolean loop)
  {
    this.mainThread = new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          MusicPlayer.this.playMusic(loop);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    });
    this.mainThread.start();
  }
  
  public void stop()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        MusicPlayer.this.stopMusic();
      }
    })
    
      .start();
  }
  
  public void continues()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        MusicPlayer.this.continueMusic();
      }
    })
    
      .start();
  }
}
