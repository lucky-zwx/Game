import FunCode.JAnimateSprite;
import java.util.Date;
import FunCode.JSprite;
import java.util.ArrayList;

public class Map1 extends Game
{
  Date date = new Date();
  public JSprite JSA1;	//��һ������
  public JSprite JSA2;	//�ڶ�������
  public JAnimateSprite JSA0;	//����
  public JSprite JStrap0;	//��һ������
  public JSprite JStrap1;	//�ڶ�������
  public JSprite mapdown;	//����ŵ��赲��
  public JSprite Text_gameover;	//��Ϸ������ʶ
  public  int Ihelpman = 2;	//��������
  public float Time = 0.0F;	//�����������Ϸʱ��
  public JSprite JSjump0;	//��һ����Ծ����
  public JSprite JSjump1;	//�ڶ�����Ծ����
  public JSprite JSleft0;	//�����赲��
  public JSprite JSleft1;	//�����赲��
  public ArrayList<JSprite> JSmapdown;	//�洢����Ŀռ�
  public int jump = 0;
  public long jumptime;
  public int tmp = 0;
  public float whereX_A0 = 0;
  public float whereY_A0 = 0;
  public long time1 = date.getTime();
  public long time2;
  
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
	  	JSmapdown = new ArrayList<JSprite>();
	    JSA0 = new JAnimateSprite("A0");
	    JSA0.SetSpriteMass(10);
	    JSA1 = new JSprite("A1");
	    JSA2 = new JSprite("A2");
	    JStrap0 = new JSprite("trap0");
	    JStrap1 = new JSprite("trap1");
	    
	    for (int i = 0; i < 10; i++)
	    {
	    	if (i>0) {
	    		if (i == 7 || i == 9) {
	    			mapdown = new JSprite("map_down" + i);
	    			JSmapdown.add(mapdown);
	    		}else {
		      mapdown = new JSprite("map_down" + i);
		      mapdown.CloneSprite("map_down0");
		      JSmapdown.add(mapdown);
		      }
	      }else {
		      mapdown = new JSprite("map_down" + i);
	    	  JSmapdown.add(mapdown);
	      }
	    	JSmapdown.get(i).SetSpriteCollisionSend(true);
	    	}

	        
	    Text_gameover = new JSprite("Gameover");
	    JSjump0 = new JSprite("jump0");
	    JSjump1 = new JSprite("jump1");
	    JSleft0 = new JSprite("map_left0");
	    JSleft1 = new JSprite("map_left1");
	    
	    JSA0.SetSpritePosition((float)-37.360, (float)11.898);
	    JSA0.SetSpriteCollisionResponse(EnumDefine.COL_RESPONSE_STICKY);
	    JSA1.SetSpritePosition((float)46.506, (float)-25.641);
	    JSA2.SetSpritePosition((float)-9.726, (float)11.797);
	    double x = -34.444;
	    double x2 = 0.567;
	    double y = 14.433;
	    double y2 = 5.567;
	    for (int i=0;i<10;i++) {
	    	if (i<6) {
	    	JSmapdown.get(i).SetSpritePosition((float)x, (float)y);
	    	x+=5;
	    	}
	    	else {
	    		JSmapdown.get(i).SetSpritePosition((float)x2, (float)y2);
	    		x2=x2+10;
	    		y2=y2-5;
	    	}
	    //	System.out.println(i+".x="+JSmapdown.get(i).GetSpritePositionX());
	    //	System.out.println(i+".y="+JSmapdown.get(i).GetSpritePositionY());
	    }
	    JStrap0.SetSpritePosition((float)30.302, (float)-39.458);
	    JStrap1.SetSpritePosition((float)-8.848, (float)12.714);
	    Text_gameover.SetSpritePosition((float)1.520, (float)2.098);
	    JSleft0.SetSpritePosition((float)-12.368, (float)11.688);
	    JSleft1.SetSpritePosition((float)-6.326, (float)11.688);
	    
  }
  
  public Map1()
  { 
    Mapbegin();
  }
  
  public void GameEnd()
  {
    Text_gameover.SetSpriteVisible(true);
    JSA0.DeleteSprite();
    JSA1.DeleteSprite();
    JSA2.DeleteSprite();
    JSjump0.DeleteSprite();
    JSjump1.DeleteSprite();
    for(int i=0;i<10;i++) {
    	JSmapdown.get(i).DeleteSprite();
    }
    JStrap0.DeleteSprite();
    JStrap1.DeleteSprite();
    JSleft0.DeleteSprite();
    JSleft1.DeleteSprite();
  }
  
  
  public void jump()
  {
	  if (CGameMain.g_GameMain.mapstate == 1) {
		  CGameMain.g_GameMain.map_first.JSA0.SpriteMoveTo(CGameMain.g_GameMain.map_first.getWhereX_A0(), (float)(CGameMain.g_GameMain.map_first.getWhereY_A0() - 8.0D), 60.0F, true);
		  CGameMain.g_GameMain.map_first.jump = 1;
	  }
  }
  
  
public void Map1_run()
  {
	if(CGameMain.g_GameMain.map_first.jump==1) {
		if ((new Date().getTime()-jumptime)/100 > 3) {
			if(JSA0.GetSpritePositionX() < 12.8)
			JSA0.SpriteMoveTo(JSA0.GetSpritePositionX(), (float)11.898, 30, true);
		}
	}
    JSA1.SpriteRotateTo(-80.0F, 35.0F, true);			//���õ�һ�����ʵ�ҡ�ڶ���
    if (JSA1.GetSpriteRotation() + 0.1D >= 80.0D) {
      JSA1.SpriteRotateTo(60.0F, 35.0F, true);
    }
    
    JStrap0.SpriteMoveTo(30.618F, -3.458F, 10.0F, true);		//���õ�һ��������ƶ�
    if (JStrap0.GetSpritePositionY() + 0.1D >= -3.458D) {
      JStrap0.SetSpritePosition(30.618F, -40.F);
    }
    
    
    time2 = (new Date().getTime()-time1)/1000;
    if (time2%10>0 && time2%10<5) {				//���õ粨�ĳ���,�Լ��谭��ĳ���
      JStrap1.SetSpriteEnable(false);
      JSmapdown.get(7).SetSpriteEnable(true);
      JSmapdown.get(9).SetSpriteEnable(false);
    } else {
      JStrap1.SetSpriteEnable(true);
      JSmapdown.get(7).SetSpriteEnable(false);
      JSmapdown.get(9).SetSpriteEnable(true);
    }
    
    /*
     * ��Ծ�߶�����
     */
    if (JSA0.GetSpritePositionX() > -38 && JSA0.GetSpritePositionX() < -6.326) {							//������������߾;ͽ�����Ӧ�ĸ߶ȿ���
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-5, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > -6.326 && JSA0.GetSpritePositionX() < 10.567) {							//������������߾;ͽ�����Ӧ�ĸ߶ȿ���
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-20, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > 10.567 && JSA0.GetSpritePositionX() < 20.567) {							//������������߾;ͽ�����Ӧ�ĸ߶ȿ���
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-30, (float)50, (float)50);
    }
    
    if (JSA0.GetSpritePositionX() > 20.567) {																//������������߾;ͽ�����Ӧ�ĸ߶ȿ���
    	JSA0.SetSpriteWorldLimit(3, (float)-50, (float)-50, (float)50, (float)50);
    }
    
    //ƽ������
    if (JSA0.GetSpritePositionY() > 12 || JSA0.GetSpritePositionX() < -38) {
    	GameEnd();
    	CGameMain.g_GameMain.m_iGameState=3;
    }
    
  }
}
