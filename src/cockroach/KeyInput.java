package cockroach;
			//class này bỏ

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private int IsCount=0;
	
	
	public KeyInput(Handler handler)
	{
		this.handler=handler;
	}

	public void keyPressed(KeyEvent evt)
	{
		int key = evt.getKeyCode();
		
		for(int i = 0 ; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Player)
			{
				if(tempObject.getVelY()==0)
				{
				if(key == KeyEvent.VK_SPACE ) {
					tempObject.setVelY(-14);
					IsCount = 1;
					}
				
				
				}
			}
		}
	}
	public void keyRelease(KeyEvent evt)
	{
		
	}
	public int getIsCount()
	{
			return IsCount;
	}
	

}
