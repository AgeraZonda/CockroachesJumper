package cockroach;

		//class tạo sự kiện bàn phím 

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

    private boolean[] keys;
    private int isPress = 0;
    private Handler handler;
	private int IsCount=0;
	private double time=0;
	
	public KeyBoard(Handler handler)
	{
		this.handler=handler;
		keys = new boolean[KeyEvent.KEY_LAST];
        keyPressedTime = new double[KeyEvent.KEY_LAST];
	}
    private double[] keyPressedTime;
    private double newTime=0;
 

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

       if(isPress==0)
    	   {
    	   newTime = System.currentTimeMillis();
    	   }																//newtime chứa thông tin thời gian lúc ấn cách
       
        isPress=1;
       
       
    }

    @Override
    public void keyReleased(KeyEvent e) {    
        keys[e.getKeyCode()] = false;   
        keyPressedTime[e.getKeyCode()] = System.currentTimeMillis();		//keypresstime chứa thông tin thời gian lúc thả nút cách
        isPress=0;															//is press để chứa thông tin người chơi đã ấn cách hay chưa
      time=keyPressedTime[e.getKeyCode()]-newTime;							//time chứa thông tin khoảng thời gian giữa ấn và thả cách
      if(time>=22*40)time=22*40;
int key = e.getKeyCode();
		
		for(int i = 0 ; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);					//khi id = với id của nhân vật 
			if(tempObject.getId()==ID.Player)
			{
				if(tempObject.getVelY()==0)
				{
				if(key == KeyEvent.VK_SPACE ) {								//khi key == code của nút space thì gán tốc độ Y cho nhân vật
					tempObject.setVelY(-(time/40));
					IsCount = 1;		
					}
				
				
				}
			}
		}
    }

    public double keyPressedTime(int k) {
        return keyPressedTime[k];
    }

    public boolean isKeyPressed(int k) {
        return keys[k];
    }
    

    public boolean isKeyCombo(int[] k) {
        boolean ret = true;
        for(int i = 0;i < k.length; i++) {
            ret &= keys[k[i]];
        }
        return ret;
    }

}