# W4_P4
CS501 Mobile App Dev Worksheet 4 Part 4
Direction Flinger


Create 5 activity xmls : activity_main, activity_north, activity_south, activity_east, activity_west  
Create 5 activity .java : MainActivity, NorthActivity, SouthActivity, EastActivity, WestActivity  
  
  
**layout xmls**  
basic: constraintLayout with one TextView  
activity_north has an extra ImageView for the image  

**implement GestureDetectors**  
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener

**where everything happens, in all of the activity .java files**  
public boolean onTouchEvent(MotionEvent event) {  
...   
 //get the initialX and initialY coordinates when you press down on the screen  
 //when you lift your finger, get the finalX and finalY values  
 //compare the values   
 //finalX - initialX for horizontal direction  
 //finalY - initialY for vertical direction  
 //depending on the direction, we declare and initialize an intent _directionIntent_ to which we feed an activity .java file  
 //then we startActivity(_directionIntent_)  
 //and called a transition to make it look smoother ;)  
}  

**transitions**  
res > anim directory  
created fade_in.xml file and shake.xml file  
  
**for shake animation (picture in north activity)**  
use sensormanager  
get all the x,y,z etc values  
calculate shakeX,Y,Z values   
_above shake values calculations from W4_P3_  
if shake is significant, apply the shake animation  
  
  
  
