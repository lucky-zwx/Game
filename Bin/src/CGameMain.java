import java.util.Date;

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
  
  public void OnMouseClick(int iMouseType, float fMouseX, float fMouseY) {
	  System.out.println("x:"+fMouseX+" y:"+fMouseY);
  }
  
  public void OnMouseUp(int iMouseType, float fMouseX, float fMouseY) {}

  //按键按下后的动作
  public void OnKeyDown(int iKey, boolean bAltPress, boolean bShiftPress, boolean bCtrlPress)
  {
	  //如果是第一关，执行相应的按键
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
	    	map_first.jumptime = new Date().getTime();
	        map_first.JSjump0.SetSpriteVisible(true);
	        map_first.JSA0.SetSpriteVisible(false);
	        if(map_first.jump == 0) {
	        	map_first.setWhereX_A0(map_first.JSA0.GetSpritePositionX());
	        	map_first.setWhereY_A0(map_first.JSA0.GetSpritePositionY());
	        }
	        map_first.JSA0.SpriteMoveTo(map_first.getWhereX_A0(), (float)(map_first.getWhereY_A0() - 8.0D), 60.0F, true);
	        map_first.jump = 1;
	    }
	  }
	  
  }

  
  public void OnKeyUp(int iKey)
  {
	  //在第一关执行的按键判断
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
	//如果为第一关
	if(mapstate == 1) {
		//如果触碰到左右阻挡物
		if (szSrcName.indexOf("left")!=-1 || szSrcName.indexOf("right")!=-1){
			map_first.JSA0.SetSpriteLinearVelocityX(0);
			map_first.JSA0.SpriteMoveTo((float) (map_first.JSA0.GetSpritePositionX()-0.01), (float)(map_first.JSA0.GetSpritePositionY()), 100, true);
		}
		//如果主角触碰到下方阻碍物
		if (szSrcName.indexOf("down")!=-1) {
			map_first.JSA0.SetSpriteLinearVelocityY(0);
			map_first.JSA0.SpriteMoveTo(map_first.JSA0.GetSpritePositionX(), (float)(map_first.JSA0.GetSpritePositionY()-0.01), 100, true);		
		}
		//如果触碰到陷阱就宣布游戏结束
		if (szSrcName.indexOf("Atrap")!=-1) {
			map_first.GameEnd();
			System.out.println("主角因为触碰到陷阱死亡");
		}
		//如果触碰到人质就删除人质，并且人质数减一
	    if (szSrcName.equals("A1"))						
	    {
	      map_first.Ihelpman -= 1;
	      map_first.JSA1.SetSpriteEnable(false);
	      map_first.JSA1.DeleteSprite();
	    }
	    //如果触碰到人质就删除人质，并且人质数减一
	    if (szSrcName.equals("A2"))						
	    {
	      map_first.Ihelpman -= 1;
	      map_first.JSA2.SetSpriteEnable(false);
	      map_first.JSA2.DeleteSprite();
	    }
	
	}
    
  }
  
  public void OnSpriteColWorldLimit(String szName, int iColSide) {}
  
}
