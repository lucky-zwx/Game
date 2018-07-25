import FunCode.JAnimateSprite;
import java.util.Date;
import FunCode.JSprite;

public class Map1 extends Game
{
  Date date = new Date();
  public JSprite JSA1;	//第一个人质
  public JSprite JSA2;	//第二个人质
  public JAnimateSprite JSA0;	//主角
  public JSprite JStrap0;	//第一个陷阱
  public JSprite JStrap1;	//第二个陷阱
  public JSprite JSdowntrap1;	//第一个地砖消失陷阱
  public JSprite JSdowntrap2;	//第二个地砖消失陷阱
  public JSprite mapdown;	//横放着的阻挡物
  public JSprite mapdown1;	//横放着的阻挡物
  public JSprite mapdown2;	//横放着的阻挡物
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
	  	//初始化角色
	    JSA0 = new JAnimateSprite("A0");
	    JSA1 = new JSprite("A1");
	    JSA2 = new JSprite("A2");
	    JStrap0 = new JSprite("Atrap0");
	    JStrap1 = new JSprite("Atrap1");    
	    JSdowntrap1 = new JSprite("Amap_downtrap1");
	    JSdowntrap2 = new JSprite("Amap_downtrap2");
	    Text_gameover = new JSprite("AGameover");
	    JSjump0 = new JSprite("Ajump0");
	    JSjump1 = new JSprite("Ajump1");
	    JSleft0 = new JSprite("Amap_left");
	    JSleft1 = new JSprite("Amap_right");
	    mapdown = new JSprite("Amap_down");
	    mapdown1 = new JSprite("Amap_down1");
	    mapdown2 = new JSprite("Amap_down2");
	    //设置主角的世界反弹模式
	    JSA0.SetSpriteCollisionResponse(EnumDefine.COL_RESPONSE_STICKY); 
  }
  
  public Map1()
  { 
    Mapbegin();
  }
  
  public void GameEnd()
  {
	  	//游戏失败显示Gameover
	  	Text_gameover.SetSpriteVisible(true);
	    JSA0.SetSpriteEnable(false);
	    JSA1.SetSpriteEnable(false);
	    JSA2.SetSpriteEnable(false);
	    JStrap0.SetSpriteEnable(false);
	    JStrap1.SetSpriteEnable(false);
	    JSdowntrap1.SetSpriteEnable(false);
	    JSdowntrap2.SetSpriteEnable(false);
	    JSjump0.SetSpriteEnable(false);
	    JSjump1.SetSpriteEnable(false);
	    JSleft0.SetSpriteEnable(false);
	    JSleft1.SetSpriteEnable(false);
	    mapdown.SetSpriteEnable(false);
	    mapdown1.SetSpriteEnable(false);
	    mapdown2.SetSpriteEnable(false);
	    CGameMain.g_GameMain.m_iGameState = 3;
  }
  
  
  
public void Map1_run()
  {
	
	//控制跳跃时间
	if(CGameMain.g_GameMain.map_first.jump==1) {
		if ((new Date().getTime()-jumptime)/100 > 3) {
			if(JSA0.GetSpritePositionX() < 12.8)
			JSA0.SpriteMoveTo(JSA0.GetSpritePositionX(), (float)12.850, 30, true);
		}
	}
	
	//设置第一个人质的摇摆动作
    JSA1.SpriteRotateTo(-80.0F, 35.0F, true);			
    if (JSA1.GetSpriteRotation() + 0.1D >= 80.0D) {
      JSA1.SpriteRotateTo(60.0F, 35.0F, true);
    }
    
    //设置第一个陷阱的移动
    JStrap0.SpriteMoveTo(30.618F, -3.458F, 10.0F, true);		
    if (JStrap0.GetSpritePositionY() + 0.1D >= -3.458D) {
      JStrap0.SetSpritePosition(30.618F, -40.F);
    }
    
    //设置电波的出现,以及阻碍物的出现
    time2 = (new Date().getTime()-time1)/1000;
    if (time2%10>0 && time2%10<5) {				
      JStrap1.SetSpriteEnable(false);
      JSdowntrap1.SetSpriteEnable(true);
      JSdowntrap2.SetSpriteEnable(false);
    } else {
      JStrap1.SetSpriteEnable(true);
      JSdowntrap1.SetSpriteEnable(false);
      JSdowntrap2.SetSpriteEnable(true);
    }
    
    /*
     * 跳跃高度限制
     */
    if (JSA0.GetSpritePositionX() > -43 && JSA0.GetSpritePositionX() < -11) {
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-2, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > -11 && JSA0.GetSpritePositionX() < -2) {
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-8, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > -2 && JSA0.GetSpritePositionX() < 11) {
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-15, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > 11 && JSA0.GetSpritePositionX() < 19) {
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-21, (float)50, (float)50);
    }
    
    
    if (JSA0.GetSpritePositionX() > 19 && JSA0.GetSpritePositionX() < 28) {
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-25, (float)50, (float)50);
    }
    
    //平地限制
    if (JSA0.GetSpritePositionY() > 14 || JSA0.GetSpritePositionX() < -43) {
    	System.out.println("因平地限制死亡！！！");
    	GameEnd();
    }
    
  }
}
