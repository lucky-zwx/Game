import FunCode.JAnimateSprite;
import java.util.Date;
import FunCode.JSprite;

public class Map1 extends Game
{
  Date date = new Date();
  public JSprite JSA1;	//��һ������
  public JSprite JSA2;	//�ڶ�������
  public JAnimateSprite JSA0;	//����
  public JSprite JStrap0;	//��һ������
  public JSprite JStrap1;	//�ڶ�������
  public JSprite JSdowntrap1;	//��һ����ש��ʧ����
  public JSprite JSdowntrap2;	//�ڶ����שСʱ����
  public JSprite mapdown;	//����ŵ��赲��
  public JSprite Text_gameover;	//��Ϸ������ʶ
  public  int Ihelpman = 2;	//��������
  public float Time = 0.0F;	//�����������Ϸʱ��
  public JSprite JSjump0;	//��һ����Ծ����
  public JSprite JSjump1;	//�ڶ�����Ծ����
  public JSprite JSleft0;	//�����赲��
  public JSprite JSleft1;	//�����赲��
  public int jump = 0;	//��Ծ״̬
  public long jumptime;	//��Ծʱ��
  public float whereX_A0 = 0;	//���ǵ�xλ��
  public float whereY_A0 = 0;	//���ǵ�Y��λ��
  public long time1 = date.getTime();	//������Ϸ�Ŀ�ʼʱ��
  public long time2;	//���̶�ʱ�����������ʱ�����
  
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
	  	//��ʼ����ɫ
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
	    //�������ǵ����練��ģʽ
	    JSA0.SetSpriteCollisionResponse(EnumDefine.COL_RESPONSE_STICKY); 
  }
  
  public Map1()
  { 
    Mapbegin();
  }
  
  public void GameEnd()
  {
	  //��Ϸʧ����ʾGameover
	  Text_gameover.SetSpriteVisible(true);
  }
  
  
  
public void Map1_run()
  {
	
	//������Ծʱ��
	if(CGameMain.g_GameMain.map_first.jump==1) {
		if ((new Date().getTime()-jumptime)/100 > 3) {
			if(JSA0.GetSpritePositionX() < 12.8)
			JSA0.SpriteMoveTo(JSA0.GetSpritePositionX(), (float)12.850, 30, true);
		}
	}
	
	//���õ�һ�����ʵ�ҡ�ڶ���
    JSA1.SpriteRotateTo(-80.0F, 35.0F, true);			
    if (JSA1.GetSpriteRotation() + 0.1D >= 80.0D) {
      JSA1.SpriteRotateTo(60.0F, 35.0F, true);
    }
    
    //���õ�һ��������ƶ�
    JStrap0.SpriteMoveTo(30.618F, -3.458F, 10.0F, true);		
    if (JStrap0.GetSpritePositionY() + 0.1D >= -3.458D) {
      JStrap0.SetSpritePosition(30.618F, -40.F);
    }
    
    //���õ粨�ĳ���,�Լ��谭��ĳ���
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
     * ��Ծ�߶�����
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
    
    //ƽ������
    if (JSA0.GetSpritePositionY() > 14 || JSA0.GetSpritePositionX() < -43) {
    	System.out.println("��ƽ����������������");
    	GameEnd();
    	CGameMain.g_GameMain.m_iGameState=3;
    }
    
  }
}
