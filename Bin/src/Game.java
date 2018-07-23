import FunCode.JSystem;

public class Game
{
  public static void main(String[] args)
  {
    EngineInterface g_EngineInterface = new EngineInterface();
    if (!JSystem.InitGameEngine(g_EngineInterface, args)) {
      return;
    }
    MusicPlayer player = new MusicPlayer("bin/1969.wav");
    player.start(true);
    
    JSystem.SetWindowTitle("»ð²ñÈË");
    while (JSystem.EngineMainLoop())
    {
      float fTimeDelta = JSystem.GetTimeDelta();
      
      CGameMain.g_GameMain.GameMainLoop(fTimeDelta);
    }
    JSystem.ShutdownGameEngine();
    player.Stopmusic();
  }
}
