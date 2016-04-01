package PrathamVasaClock;
//Import the vital packages
import java.awt.*;
import java.applet.*;
import static java.awt.Color.BLACK;
import java.beans.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;



public class PrathamClockDisplay extends Canvas implements Serializable, Runnable 
{
//State and the properties of the class PrathamVasaClock
private transient Image     offImage;
private transient Graphics  offGrfx;
private transient Thread    clockThread;
private boolean             raised;
private boolean             digital;

private transient Color foreground_color=Color.BLUE;
private transient Color background_color=Color.BLACK;
		  
int hh;
int mm;
int ss;
int x_co_cc=200,y_co_cc=150;
int x_co_hr,y_co_hr,x_co_min,y_co_min,x_co_sec,y_co_sec,s,m,h;
    //private Object frame;

		  


// Constructors
public PrathamClockDisplay(boolean r, boolean d) 
{

super();//calling the SuperClass constructor

raised=r;
digital=d;
setSize(400,300);
//setBackground(Color.BLACK);
//setBackground(new Color(0,0,0));

clockThread=new Thread(this,"NewClass6");
clockThread.start();

}


public PrathamClockDisplay() 
{
			
this(false, false);//set default values for digital and raised

}



// Accessor methods
public boolean isRaised()//returns TRUE/FALSE for raised 
{

return raised;

}



public void setRaised(boolean r)//it gives 3D-EFFECTS at the border of the clock 
{

raised = r;
repaint();//invokes the update() which in turn invokes the paint()

}



public boolean isDigital()//returns TRUE/FALSE for the digital
{

return digital;

}



public void setDigital(boolean d)//it displays ANALOG/DIGITAL clock 
{

digital = d;
repaint();//invokes the update() which in turn invokes the paint()
 
}

public void setForegroundColor(Color fgcolor)
{
    foreground_color=fgcolor;
    repaint();
}

public Color getForegroundColor()
        {
            return foreground_color;
        }

public void setBackgroundColor(Color bgcolor)
{
    background_color=bgcolor;
    repaint();
}

public Color getBackgroundColor()
{
    return background_color;
}



// Other public methods
public void run() 
{

Thread pthread=Thread.currentThread();
while(clockThread==pthread)//this condition will continuously run the thread 
{
    repaint();
    try
    {
        Thread.sleep(998);
       
    }
    catch(InterruptedException e)
    {
        
    }
}
}

public void update(Graphics g)//erases the previous history and invokes PAINT() 
{

paint(g);
  
}



public synchronized void paint(Graphics g)
{
	Dimension d = getSize();

// Create the offscreen graphics content
offImage = createImage(d.width, d.height);
offGrfx = offImage.getGraphics();
		    

// Paint the background with 3D effects
offGrfx.setColor(background_color);
offGrfx.fillRect(0,0,500,400);
offGrfx.draw3DRect(0,0,500,400,raised);
//offGrfx.fill3DRect(0,0,400,300,raised);


// Paint the clock
if (digital)//if TRUE then draw the DIGITAL CLOCK
{
	
drawDigitalClock(offGrfx);

}
else//if FALSE then draw the ANALOG CLOCK
{

drawAnalogClock(offGrfx);

}



// Paint the image onto the screen
g.drawImage(offImage, 0, 0, null);
		  
}



private void drawDigitalClock(Graphics g)
{

Dimension d=getSize();
	
//getting the current system time
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
String curr_time=sdf.format(cal.getTime());	
	
//setting the display properties	
g.setFont(new Font("Serif", Font.HANGING_BASELINE, 50)); 
g.setColor(foreground_color);
g.drawString(curr_time,85,150);
}




// Private support methods
private void drawAnalogClock(Graphics g)
{
Dimension d=getSize();
//initializing the variables
int hh;
int mm;
int ss;
int x_co_cc=200,y_co_cc=150;
int x_co_hr,y_co_hr,x_co_min,y_co_min,x_co_sec,y_co_sec,s,m,h;

	
		
//setting the properties for the Clock shape as a whole	
g.setFont(new Font("Serif", Font.HANGING_BASELINE, 30)); 
g.setColor(Color.white);
g.fillOval(x_co_cc-150,y_co_cc-150,300,300);
//g.fillOval(x_co_cc-150,y_co_cc-50,100,100);

//setting the properties for the numbers displayed on the clock
g.setColor(foreground_color);
g.drawString("3",x_co_cc+135,y_co_cc+0);
g.drawString("6",x_co_cc-10,y_co_cc+145);
g.drawString("9",x_co_cc - 145,y_co_cc+0);
g.drawString("12",x_co_cc - 10,y_co_cc - 130);
g.drawString("COEN235",150,205);

g.setColor(foreground_color);
g.drawString("1", x_co_cc+60, y_co_cc-100);     				
g.drawString("2", x_co_cc+115, y_co_cc-65);     				
g.drawString("4", x_co_cc+112, y_co_cc+72);     				
g.drawString("5", x_co_cc+75, y_co_cc+115);     				
g.drawString("7", x_co_cc-80, y_co_cc+125);     				
g.drawString("8", x_co_cc-123, y_co_cc+72);     				
g.drawString("10",x_co_cc-125, y_co_cc-55);     				
g.drawString("11",x_co_cc-80, y_co_cc-105);     				
	   					




//g.drawLine(,,,);
//g.drawLine(,,,);
//g.drawLine(,,,);
//g.drawLine(,,,);



Calendar cal1=Calendar.getInstance();


//getting the current system time in the form of integer
h=cal1.get(Calendar.HOUR_OF_DAY);
m=cal1.get(Calendar.MINUTE);
s=cal1.get(Calendar.SECOND);


//calculations for the co-ordinates of current time
x_co_sec=(int)(Math.cos(s * 3.14f / 30 - 3.14f / 2)*120+x_co_cc);
y_co_sec=(int)(Math.sin(s * 3.14f / 30 - 3.14f / 2)*120+y_co_cc);
x_co_min=(int)(Math.cos(m * 3.14f / 30 - 3.14f / 2)*100+x_co_cc);
y_co_min=(int)(Math.sin(m * 3.14f / 30 - 3.14f / 2)*100+y_co_cc);
x_co_hr=(int)(Math.cos((h * 30 + m / 2) * 3.14f / 180 - 3.14f / 2)*80+x_co_cc);
y_co_hr=(int)(Math.sin((h * 30 + m / 2) * 3.14f / 180 - 3.14f / 2)*80+y_co_cc);


//drawing the seconds hand of the clock
g.setColor(Color.red);
g.drawLine(x_co_cc,y_co_cc,x_co_sec,y_co_sec); 


//drawing the minute hand of the clock
g.setColor(Color.black);
g.drawLine(x_co_cc-1,y_co_cc,x_co_min,y_co_min);
g.drawLine(x_co_cc,y_co_cc-1,x_co_min,y_co_min);


//drawing the hour hand of the clock
g.setColor(Color.black);
g.drawLine(x_co_cc-1,y_co_cc,x_co_hr,y_co_hr);
g.drawLine(x_co_cc,y_co_cc-1,x_co_hr,y_co_hr);

}

  
		  
		  
		  
}//end of the class

