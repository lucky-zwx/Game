import FunCode.JAnimateSprite;
import FunCode.JSprite;
import FunCode.JSystem;

import java.util.Date;

import FunCode.EngineAPI;

public class CGameMain
{
  public int mapstate = 1;		//关卡
  public int m_iGameState = 0;
  Map1 map_first = new Map1();
  public static CGameMain g_GameMain = new CGameMain();
  
  public CGameMain()
  {
    m_iGameState = 1;
  }
  
  public void GameMainLoop(float fDeltaTime)
  {
    switch (m_iGameState)
    {
    case 1:
      GameInit();
      m_iGameState = 2;
      
      break;
    case 2: 
      if (m_iGameState == 2) {
        GameRun(fDeltaTime);
      }
      break;
    case 3: 
      GameEnd();
      break;
    }
  }
  
  public void GameInit() {}
  
  public void GameRun(float fDeltaTime)
  {
    switch (mapstate)
    {
    case 1: 
      map_first.Map1_run();
      break;
    }
  }
  
  public void GameEnd()
  {
  }
  
  public void OnMouseMove(float fMouseX, float fMouseY) {}
  
  public void OnMouseClick(int iMouseType, float fMouseX, float fMouseY) {}
  
  public void OnMouseUp(int iMouseType, float fMouseX, float fMouseY) {}
  
  public void OnKeyDown(int iKey, boolean bAltPress, boolean bShiftPress, boolean bCtrlPress)		//按键按下后的动作
  {
	  if(mapstate == 1) {
	    if (iKey == EnumDefine.KEY_D)
	    {
	      map_first.JSA0.SetSpriteLinearVelocityX(25.0F);
	      map_first.JSA0.SetSpriteFlipX(false);
	      map_first.JSjump0.SetSpriteFlipX(false);
	    }
	    if (iKey == EnumDefine.KEY_A)
	    {
	      map_first.JSA0.SetSpriteLinearVelocityX(-25.0F);
	      map_first.JSA0.SetSpriteFlipX(true);
	      map_first.JSjump0.SetSpriteFlipX(true);
	  	}
	    if (iKey == EnumDefine.KEY_W)
	    {
//	    	System.out.println(new Date().getTime()-map_first.time1);
	    	map_first.jumptime = new Date().getTime();
	        map_first.JSjump0.SetSpriteVisible(true);
	        map_first.JSA0.SetSpriteVisible(false);
	        if(map_first.jump == 0) {
	        	map_first.setWhereX_A0(map_first.JSA0.GetSpritePositionX());
	        	map_first.setWhereY_A0(map_first.JSA0.GetSpritePositionY());
	        }
	        map_first.jump();
	    }
	    if (iKey == EnumDefine.KEY_R && m_iGameState==3) {
	    	map_first.Text_gameover.SetSpriteVisible(false);
	    	map_first = new Map1();
	    	map_first.Mapbegin();
	    }
	  }
  }

  
  public void OnKeyUp(int iKey)
  {
	  if (mapstate == 1) {
	    if ((iKey == EnumDefine.KEY_A) || (iKey == EnumDefine.KEY_D)) {
	      map_first.JSA0.SetSpriteLinearVelocityX(0.0F);
	    }
	    if (iKey == EnumDefine.KEY_W)
	    {
	        map_first.JSjump0.SetSpriteVisible(false);
	        map_first.JSA0.SetSpriteVisible(true);
	        map_first.jump = 0;
	        map_first.JSA0.SetSpriteLinearVelocityY(30);
	    }
	  }
  }
  
  public void OnSpriteColSprite(String szSrcName, String szTarName)
  {
	  
    for (int i = 0; i < 10; i++) {											//如果主角碰触到下方的阻碍物就将Y轴速度设为0
      if ((szTarName.equals("A0")) && (szSrcName.equals("map_down" + i)))
      {
        map_first.JSA0.SetSpritePositionY(map_first.JSA0.GetSpritePositionY() - 0.001F);
        map_first.JSA0.SetSpriteLinearVelocityY(0);
      }
    }
    
    for (int i = 0; i < 10; i++) {											//如果主角碰触到左右两边的阻碍物就将X轴速度设为0
      if ((szTarName.equals("A0")) && (szSrcName.equals("map_left" + i)))
      {
        if (!map_first.JSA0.GetSpriteFlipX()) {
          map_first.JSA0.SetSpritePositionX(map_first.JSA0.GetSpritePositionX() - 0.001F);
        } else {
          map_first.JSA0.SetSpritePositionX(map_first.JSA0.GetSpritePositionX() + 0.001F);
        }
        map_first.JSA0.SetSpriteLinearVelocityX(0);
      }
    }
    
    if (szSrcName.equals("A1"))						//如果触碰到人质就删除人质，并且人质数减一
    {
      map_first.Ihelpman -= 1;
      map_first.JSA1.SetSpriteEnable(false);
      map_first.JSA1.DeleteSprite();
    }
    
    if (szSrcName.equals("A2"))						//如果触碰到人质就删除人质，并且人质数减一
    {
      map_first.Ihelpman -= 1;
      map_first.JSA2.SetSpriteEnable(false);
      map_first.JSA2.DeleteSprite();
    }
    
    for (int i = 0; i < 2; i++) {
      if (szSrcName.equals("trap" + i)) {
        map_first.GameEnd();
      }
    }
  }
  
  public void OnSpriteColWorldLimit(String szName, int iColSide) {}
  
}
