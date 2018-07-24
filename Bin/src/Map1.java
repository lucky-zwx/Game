import FunCode.JAnimateSprite;
import java.util.Date;
import FunCode.JSprite;

import java.util.ArrayList;

public class Map1 extends Game
{
  Date date = new Date();
  public JSprite JSA1;	//第一个人质
  public JSprite JSA2;	//第二个人质
  public JAnimateSprite JSA0;	//主角
  public JSprite JStrap0;	//第一个陷阱
  public JSprite JStrap1;	//第二个陷阱
  public JSprite JSdowntrap1;	//第一个地砖消失陷阱
  public JSprite JSdowntrap2;	//第二哥地砖小时陷阱
  public JSprite mapdown;	//横放着的阻挡物
  public JSprite Text_gameover;	//游戏结束标识
  public  int Ihelpman = 2;	//人质数量
  public float Time = 0.0F;	//用于陷阱的游戏时间
  public JSprite JSjump0;	//第一个跳跃动作
  public JSprite JSjump1;	//第二个跳跃动作
  public JSprite JSleft0;	//左右阻挡物
  public JSprite JSleft1;	//左右阻挡物
  public int jump = 0;	//跳跃状态
  public long jumptime;	//跳跃时间
  public float whereX_A0 = 0;	//主角的x位置
  public float whereY_A0 = 0;	//主角的Y的位置
  public long time1 = date.getTime();	//保存游戏的开始时间
  public long time2;	//不固定时间用于陷阱的时间计算
  
  public float getWhereX_A0() {
	return whereX_A0;
}

public void setWhereX_A0(float whereX_A0) {
	this.whereX_A0 = whereX_A0;
}

public float getWhereY_A0() {
	return whereY_A0;
}

public void setWhereY_A0(float whereY_A0) {
	this.whereY_A0 = whereY_A0;
}


  public void Mapbegin() {
	    JSA0 = new JAnimateSprite("A0");
	    JSA1 = new JSprite("A1");
	    JSA2 = new JSprite("A2");
	    JStrap0 = new JSprite("Atrap0");
	    JStrap1 = new JSprite("Atrap1");     
	    Text_gameover = new JSprite("AGameover");
	    JSjump0 = new JSprite("Ajump0");
	    JSjump1 = new JSprite("Ajump1");
	    JSleft0 = new JSprite("Amap_left");
	    JSleft1 = new JSprite("Amap_right");
	    mapdown = new JSprite("Amap_down");
	    //设置主角的世界反弹模式
	    JSA0.SetSpriteCollisionResponse(EnumDefine.COL_RESPONSE_STICKY);

	    
  }
  
  public Map1()
  { 
    Mapbegin();
  }
  
  public void GameEnd()
  {
	  Text_gameover.SetSpriteVisible(true);
  }
  
  
  
public void Map1_run()
  {
	if(CGameMain.g_GameMain.map_first.jump==1) {
		if ((new Date().getTime()-jumptime)/100 > 3) {
			if(JSA0.GetSpritePositionX() < 12.8)
			JSA0.SpriteMoveTo(JSA0.GetSpritePositionX(), (float)11.898, 30, true);
		}
	}
    JSA1.SpriteRotateTo(-80.0F, 35.0F, true);			//设置第一个人质的摇摆动作
    if (JSA1.GetSpriteRotation() + 0.1D >= 80.0D) {
      JSA1.SpriteRotateTo(60.0F, 35.0F, true);
    }
    
    JStrap0.SpriteMoveTo(30.618F, -3.458F, 10.0F, true);		//设置第一个陷阱的移动
    if (JStrap0.GetSpritePositionY() + 0.1D >= -3.458D) {
      JStrap0.SetSpritePosition(30.618F, -40.F);
    }
    
    
    time2 = (new Date().getTime()-time1)/1000;
    if (time2%10>0 && time2%10<5) {				//设置电波的出现,以及阻碍物的出现
      JStrap1.SetSpriteEnable(false);
//      JSmapdown.get(7).SetSpriteEnable(true);
//      JSmapdown.get(9).SetSpriteEnable(false);
    } else {
      JStrap1.SetSpriteEnable(true);
//      JSmapdown.get(7).SetSpriteEnable(false);
//      JSmapdown.get(9).SetSpriteEnable(true);
    }
    
    /*
     * 跳跃高度限制
     */
    if (JSA0.GetSpritePositionX() > -38 && JSA0.GetSpritePositionX() < -6.326) {							//如果主角在左半边就就进行相应的高度控制
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-5, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > -6.326 && JSA0.GetSpritePositionX() < 10.567) {							//如果主角在左半边就就进行相应的高度控制
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-20, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > 10.567 && JSA0.GetSpritePositionX() < 20.567) {							//如果主角在左半边就就进行相应的高度控制
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-30, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > 20.567) {																//如果主角在左半边就就进行相应的高度控制
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-50, (float)50, (float)50);
    }
    
    //平地限制
    if (JSA0.GetSpritePositionY() > 14 || JSA0.GetSpritePositionX() < -43) {
//    	GameEnd();
//    	CGameMain.g_GameMain.m_iGameState=3;
    }
    
  }
}
